package com.teashop.teacharge.View;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import com.teashop.teacharge.Fragment.ProductDescriptionFragment;
import com.teashop.teacharge.R;
import androidx.appcompat.app.AppCompatActivity;

public class ProductsDescription extends AppCompatActivity {

   //DialogFragment dialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemcalulation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ProductDescriptionFragment.newInstance());

        }
    }

    }










