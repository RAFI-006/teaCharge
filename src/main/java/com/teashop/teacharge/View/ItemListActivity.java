package com.teashop.teacharge.View;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.teashop.teacharge.R;

import androidx.appcompat.app.AppCompatActivity;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
       // ImageView img = (ImageView) findViewById(R.id.bis1);
    }

    public void openDialog1(View view) {
        Dialog dialog = new Dialog(ItemListActivity.this);
        dialog.setContentView(R.layout.activity_itemcalulation);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}

