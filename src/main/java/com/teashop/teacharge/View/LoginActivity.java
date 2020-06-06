package com.teashop.teacharge.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.ActivityLoginBinding;
import com.teashop.teacharge.viewModel.LoginViewModel;

import java.util.Observer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClick;
    ActivityLoginBinding mbinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel();
        mbinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mbinding.edtName.getText().toString().matches("") && mbinding.edtPwd.getText().toString().matches("")) {
                    startActivity(new Intent(LoginActivity.this, ProductsCategory.class));
                    Log.d("loginresponse", "onClick: " + "empty");
                } else {
                    Log.d("doramiEditWala", "onClick: " + mbinding.edtName.getText().toString() + "\t" + mbinding.edtPwd.getText().toString());
                    SignInModel signInModel = new SignInModel();

                    signInModel.setUsername(mbinding.edtName.getText().toString());
                    signInModel.setPassword(mbinding.edtPwd.getText().toString());

                    loginViewModel.GetLoginResponse(signInModel).observe(LoginActivity.this, new androidx.lifecycle.Observer<GenericModel>() {
                        @Override
                        public void onChanged(GenericModel genericModel) {

                            if (genericModel != null) {
                                if (genericModel.getErrorCode().equals("200"))
                                    startActivity(new Intent(LoginActivity.this, ProductsCategory.class));
                                else
                                    Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }

        });


    }


    @Override
    public void onClick(View v) {

        if (mbinding.edtName.getText().toString().matches("") && mbinding.edtPwd.getText().toString().matches("")) {
            Log.d("dorami", "onClick: " + "user name or passowrd is empty");
        } else {
            Log.d("doramiEditWala", "onClick: " + mbinding.edtName.getText().toString() + "\t" + mbinding.edtPwd.getText().toString());
            SignInModel signInModel = new SignInModel();

            signInModel.setUsername(mbinding.edtName.getText().toString());
            signInModel.setPassword(mbinding.edtPwd.getText().toString());
            // ApiResponse(signInModel);


            //            if (response)
//                startActivity(new Intent(this, ProductsCategory.class));
//            else
//                startActivity(new Intent(this, ProductsCategory.class));

//                Toast.makeText(this, "Username or Password is incorect", Toast.LENGTH_SHORT).show();


        }
    }
}
