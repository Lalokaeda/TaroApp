package com.example.taro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button rectangle_2;
    public static int c;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rectangle_2 =(Button) findViewById(R.id.rectangle_2);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
    }

    public void means(View view) {
        try{
            c=1;
            Intent intent = new Intent(MainActivity.this, spravs.class);
            startActivity(intent);
        } catch(Exception e){

        }
    };

    public void spraw(View view) {
        try{
            c=2;
            Intent intent = new Intent(MainActivity.this, spravs.class);
            startActivity(intent);
        } catch(Exception e){

        }
    };

    public void tren(View view) {
        try{
            Intent intent = new Intent(MainActivity.this, trena.class);
            startActivity(intent);
        } catch(Exception e){

        }
    };
}