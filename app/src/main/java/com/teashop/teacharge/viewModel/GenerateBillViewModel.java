package com.teashop.teacharge.viewModel;

import android.app.Application;
import android.content.Context;
import android.text.Layout;
import android.widget.Toast;

import com.ngx.tp300x_sdk.NGXPOSPrinter;
import com.teashop.teacharge.Model.BillingModel;
import com.teashop.teacharge.View.GenerateBill;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class GenerateBillViewModel {

    String total;
    NGXPOSPrinter ngxposPrinter = NGXPOSPrinter.getInstance();
    List<BillingModel> mbillingList;
    Context ctx;

    public GenerateBillViewModel(List<BillingModel> billingList,String total,Context ctx) {

         mbillingList=billingList;
         this.total=total;
         this.ctx=ctx;
    }




    public void generateBill() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
String date=String.valueOf(currentDay)+"/"+String.valueOf(currentMonth)+"/"+String.valueOf(currentYear);

        try {


            ngxposPrinter.resetAll();
            ngxposPrinter.setAlign(Layout.Alignment.ALIGN_CENTER);
            ngxposPrinter.setBoldStyle();
            ngxposPrinter.printText("Tea Charge\n\n");
            ngxposPrinter.setUnderLine();
            ngxposPrinter.printText("CASH BILL\n");
            ngxposPrinter.setAlign(Layout.Alignment.ALIGN_NORMAL);
            ngxposPrinter.removeUnderLine();
            ngxposPrinter.setHorizontalTabPosition((byte) 30);
            ngxposPrinter.printText("Bill NO:10\tDate:+"+date+"\n");
            ngxposPrinter.setUnBoldStyle();
            ngxposPrinter.printText("------------------------------------------------\n");
            ngxposPrinter.setBoldStyle();
            ngxposPrinter.setHorizontalTabPosition((byte) 20, (byte) 33, (byte) 40);
            ngxposPrinter.printText("ITEM\tPRICE \tQTY\tTOTAL\n");
            ngxposPrinter.setUnBoldStyle();
            StringBuilder str = new StringBuilder("------------------------------------------------\n");

    for(int i=0;i<mbillingList.size();i++)
    {
        str.append(mbillingList.get(i).getProduct()+"\t"+mbillingList.get(i).getPrice()+
                  "\t"+mbillingList.get(i).getQuantity()+"\t"+mbillingList.get(i).getTotal());
    }

    str.append("----------------------------------------------\n");

            ngxposPrinter.setHorizontalTabPosition((byte) 25, (byte) 40);
            //// ngxposPrinter.setBoldStyle();
            str.append("\ttotal:\t"+total+"\n")
                    .append("------------------------------------------------\n");
            ngxposPrinter.printText(str.toString());
            str.setLength(0);

            ngxposPrinter.setDoubleWidth();
            ngxposPrinter.setHorizontalTabPosition((byte) 5, (byte) 18);
            ngxposPrinter.printText("Thank You Visit Again");
            ngxposPrinter.paperFeedAndCut(mbillingList.size()+2);

        } catch (Exception e) {
            e.printStackTrace();
                }


    }
}