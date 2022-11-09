package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SInterface {
    class GetBalanceRequest {

        public GetBalanceRequest(String jsonrpc, Integer id, String method, String[] params) {
            this.jsonrpc = jsonrpc;
            this.id = id;
            this.method = method;
            this.params = params;
        }

        @SerializedName("jsonrpc")
        String jsonrpc;
        @SerializedName("id")
        Integer id;
        @SerializedName("method")
        String method;
        @SerializedName("params")
        String[] params;
    }
    class GetBalanceResponse {

        class Result {
            class Context {
                @SerializedName("slot")
                Integer slot;
                @Override
                public String toString() {
                    return "Context{" +
                            "slot=" + slot +
                            '}';
                }
            }
            @SerializedName("context")
            SInterface.GetBalanceResponse.Result.Context context;
            @SerializedName("value")
            BigInteger value;

            @Override
            public String toString() {
                return "Result{" +
                        "context=" + context +
                        ", value=" + value +
                        '}';
            }
        }

        @SerializedName("jsonrpc")
        String jsonrpc;
        @SerializedName("id")
        Integer id;
        @SerializedName("result")
        SInterface.GetBalanceResponse.Result result;

        @Override
        public String toString() {
            return "GetBalanceResponse{" +
                    "jsonrpc='" + jsonrpc + '\'' +
                    ", id=" + id +
                    ", result=" + result +
                    '}';
        }
    }
    @POST("/")
    Call<GetBalanceResponse> retreiveBalance(
            @Body GetBalanceRequest request
    );

}
