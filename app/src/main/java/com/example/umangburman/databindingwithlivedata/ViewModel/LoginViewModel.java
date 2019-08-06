package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.umangburman.databindingwithlivedata.View.ui.MainActivity;
import com.example.umangburman.databindingwithlivedata.View.ui.RviewActivity;
import com.example.umangburman.databindingwithlivedata.service.Model.LoginUser;

public class LoginViewModel extends ViewModel {

     public  MutableLiveData<String>EmailAddress = new MutableLiveData<>();
     public  MutableLiveData<String>Password = new MutableLiveData<>();

     private MutableLiveData<LoginUser>userMutableLiveData;

     public MutableLiveData<LoginUser>getUser(){
        if(userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }


    public void onClick(View view){
        LoginUser loginUser = new LoginUser(EmailAddress.getValue(),Password.getValue() );
        userMutableLiveData.setValue(loginUser);
    }
}
