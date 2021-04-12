/**
 * Copyright 2014 NGX Technologies Pvt. Ltd http://ngxtech.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teashop.teacharge.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.JsonObject;
import com.ngx.BluetoothPrinter;
import com.ngx.BtpCommands;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Model.BillingModel;
import com.teashop.teacharge.Model.OrderItemParams;
import com.teashop.teacharge.Model.PostTransactionHistoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.Repository.ApiRepository;
import com.teashop.teacharge.databinding.BtpMainBinding;
import com.teashop.teacharge.prefs.PrefSetting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BluetoothPrinterMain extends AppCompatActivity {
    // private BluetoothPrinter mBtp = BluetoothPrinterMain.mBtp;
    SharedPreferences sp;
    public static BluetoothPrinter mBtp = BluetoothPrinter.INSTANCE;
    BtpMainBinding mbinding;
    private static FragmentManager fragMgr;
    private Fragment nm;
    private static final String cHomeStack = "home";
    private TextView tvStatus;

    private String mConnectedDeviceName = "";
    public static final String title_connecting = "connecting...";
    public static final String title_connected_to = "connected: ";
    public static final String title_not_connected = "not connected";

    Integer total = 0;
    String mytotal ="";
    List<BillingModel> billingList;
    OrderItemParams params;
    PrefSetting pfs;
    PostTransactionHistoryModel PostOrdermodel;
    List<OrderItemParams> Orderlist = new ArrayList<>();
    int count = 1123224;
    ApiRepository repo;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BluetoothPrinter.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothPrinter.STATE_CONNECTED:
                            mbinding.tvStatus.setText(title_connected_to);
                            //   Toast.makeText(BluetoothPrinterMain.this, mConnectedDeviceName, Toast.LENGTH_LONG).show();
                            mbinding.tvDeviceName.setText(mConnectedDeviceName);
                            break;
                        case BluetoothPrinter.STATE_CONNECTING:
                            //  Toast.makeText(BluetoothPrinterMain.this, title_connecting, Toast.LENGTH_LONG).show();
                            mbinding.tvStatus.setText(title_connecting);
                            break;
                        case BluetoothPrinter.STATE_LISTEN:

                        case BluetoothPrinter.STATE_NONE:
                            // Toast.makeText(BluetoothPrinterMain.this, title_not_connected, Toast.LENGTH_LONG).show();
                            mbinding.tvStatus.setText(title_not_connected);
                            break;
                    }
                    break;
                case BluetoothPrinter.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(
                            BluetoothPrinter.DEVICE_NAME);
                    break;
                case BluetoothPrinter.MESSAGE_STATUS:
                    mbinding.tvStatus.setText(msg.getData().getString(
                            BluetoothPrinter.STATUS_TEXT));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("your_shared_pref_name", MODE_PRIVATE);
        mbinding = DataBindingUtil.setContentView(this, R.layout.btp_main);
        //  fragMgr = getFragmentManager();

        // lockOrientation(this);

        //	mBtp.setDebugService(BuildConfig.DEBUG);

        try {

            mBtp.initService(this, mHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        billingList = new ArrayList<>();
        PostOrdermodel = new PostTransactionHistoryModel();
        billingList = (ArrayList<BillingModel>) getIntent().getSerializableExtra("bill");
        Log.d("billActivity", "onCreate: " + billingList);

        mytotal = getIntent().getStringExtra("total");
        pfs = new PrefSetting(this);

        initControls();

      /*  nm = new GenerateBill();

        fragMgr.beginTransaction().replace(R.id.container, nm)
                .addToBackStack(cHomeStack).commit();*/
    }

    @Override
    public void onPause() {
       // mBtp.onActivityPause();
        super.onPause();
    }

    @Override
    public void onResume() {
       // mBtp.onActivityResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
     //   mBtp.onActivityDestroy();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mBtp.onActivityResult(requestCode, resultCode, data, this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
    /*        switch (fragMgr.getBackStackEntryCount()) {
                case 0:
                    startActivity(new Intent(BluetoothPrinterMain.this, ProductsCategory.class));
                case 1:
                    finish();
                    break;
                case 2:
                    fragMgr.popBackStack();
                    break;
                default:
                    fragMgr.popBackStack();
                    break;
            }
            return true;*/
            startActivity(new Intent(BluetoothPrinterMain.this, ProductsCategory.class));
            finish();
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_clear_device:
                // deletes the last used printer, will avoid auto connect
                Builder d = new Builder(this);
                d.setTitle("NGX Bluetooth Printer");
                // d.setIcon(R.drawable.ic_launcher);
                d.setMessage("Are you sure you want to delete your preferred Bluetooth printer ?");
                d.setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mBtp.clearPreferredPrinter();
                                Toast.makeText(getApplicationContext(),
                                        "Preferred Bluetooth printer cleared",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                d.setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                d.show();
                return true;
            case R.id.action_connect_device:
                // show a dialog to select from the list of available printers
                mBtp.showDeviceList(this);
                return true;
            case R.id.action_unpair_device:
                Builder u = new Builder(this);
                u.setTitle("Bluetooth Printer unpair");
                // d.setIcon(R.drawable.ic_launcher);
                u.setMessage("Are you sure you want to unpair all Bluetooth printers ?");
                u.setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (mBtp.unPairBluetoothPrinters()) {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "All NGX Bluetooth printer(s) unpaired",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                u.setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });
                u.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bluetooth_printer_main, menu);
        return true;
    }

    @SuppressLint("InlinedApi")
    public static void changeFragment(int iContainerId, Fragment fragment,
                                      boolean addToBackStack) {
        FragmentTransaction t = fragMgr.beginTransaction();

        if (iContainerId == R.id.container) {
            t.hide(fragMgr.findFragmentById(R.id.container));
            t.add(iContainerId, fragment);

            if (addToBackStack) {
                t.addToBackStack(cHomeStack);
            }
        }
        // Commit the transaction
        t.commit();
    }


    private void initControls() {
        billingList = new ArrayList<>();
        PostOrdermodel = new PostTransactionHistoryModel();

        billingList = (ArrayList<BillingModel>) this.getIntent().getSerializableExtra("bill");
        Log.d("billActivity", "onCreate: " + billingList);


        pfs = new PrefSetting(this);

        mbinding.btnLeftAlign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mBtp.showDeviceList(BluetoothPrinterMain.this);
            }
        });


        mbinding.printBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                if (mbinding.tvStatus.getText().toString().equalsIgnoreCase("connected: ")) {
//                    PostOrders();
//                } else {
//                    mBtp.showDeviceList(BluetoothPrinterMain.this);
//                }

                if(mBtp.getState() ==3)
                    PostOrders();
                else
                    mBtp.showDeviceList(BluetoothPrinterMain.this);
            }
        });
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String branchCode = Objects.requireNonNull(sp.getString("userName", "")).substring(0, 3);
        mbinding.tvDate.setText(currentDate);
        mbinding.tvUserId.setText(String.valueOf(sp.getInt("userId", 0)));
        new LoadDataTask().execute(0);
        String billId = pfs.GetUserCode() + UUID.randomUUID().toString().substring(0,8);
      mbinding.tvBillId.setText(billId);
        //        int billId = pfs.GetBillId();
