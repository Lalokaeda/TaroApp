package com.example.taro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class trena extends AppCompatActivity {
    public static int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trena);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void one(View view) {
        try{
            c=0;
            Intent intent = new Intent(this, realtrena.class);
            startActivity(intent);
        } catch(Exception e){

        }
    };

    public void three(View view) {
        try{
            c=1;
            Intent intent = new Intent(this, realtrena.class);
            startActivity(intent);
        } catch(Exception e){

        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}