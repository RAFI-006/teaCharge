/*
package com.teashop.teacharge.View;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
import com.teashop.teacharge.databinding.ActivityGenerateBillBinding;
import com.teashop.teacharge.prefs.PrefSetting;
import com.teashop.teacharge.viewModel.GenerateBillViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateBill extends Fragment {
    private BluetoothPrinter mBtp = BluetoothPrinterMain.mBtp;
    Integer total = 0;
    List<BillingModel> billingList;
    OrderItemParams params;
    PrefSetting pfs;
    PostTransactionHistoryModel PostOrdermodel;
    List<OrderItemParams> Orderlist = new ArrayList<>();
    int count = 1123224;
    ApiRepository repo;

    ActivityGenerateBillBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billingList = new ArrayList<>();
        PostOrdermodel = new PostTransactionHistoryModel();
        billingList = (ArrayList<BillingModel>) getActivity().getIntent().getSerializableExtra("bill");
        Log.d("billActivity", "onCreate: " + billingList);


        pfs = new PrefSetting(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_generate_bill, container, false);

        initControls(view);
        return view;
    }
*/
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bill);

     billingList=new ArrayList<>();
         PostOrdermodel = new PostTransactionHistoryModel();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_generate_bill);

        billingList=(ArrayList<BillingModel>)getIntent().getSerializableExtra("bill");
        Log.d("billActivity", "onCreate: "+billingList);


         pfs=new PrefSetting(getApplicationContext());

        Button btnTestBill = (Button) v.findViewById(R.id.btnTestBill);
        btnTestBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_REGULAR);
                mBtp.setPrintFontSize(BtpCommands.FONT_SIZE_NORMAL);
                mBtp.printText("          Cash Bill             ");
                mBtp.printLineFeed();
                mBtp.printTextLine("----------------------------------");
                mBtp.printTextLine("Items     Qty     Rate      Amt");
                mBtp.printTextLine("-------------------------------");
                mBtp.printTextLine("Reynolds Pen     2     10    20");
                mBtp.printTextLine("Natraj Pen       2     10    20");
                mBtp.printTextLine("Next Item        20    5    100");
                mBtp.printLineFeed();
                mBtp.printTextLine("-------------------------------");
                mBtp.printTextLine("Tot Items: 2      Amount: 66.50");
                mBtp.printTextLine("               ----------------");
                mBtp.printTextLine("                 Net Amt: 70.00");
                mBtp.printLineFeed();
                mBtp.printTextLine("      Thank you ! Visit Again");
                mBtp.printLineFeed();
                mBtp.printTextLine("*******************************");
                mBtp.printLineFeed();
            }
        });



      final   GenerateBillViewModel model=new GenerateBillViewModel(billingList,total,this);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        mBinding.date.setText(currentDate);
        new LoadDataTask().execute(0);
        int billId=pfs.GetBillId();
        if(billId!=0)
        {
            pfs.SaveBillId(++billId);
            mBinding.billId.setText(String.valueOf(pfs.GetBillId()));

        }
        else
        {
            int billid=110010;
            pfs.SaveBillId(billid);

        }

        mBinding.printBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //model.generateBill();
                PostOrders();

                Toast.makeText(GenerateBill.this,"Order Posted Successfully",Toast.LENGTH_SHORT).show();
            }
        });


        }*//*



    private void initControls(View v) {
        billingList = new ArrayList<>();
        PostOrdermodel = new PostTransactionHistoryModel();

        mBinding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_generate_bill);

        billingList = (ArrayList<BillingModel>) getActivity().getIntent().getSerializableExtra("bill");
        Log.d("billActivity", "onCreate: " + billingList);


        pfs = new PrefSetting(getActivity());

        mBinding.btnLeftAlign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mBtp.showDeviceList(getActivity());
            }
        });


        mBinding.printBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PostOrders();
            }
        });

        final GenerateBillViewModel model = new GenerateBillViewModel(billingList, total, getActivity());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        mBinding.date.setText(currentDate);
        new LoadDataTask().execute(0);
        int billId = pfs.GetBillId();
        if (billId != 0) {
            pfs.SaveBillId(++billId);
            mBinding.billId.setText(String.valueOf(pfs.GetBillId()));

        } else {
            int billid = 110010;
            pfs.SaveBillId(billid);

        }
