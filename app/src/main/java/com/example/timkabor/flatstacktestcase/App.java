package com.example.timkabor.flatstacktestcase;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKSdk;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize( getApplicationContext() );
    }
    public static void loadImageFromURL(String url, ImageView imageView ) {
        Picasso.get().load(url).into(imageView);
    }
    public static String parseUnixTime(int unixTime) {
        Date date = new java.util.Date(unixTime * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
