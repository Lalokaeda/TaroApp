package com.example.taro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class realtrena extends AppCompatActivity {
    ImageView card;
    ImageView card1;
    ImageView card2;
    Button butvar1;
    Button butvar2;
    Button butvar3;
    ImageView rear;
    TextView textQuest;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    List<Cards> CL;
    List<Trip> Trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtrena);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        card=(ImageView) findViewById(R.id.card);
        card1=(ImageView) findViewById(R.id.card1);
        card2=(ImageView) findViewById(R.id.card2);
        butvar1=findViewById(R.id.butvar1);
        butvar2=findViewById(R.id.butvar2);
        butvar3=findViewById(R.id.butvar3);
        rear=findViewById(R.id.rear);
        textQuest=findViewById(R.id.textQuest);
        List<Cards> CL = new ArrayList<>();
        rear.setVisibility(View.GONE);
        switch (trena.c) {
            case 0:
                card1.setVisibility(View.GONE);
                card2.setVisibility(View.GONE);
                textQuest.setVisibility(View.GONE);
                break;
            case 1:

                break;
        }

        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();
        fill();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public List<Cards> getFromDB(Cursor curs){
            List<Cards> cardsList = new ArrayList<>();
            List<String> Means = new ArrayList<>();
            while (curs.moveToNext()) {
                int _id = curs.getInt(curs.getColumnIndex("_id"));
                String Name = curs.getString(curs.getColumnIndex("Name"));
                int Pic = getResources().getIdentifier(curs.getString(curs.getColumnIndex("Pic")), "drawable", getPackageName());
                String Mast = curs.getString(curs.getColumnIndex("Mast"));
                String Metka = curs.getString(curs.getColumnIndex("Metka"));
                Cursor crs = db.rawQuery("SELECT Mean From Means where idCard = " + _id, null);
                while (crs.moveToNext()) {
                    String M = crs.getString(crs.getColumnIndex("Mean"));
                    Means.add(M);
                }
                com.example.taro.Cards c = new com.example.taro.Cards(_id, Name, Pic, Mast, Metka, (List<String>) ((ArrayList<String>) Means).clone());
                cardsList.add(c);
                crs.close();
                Means.clear();
            }
            curs.close();
            return cardsList;
    }

    public List<Trip> get3FromDB(Cursor curs){
        List<Trip> TripList = new ArrayList<>();
        List<Integer> idCards = new ArrayList<>();
        List<Integer> Pics = new ArrayList<>();
        while (curs.moveToNext()) {
            String Quest=curs.getString(curs.getColumnIndex("Quest"));
            int numTrip=curs.getInt(curs.getColumnIndex("idTrip"));
            String mean=curs.getString(curs.getColumnIndex("Tripmean"));
            Cursor crs= db.rawQuery("SELECT idCard, Pic From Card inner join T_K on Card._id=T_K.idCard where idTrip ="+ numTrip, null);
            while (crs.moveToNext()) {
                int idCard= crs.getInt(crs.getColumnIndex("idCard"));
                int Pic = getResources().getIdentifier(crs.getString(crs.getColumnIndex("Pic")), "drawable", getPackageName());
                idCards.add(idCard);
                Pics.add(Pic);
            }
            Trip c = new Trip((List<Integer>) ((ArrayList<Integer>) idCards).clone(), Quest, (List<Integer>) ((ArrayList<Integer>) Pics).clone(), numTrip, mean);
            TripList.add(c);
            crs.close();
            idCards.clear();
            Pics.clear();
        }
        curs.close();
        return TripList;
    }


    int pos;
public void fill(){
    Random rnd = new Random();
    Cursor cursor;
    int id;
    String meanCard;
    switch (trena.c) {
        case 0:
         cursor = db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id", null);
        CL = getFromDB(cursor);
        id = rnd.nextInt(CL.size());
        card.setImageResource(CL.get(id).getPicResource());
        int mid = rnd.nextInt(CL.get(id).getMeans().size());
        meanCard = CL.get(id).getMeans().get(mid);
        break;
        
        case 1:
            cursor = db.rawQuery("select DISTINCT Quest, idTrip, Tripmean from Question inner join Trip on Question._id=Trip.idQuest inner join T_K on Trip._id=T_K.idTrip", null);
            Trip=get3FromDB(cursor);
            id = rnd.nextInt(Trip.size());
            textQuest.setText("Вопрос: "+Trip.get(id).getQuest());
            card1.setImageResource(Trip.get(id).getPicResource().get(0));
            card.setImageResource(Trip.get(id).getPicResource().get(1));
            card2.setImageResource(Trip.get(id).getPicResource().get(2));
            meanCard=Trip.get(id).getMean();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + trena.c);
    }
    pos = rnd.nextInt(3);
    switch (pos) {
        case 0:
            butvar2.setText(Crd(id));
            butvar3.setText(Crd(id));
            butvar1.setText(meanCard);
            break;
        case 1:
            butvar1.setText(Crd(id));
            butvar2.setText(meanCard);
            butvar3.setText(Crd(id));
            break;
        case 2:
            butvar1.setText(Crd(id));
            butvar2.setText(Crd(id));
            butvar3.setText(meanCard);
            break;
    }
}
    String Crd(int trueid){
        Random rnd = new Random();
        int id;
        String mC="";
    switch (trena.c){
        case 0:
        id = rnd.nextInt(CL.size());
        int mid =rnd.nextInt(CL.get(id).getMeans().size());
        mC=CL.get(id).getMeans().get(mid);
        if (CL.get(trueid).getMeans().contains(mC)){
            return Crd(trueid);
        }
       break;
        case 1:
            id = rnd.nextInt(Trip.size());
            mC=Trip.get(id).getMean();
            if (Trip.get(trueid).getMean().contains(mC)){
                return Crd(trueid);
            }
            break;
    }
     return mC;
}

    public void var1(View view) {
        rear.setVisibility(View.VISIBLE);
    switch (pos) {
        case 0:
            butvar2.setTextColor(getResources().getColor(R.color.red));
            butvar3.setTextColor(getResources().getColor(R.color.red));
            butvar1.setTextColor(getResources().getColor(R.color.green));
            break;
        case 1:
            butvar1.setTextColor(getResources().getColor(R.color.red));
            butvar2.setTextColor(getResources().getColor(R.color.green));
            butvar3.setTextColor(getResources().getColor(R.color.red));
            break;
        case 2:
            butvar1.setTextColor(getResources().getColor(R.color.red));
            butvar2.setTextColor(getResources().getColor(R.color.red));
            butvar3.setTextColor(getResources().getColor(R.color.green));
            break;
    }

    };


    public void rear(View view) {
        fill();

        butvar1.setTextColor(getResources().getColor(R.color.txt_icons));
        butvar2.setTextColor(getResources().getColor(R.color.txt_icons));
        butvar3.setTextColor(getResources().getColor(R.color.txt_icons));

        rear.setVisibility(View.GONE);
    }
}