*/
/*
        mBinding.printBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //model.generateBill();
                PostOrders();

                Toast.makeText(getActivity(),"Order Posted Successfully",Toast.LENGTH_SHORT).show();
            }
        });*//*

    }

    void PostOrders() {

        for (int i = 0; i < billingList.size(); i++) {
            params = new OrderItemParams();
            params.setProductId(billingList.get(i).getID());
            params.setPrice(billingList.get(i).getPrice());
            params.setQuantity(billingList.get(i).getQuantity());
            // total += billingList.get(i).getPrice();
            Log.d("dorami", "PostOrders: " + total);
            Orderlist.add(params);
        }

        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


        PostOrdermodel.setUserId(1);
        PostOrdermodel.setOrderitems(Orderlist);
        PostOrdermodel.setTotalQuantity(billingList.size());
        PostOrdermodel.setSaleDate(currentDate);
        PostOrdermodel.setTotalPrice(total);
        PostOrdermodel.setBillId(pfs.GetBillId());

        mBtp.setPrintFontStyle(BtpCommands.FONT_STYLE_REGULAR);
        mBtp.setPrintFontSize(BtpCommands.FONT_SIZE_NORMAL);
        mBtp.printText("          Chai Boy             ");
        mBtp.printLineFeed();
        mBtp.printTextLine("                               ");
        mBtp.printTextLine(currentDate + "      " + "Bill No:- " + pfs.GetBillId());
        mBtp.printTextLine("----------9491771333-----------");
        mBtp.printTextLine("-------------------------------");
        mBtp.printTextLine("Sno  Items         Qty      Amt");
        mBtp.printTextLine("-------------------------------");
        mBtp.printLineFeed();
        for (int i = 0; i < billingList.size(); i++) {
            mBtp.printTextLine(String.valueOf(i + 1) + "  " + billingList.get(i).getProduct() + "         " + billingList.get(i).getQuantity() + "      " + billingList.get(i).getPrice());
        }
        mBtp.printLineFeed();
        mBtp.printTextLine("-------------------------------");
        mBtp.printTextLine("            " + "Sub Total:- " + total);
        mBtp.printTextLine("                     ----------------");
        mBtp.printTextLine("             " + " Total:- " + (total));
        mBtp.printLineFeed();
        mBtp.printTextLine("  WhatsApp this  Bill no to    ");
        mBtp.printTextLine("                     8333813131");
        mBtp.printLineFeed();
        mBtp.printTextLine("*******************************");
        mBtp.printLineFeed();

        repo = BaseApi.getClient().create(ApiRepository.class);

        Call<JsonObject> call = repo.PostOrders(PostOrdermodel);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.message().equalsIgnoreCase("OK")) {
                    Toast.makeText(getActivity(), "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), ProductsCategory.class));
                }

                Log.d("PostHistory", "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //        mBinding.fab.setOnClickListener(new View.OnClickListener() {
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
            final TextView tv = new TextView(getActivity());
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
            final TextView tv2 = new TextView(getActivity());
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


            final TextView tv3 = new TextView(getActivity());
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
            final TextView tv4 = new TextView(getActivity());
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

            final TableRow tr = new TableRow(getActivity());
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
            mBinding.tablelayout.addView(tr, trParams);


            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(getActivity());
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(getActivity());
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);

                mBinding.tablelayout.addView(trSep, trParamsSep);

            }


        }

        mBinding.totalPrice.setText(totalprice + "");
        total = totalprice;

    }

    static class LoadDataTask extends AsyncTask<Integer, Integer, String> {
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


}*/
