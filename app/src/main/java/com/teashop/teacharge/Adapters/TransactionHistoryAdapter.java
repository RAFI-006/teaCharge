package com.teashop.teacharge.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.Model.OrderItem;
import com.teashop.teacharge.R;
import com.teashop.teacharge.View.TransactionHistory;
import com.teashop.teacharge.databinding.TransactionHistoryItemlistBinding;
import com.teashop.teacharge.prefs.PrefSetting;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.TransactionHolder> {
    int textSize; int smallTextSize; int mediumTextSize;Context ctx;
    List<GetTransactionHistoryModel> historyList;
    List<OrderItem> items=new ArrayList<>();
    PrefSetting pfs;
    public TransactionHistoryAdapter(int textSize, int smallTextSize, int mediumTextSize, Context ctx) {
this.textSize=textSize;
this.smallTextSize=smallTextSize;
this.mediumTextSize=mediumTextSize;
this.ctx=ctx;

 pfs = new PrefSetting(ctx);

    }

    public void SetHistoryList(List<GetTransactionHistoryModel> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TransactionHistoryItemlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.transaction_history_itemlist, parent, false);


        return new TransactionHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {

        holder.mbinding.billId.setText(historyList.get(position).getBillId());
        holder.mbinding.saleDate.setText(historyList.get(position).getSaleDate());
        holder.mbinding.totalPrice.setText(historyList.get(position).getTotalPrice());
        holder.mbinding.totalQuantity.setText(historyList.get(position).getTotalQuantity());
        holder.mbinding.userCode.setText(pfs.GetUserCode());
        items = historyList.get(position).getOrderItems();
        LoadProducts(items,holder.mbinding,textSize,smallTextSize,mediumTextSize,ctx);

    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
        TransactionHistoryItemlistBinding mbinding;

        public TransactionHolder(@NonNull TransactionHistoryItemlistBinding binding) {
            super(binding.getRoot());
            mbinding = binding;
        }
    }


    public  void LoadProducts(List<OrderItem> billingList, TransactionHistoryItemlistBinding mBinding, int textSize, int smallTextSize, int mediumTextSize, Context ctx) {
        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;
        int totalprice = 0;

        mBinding.tablelayout.removeAllViews();
        for (int i = -1; i < billingList.size(); i++) {
            if (i > -1) {
                int price = Integer.parseInt(billingList.get(i).getPrice());
                totalprice = totalprice + price;
            }
            final TextView tv = new TextView(ctx);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tv.setGravity(Gravity.LEFT);

            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("SNO");
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {

                tv.setText(String.valueOf(i+1));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
// serial id ends
//item count start
            final TextView tv2 = new TextView(ctx);
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


            final TextView tv3 = new TextView(ctx);
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
                tv3.setBackgroundColor(Color.parseColor("#ffffff"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv3.setText(billingList.get(i).getProductTitle());
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            }

// total starts
            final TextView tv4 = new TextView(ctx);
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
                tv4.setText(String.valueOf(billingList.get(i).getPrice()));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }


            //add table row

            final TableRow tr = new TableRow(ctx);
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
                final TableRow trSep = new TableRow(ctx);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(ctx);
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


    }

}