//        if (billId != 0) {
//            pfs.SaveBillId(++billId);
//            mbinding.tvBillId.setText(String.valueOf(branchCode + pfs.GetBillId()));
//
//        } else {
//            int billid = 110010;
//            pfs.SaveBillId(billid);
//
//        }
/*
        mbinding.printBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //model.generateBill();
                PostOrders();

                Toast.makeText(this,"Order Posted Successfully",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    void PostOrders() {

        for (int i = 0; i < billingList.size(); i++) {
            params = new OrderItemParams();
            params.setProductId(billingList.get(i).getID());
            params.setPrice(billingList.get(i).getPrice());
            params.setQuantity(billingList.get(i).getQuantity());
            // total += billingList.get(i).getPrice();
            Log.d("total", "PostOrders: " + total);
            Orderlist.add(params);
        }

        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("HH:mm"); // Format time
        String time = df.format(Calendar.getInstance().getTime());
        String branchCode = Objects.requireNonNull(sp.getString("userName", "")).substring(0, 3);

        PostOrdermodel.setUserId(Integer.valueOf(mbinding.tvUserId.getText().toString()));
        PostOrdermodel.setOrderitems(Orderlist);
        PostOrdermodel.setTotalQuantity(billingList.size());
        PostOrdermodel.setSaleDate(currentDate);
        PostOrdermodel.setTotalPrice(total);
        PostOrdermodel.setBillId(mbinding.tvBillId.getText().toString());
        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_BOLD);
        mBtp.setPrintFontSize(BtpCommands.FONT_SIZE_NORMAL);

        mBtp.setAlignment(BtpCommands.CENTER_ALIGN);
        mBtp.printTextLine("CHAI BOY");
        mBtp.printTextLine("-------------------------------");
        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_BOLD);
        mBtp.printTextLine("--" + " Bill No:- " +  mbinding.tvBillId.getText().toString() + "--");
        mBtp.printLineFeed();
        mBtp.printTextLine("-------------------------------");
        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_BOLD);
        mBtp.printTextLine("----" + "UserCode:- " + pfs.GetUserCode() + "----");

        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_REGULAR);
        mBtp.setPrintFontSize(BtpCommands.FONT_SIZE_NORMAL);
        mBtp.printTextLine("                               ");
        mBtp.printTextLine(currentDate + "      " + "Time:- " + time);
        mBtp.printTextLine("-------------------------------");
        mBtp.printTextLine("Sno  Items  Qty           Amt");
        //  mBtp.printTextLine("                             Amt");
        mBtp.printTextLine("-------------------------------");
        for (int i = 0; i < billingList.size(); i++) {
             mBtp.setAlignment(BtpCommands.LEFT_ALIGN);
             int price = billingList.get(i).getQuantity() * billingList.get(i).getPrice();
            mBtp.printTextLine(String.valueOf(i + 1) + "  " + billingList.get(i).getProduct() + " X " + billingList.get(i).getQuantity() + " = "+ price);

//           mBtp.printText("     ");
//            mBtp.printText( billingList.get(i).getQuantity() + "   " + billingList.get(i).getPrice());


            //  mBtp.printTextLine("                             " + billingList.get(i).getPrice());
        }
        mBtp.printTextLine("-------------------------------");
        mBtp.printTextLine("             " + " Total:- " + mytotal);
        mBtp.printLineFeed();
        mBtp.printTextLine("  WhatsApp this  Bill no to :-     ");

        mBtp.setAlignment(BtpCommands.CENTER_ALIGN);
        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_BOLD);
        mBtp.printTextLine("83338 13131");
        mBtp.printTextLine("*******************************");
        mBtp.printTextLine("                               ");
        mBtp.printLineFeed();

        repo = BaseApi.getClient().create(ApiRepository.class);

        Call<JsonObject> call = repo.PostOrders(PostOrdermodel);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.message().equalsIgnoreCase("OK")) {
                    Toast.makeText(BluetoothPrinterMain.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BluetoothPrinterMain.this, ProductsCategory.class));
                }

                Log.d("PostHistory", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(BluetoothPrinterMain.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //        mbinding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//          ngxposPrinter.showDeviceList(GenerateBill.this);
//
//            }
//        });
//

    public void loadBills() {
        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize = 0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        int totalprice = 0;

        for (int i = -1; i < billingList.size(); i++) {
            if (i > -1) {
                int price = billingList.get(i).getTotal();
                totalprice = totalprice + price;
            }
            final TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.LEFT);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("SNO");
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {

                tv.setText(String.valueOf(i + 1));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
// serial id ends
//item count start
            final TextView tv2 = new TextView(this);
            if (i == -1) {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            tv2.setGravity(Gravity.LEFT);

            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("Item Count");
                tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
            } else {
                tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv2.setText(String.valueOf(billingList.get(i).getQuantity()));
            }
//item count ends
            //product started


            final TextView tv3 = new TextView(this);
            if (i == -1) {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 5, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv3.setPadding(5, 0, 0, 5);
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }


            if (i == -1) {
                tv3.setText("ProductName");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
            } else {
                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

                String name = billingList.get(i).getProduct();

                tv3.setText(billingList.get(i).getProduct());

            }

// total starts
            final TextView tv4 = new TextView(this);
            if (i == -1) {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tv4.setPadding(5, 5, 1, 5);
            } else {
                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv4.setPadding(5, 0, 1, 5);
            }
            tv4.setGravity(Gravity.RIGHT);
            if (i == -1) {
                tv4.setText("Total");
                tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv4.setText(String.valueOf(billingList.get(i).getTotal()));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }


            //add table row

            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0, 0, 0, 0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            mbinding.tablelayout.addView(tr, trParams);


            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);

                mbinding.tablelayout.addView(trSep, trParamsSep);

            }


        }

        mbinding.totalPrice.setText(totalprice + "");
        total = totalprice;

    }

    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }

        @Override
        protected void onPostExecute(String result) {
            loadBills();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


