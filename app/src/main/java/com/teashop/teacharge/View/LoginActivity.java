package com.teashop.teacharge.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.teashop.teacharge.Model.LoginResponse;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.ActivityLoginBinding;
import com.teashop.teacharge.prefs.PrefSetting;
import com.teashop.teacharge.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding mbinding;
    LoginViewModel loginViewModel;
    SharedPreferences sp;
    PrefSetting pfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sp = getSharedPreferences("your_shared_pref_name", MODE_PRIVATE);
        pfs = new PrefSetting(this);
        loginViewModel = new LoginViewModel();
        onClickListerner();

    }

    private void onClickListerner() {
        mbinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbinding.pBar.setVisibility(View.VISIBLE);
                if (mbinding.edtName.getText().toString().matches("") && mbinding.edtPwd.getText().toString().matches("")) {
                    // startActivity(new Intent(LoginActivity.this, ProductsCategory.class));
                    mbinding.pBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Enter Valied Username", Toast.LENGTH_SHORT).show();
                    Log.d("loginresponse", "onClick: " + "empty");
                } else {
                    Log.d("doramiEditWala", "onClick: " + mbinding.edtName.getText().toString() + "\t" + mbinding.edtPwd.getText().toString());
                    final SignInModel signInModel = new SignInModel();

                    signInModel.setUsername(mbinding.edtName.getText().toString());
                    signInModel.setPassword(mbinding.edtPwd.getText().toString());

                    loginViewModel.GetLoginResponse(signInModel).observe(LoginActivity.this, new androidx.lifecycle.Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse genericModel) {
                            mbinding.pBar.setVisibility(View.GONE);
                            if (genericModel != null) {
                                if (genericModel.getStatus() == 200) {
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putInt("userId", Integer.parseInt(genericModel.getData().getUserId()));
                                    editor.putString("userName", genericModel.getData().getUserName());
                                    editor.apply();

                                    pfs.SaveUserCode(genericModel.getData().getUserCode());
                                    startActivity(new Intent(LoginActivity.this, ProductsCategory.class));
                                    finish();
                                } else
                                    Toast.makeText(LoginActivity.this, "Username  is incorrect", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(LoginActivity.this, "Username is incorrect", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }

        });
    }

}
