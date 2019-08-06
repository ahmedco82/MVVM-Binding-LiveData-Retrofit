package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.View.adapter.CountryAdapter;
import com.example.umangburman.databindingwithlivedata.service.Model.Country;
import com.example.umangburman.databindingwithlivedata.service.Model.CountryRes;
import com.example.umangburman.databindingwithlivedata.service.repository.RetrofitApi;

import java.util.ArrayList;
import java.util.List;


public class ListViewModel extends ViewModel{

   private MutableLiveData <List<CountryRes>> allcountries = new MutableLiveData<>();

    public void init(Context context){
        fetchData();
    }

    public MutableLiveData <List<CountryRes>>getCountries() {
        return allcountries;
    }
    public void fetchData(){
        retrofit2.Call<Country> call = RetrofitApi.getInstance().getLoginService().getCountry();
        allcountries = new MutableLiveData<>();
        call.enqueue(new retrofit2.Callback<Country>(){
            List<CountryRes> allcountry = new ArrayList<>();
            List<String>allCountriesData = new ArrayList<>();
            @Override
            public void onResponse(retrofit2.Call<Country>call,retrofit2.Response<Country> response) {
                Country jsonResponse = response.body();
                allcountry = jsonResponse.getCountry();
                /*
                for(int i=0;i<allcountry.size();i++ ){
                  allCountriesData.add(allcountry.get(i).getName());
                }
                */
                allcountries.setValue(allcountry);
             }
            @Override
            public void onFailure(retrofit2.Call<Country>call,Throwable t) {
                Log.v("Error",t.getMessage());
            }
        });
    }
}
