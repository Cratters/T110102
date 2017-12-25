package com.example.auser.t110102;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.*;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Context context = this;
    ArrayAdapter<String> adapter;
    ImageView imageView,imageView2;
    ArrayList<String> mylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, mylist);

        RequestQueue queue = Volley.newRequestQueue(context);
        final StringRequest request = new UTF8StringRequest("https://udn.com/rssfeed/news/2/6638?ch=news",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ImageRequest imageRequest = new ImageRequest("https://ewedit.files.wordpress.com/2015/01/teletubbies_612x380_1.jpg?w=612",
                new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        },0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565,
                new  Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
        queue.add(imageRequest);
        queue.start();
        Picasso.with(context).load("http://mmbiz.qpic.cn/mmbiz/Fv75ib75E07Ec3teJ3bdvticeAjPpywBt35mcjXco9aqKwhfPPoVvPfu49vqCvgyYxn1KiaR9bwgGgFicfy83ibVvwg/0?/1.jpg").into(imageView2);
    }
}
