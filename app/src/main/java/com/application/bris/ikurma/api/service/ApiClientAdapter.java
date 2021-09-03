package com.application.bris.ikurma.api.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NullOnEmptyConverterFactory;
import com.application.bris.ikurma.util.service_encrypt.DESHelper;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity2;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PID on 4/5/2019.
 */

public class ApiClientAdapter {
    private static Retrofit retrofit;
    private ApiInterface apiInterface;
    private String baseurl = UriApi.Baseurl.URL;
    private static final GsonConverterFactory gson = GsonConverterFactory.create(new Gson());
    private long timeout = 90;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private Context context;
    private static int id = 1; //DEFAULT
    private AppPreferences appPreferences;

    public ApiClientAdapter(Context context){
        this.context = context;
        buildConnection(baseurl, id, timeout, timeUnit);
    }

    public ApiClientAdapter(Context context, int id){
        this.context = context;
        buildConnection(baseurl, id, timeout, timeUnit);
    }

    public ApiClientAdapter(Context context, int id, String url){
        this.context = context;
        buildConnection(url, id, timeout, timeUnit);
    }

    public ApiClientAdapter(Context context, String url){
        this.context = context;
        buildConnection(url, id, timeout, timeUnit);
    }

    public ApiClientAdapter(Context context, long timeoutReq, TimeUnit timeUnitReq){
        this.context = context;
        buildConnection(baseurl, id,  timeoutReq, timeUnitReq);
    }

    public ApiClientAdapter(Context context, int id, long timeoutReq, TimeUnit timeUnitReq){
        this.context = context;
        buildConnection(baseurl, id,  timeoutReq, timeUnitReq);
    }


    private void buildConnection(String baseUrl, int id, long timeOut, TimeUnit timeUnit) {
        appPreferences = new AppPreferences(context);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor headerAuth = null;
        Interceptor headerAuthLogin = null;
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (id == 1){

            headerAuth = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

//                    Request request = chain.request();
//                    RequestBody requestBody = request.body();

                    Request request=chain.request();
                    RequestBody requestBody = request.body();
                    if(request.method().equalsIgnoreCase("POST")){
                        String subtype = requestBody.contentType().subtype();

                        //hanya request json aja yang pakai signature
                        //request non json sperti upload foto gak perlu signature
                        if(subtype.contains("json")){
                            String signature="token=Bearer "+appPreferences.getToken()+"&body="+bodyToString(requestBody);
                            AppUtil.logSecure("xsign",signature);
                            request = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                    .addHeader("X-Signature",AppUtil.hashSha256(signature).toUpperCase())
                                    .build();
                            return chain.proceed(request);
                        }
                        else{
                            String signature="token=Bearer "+appPreferences.getToken()+"&body=";
                            AppUtil.logSecure("xsign",signature);

                            request = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                    .addHeader("X-Signature",AppUtil.hashSha256(signature).toUpperCase())
                                    .build();
                            return chain.proceed(request);
                        }

                    }

                    //PROTOKOL GET TANPA HASH BODY
                    else if(request.method().equalsIgnoreCase("GET")){
                        String signature="token=Bearer "+appPreferences.getToken()+"&body=";
                        request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .addHeader("X-Signature",AppUtil.hashSha256(signature).toUpperCase())
                                .build();
                        return chain.proceed(request);
                    }
                    else{
                        //DEFAULTNYA DIBIKIN SEPERTI POST
                        String signature="token=Bearer "+appPreferences.getToken()+"&body="+bodyToString(requestBody);
                        request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .addHeader("X-Signature",AppUtil.hashSha256(signature).toUpperCase())
                                .build();
                        return chain.proceed(request);
                    }

                }
            };
            clientBuilder.addInterceptor(headerAuth);

            //menambah interceptor baru jika token expired, alias perlu login ulang, atau untuk error code lain
            clientBuilder .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    okhttp3.Response response = chain.proceed(request);

                    // validasi global untuk response code tertentu

                    if (response.code() == 401) {
                        //dialog hanya bisa muncul kalo dijalankan di main thread, jadi ditaruh didalam handler
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                                //current scenario
                                if(appPreferences.getNama().equalsIgnoreCase("developer")){
                                    Intent intent=new Intent(context, LoginActivity2.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("type", "bdwelcome");
                                    intent.putExtra("expiredToken",true);
                                    context.startActivity(intent);
                                }
                                else{
                                    Intent intent=new Intent(context, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("type", "bdlogin");
                                    intent.putExtra("expiredToken",true);
                                    context.startActivity(intent);
                                }
                            }
                        });


                        return response;
                    }

                    return response;
                }
            });
            //END OF INTERCEPTOR AUTO LOGOFF

        }


        //tanpa token
        else if (id == 99){
            headerAuthLogin = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                            .build();
                    return chain.proceed(request);

                }
            };
            clientBuilder.addInterceptor(headerAuthLogin);
        }
        else{
            headerAuthLogin = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                            .build();
                    return chain.proceed(request);

                }
            };
            clientBuilder.addInterceptor(headerAuthLogin);
        }



        if (BuildConfig.SHOW_LOG) {
            clientBuilder.addInterceptor(loggingInterceptor);
        }



        OkHttpClient httpClient = clientBuilder
                                    .connectTimeout(timeOut, timeUnit)
                                    .readTimeout(timeOut, timeUnit)
                // TODO: 19/04/21 create a proper ssl checking
                                    .hostnameVerifier(new HostnameVerifier() {
                                        @Override
                                        public boolean verify(String s, SSLSession sslSession) {
                                            return true;
                                        }
                                    })
                                    .build();

        retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(gson)
                    .client(httpClient)
                    .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return apiInterface;

    }


    private String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
