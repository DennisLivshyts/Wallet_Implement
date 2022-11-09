package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.testnet.solana.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SInterface solanaInterface = retrofit.create(SInterface.class);
        String[] walletAddress = {"8tfDNiaEyrV6Q1U4DEXrEigs9DoDtkugzFbybENEbCDz"};
        Call<SInterface.GetBalanceResponse> responseCall = solanaInterface.retreiveBalance(
                new SInterface.GetBalanceRequest(
                        "2.0",
                        1,
                        "getBalance",
                        walletAddress
                )
        );
        responseCall.enqueue(new Callback<SInterface.GetBalanceResponse>() {
            @Override
            public void onResponse(@NonNull Call<SInterface.GetBalanceResponse> call, Response<SInterface.GetBalanceResponse> response) {
                try {
                    if(response.isSuccessful()){
                        textView.setText("Success: " + response.body().toString());
                    } else {
                        textView.setText("Failed to access Solana wallet: " + response.errorBody().string());
                    }
                } catch (IOException exception){
                    textView.setText(exception.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SInterface.GetBalanceResponse> call, Throwable t) {
                t.printStackTrace();
                textView.setText("onFailure: "+t.getMessage());
            }
        });

    }

}