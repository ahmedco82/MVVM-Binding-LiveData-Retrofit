package com.example.umangburman.databindingwithlivedata.View.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.umangburman.databindingwithlivedata.R;
import com.example.umangburman.databindingwithlivedata.View.adapter.CountryAdapter;
import com.example.umangburman.databindingwithlivedata.ViewModel.ListViewModel;
import com.example.umangburman.databindingwithlivedata.ViewModel.LoginViewModel;
import com.example.umangburman.databindingwithlivedata.databinding.ActivityRviewBinding;
import com.example.umangburman.databindingwithlivedata.service.Model.Country;
import com.example.umangburman.databindingwithlivedata.service.Model.CountryRes;
import com.example.umangburman.databindingwithlivedata.service.repository.AllCountryResponse;
import com.example.umangburman.databindingwithlivedata.service.repository.RetrofitApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RviewActivity extends AppCompatActivity {

    private ListViewModel listViewModel;
    private ActivityRviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        binding = DataBindingUtil.setContentView(RviewActivity.this, R.layout.activity_rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setHasFixedSize(true);
        // binding.recycler.setAdapter();
        binding.setLifecycleOwner(this);
        binding.setListViewModel(listViewModel);
        listViewModel.init(this);
        listViewModel.getCountries().observe(this , new Observer<List<CountryRes>>(){
            @Override
            public void onChanged(@Nullable List<CountryRes> countryRes){
                if(countryRes != null) {
                  binding.recycler.setAdapter(new CountryAdapter(getBaseContext(),countryRes));
                  // Log.i("Result","Ok"+countryRes);
                }
            }
        });
      }
 }
















        /*
        retrofit2.Call<Country> call = RetrofitApi.getInstance().getLoginService().getCountry();
        call.enqueue(new retrofit2.Callback<Country>(){
            List<CountryRes> allcountry = new ArrayList<>();
            List<String>allCountriesData = new ArrayList<>();
            @Override
            public void onResponse(retrofit2.Call<Country>call,retrofit2.Response<Country> response) {
                Country jsonResponse = response.body();
                allcountry = jsonResponse.getCountry();
                for(int i=0;i<allcountry.size();i++ ){
                    allCountriesData.add(allcountry.get(i).getName());
                }
                Log.i("Data111", "00 __" + allCountriesData);
                Log.d("Data", "Refreshed");
                CountryAdapter countryAdapter = new CountryAdapter( context , allcountry);
                recyclerView.setAdapter(countryAdapter);
                // mOnGetDatalistener.onSuccess("List Size:" + allCountriesData.size() , allcountry);
            }
            @Override
            public void onFailure(retrofit2.Call<Country>call,Throwable t) {
               Log.v("Error",t.getMessage());
            }
        });
        */