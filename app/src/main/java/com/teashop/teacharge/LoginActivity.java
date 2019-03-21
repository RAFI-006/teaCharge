package com.teashop.teacharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
          btnClick = (Button) findViewById(R.id.btn_login) ;
        btnClick.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this,ItemListActivity.class);
        startActivity(intent);
        finish();
    }
}
