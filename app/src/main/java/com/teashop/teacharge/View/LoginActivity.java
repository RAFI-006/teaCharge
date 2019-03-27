package com.teashop.teacharge.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClick;
EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnClick = (Button) findViewById(R.id.btn_login) ;



        btnClick.setOnClickListener(this);
        Init();

    }

  void  Init()
    {
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);


    }




    @Override
    public void onClick(View v) {

        SignInModel signInModel=new SignInModel();

        signInModel.setUsername(username.getText().toString());
        signInModel.setPassword(password.getText().toString());
       // ApiResponse(signInModel);
  GenericModel model = LoginViewModel.DoApiLogin(username.getText().toString(),password.getText().toString());

  if(model.getErrorCode().equals("Success"))
  startActivity(new Intent(this,ProductsCategory.class));
  else
      startActivity(new Intent(this,ProductsCategory.class));

    }
}
