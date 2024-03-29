package com.example.umangburman.databindingwithlivedata.View.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.example.umangburman.databindingwithlivedata.service.Model.LoginUser;
import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.ViewModel.LoginViewModel;
import com.example.umangburman.databindingwithlivedata.databinding.ActivityMainBinding;
import java.util.Objects;

// https://github.com/umangburman/MVVM-DataBinding-With-LiveData-Login

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this , R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this , new Observer<LoginUser>(){
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())){
                    binding.txtEmailAddress.setError("Enter an E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (!loginUser.isEmailValid()){
                    binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                    binding.txtPassword.setError("Enter a Password");
                    binding.txtPassword.requestFocus();
                }
                else if (!loginUser.isPasswordLengthGreaterThan5()){
                    binding.txtPassword.setError("Enter at least 6 Digit password");
                    binding.txtPassword.requestFocus();
                }
                else {
                    binding.lblEmailAnswer.setText(loginUser.getStrEmailAddress());
                    binding.lblPasswordAnswer.setText(loginUser.getStrPassword());

                    Intent goToListView = new Intent(getBaseContext() , RviewActivity.class);
                    startActivity(goToListView);
                }
               // startActivity allow you to move .......
            }
        });


    }
}






