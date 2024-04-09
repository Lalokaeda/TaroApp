package com.example.taro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class spravs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton filter;
    DrawerLayout mamarama;
    NavigationView nav_view;
    EditText find;

    RecyclerView rcvCard;
    TarotAdapter TarotAdapter;
    SchemeAdapter SchemeAdapter;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    List<Cards> CL = new ArrayList<>();
    List<Scheme> SL=new ArrayList<>();
    Cursor curs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spravs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        filter=(ImageButton) findViewById(R.id.filter);
        mamarama=findViewById(R.id.mamarama);
        nav_view=findViewById(R.id.nav_view);
        find=findViewById(R.id.find);
        rcvCard = findViewById(R.id.rcvCard);


        switch (MainActivity.c) {
            case 1:
                toolbar.setTitle("Справочник значений");
nav_view.setNavigationItemSelectedListener(this);
filter.setVisibility(View.VISIBLE);
nav_view.setVisibility(View.VISIBLE);
break;
            case 2:
                toolbar.setTitle("Справочник раскладов");
                filter.setVisibility(View.GONE);
find.setHint("Найти расклад...");
nav_view.setVisibility(View.GONE);
                break;
        }

        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        switch (MainActivity.c) {
            case 1:
        curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id", null);
       CL=getFromDB(curs);
       TA(CL);
                break;
            case 2:
                curs =db.rawQuery("SELECT _id, Name, Pic, Quests From Rasklad", null);
                SL=getSCHFromDB(curs);
                SA(SL);
                break;
        }

find.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        char search='%';
        switch (MainActivity.c) {
            case 1:
        curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Name like '"+search+ s.toString() +search+"' or Mast like'"+search+ s.toString()+search+"'", null);
        CL=getFromDB(curs);
        TA(CL);
        break;
            case 2:
                curs =db.rawQuery("SELECT _id, Name, Pic, Quests From Rasklad where Name like '"+search+ s.toString() +search+"'", null);
                SL=getSCHFromDB(curs);
                SA(SL);
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void filter(View view) {
        if (mamarama.isDrawerOpen(GravityCompat.END)) {
            mamarama.closeDrawer(GravityCompat.END);
        }
        mamarama.openDrawer(GravityCompat.END);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (MainActivity.c) {
            case 1:
        switch(menuItem.getItemId()){
            case R.id.Dedarc:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Metka like 'Старший'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Старшие арканы", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Miniarc:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Metka like 'Младший' or Metka like 'Придворный'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Младшие арканы", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Dvorarc:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Metka like 'Придворный'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Придворные арканы", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Numarc:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Metka like 'Младший'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Числовые арканы", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Zhezl:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Mast like 'Жезлы'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Жезлы", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mech:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Mast like 'Мечи'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Мечи", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Kub:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Mast like 'Кубки'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Кубки", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Money:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id where Mast like 'Пентакли'", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Пентакли", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Cancel:
                curs =db.rawQuery("SELECT Card._id, Name, Pic, Mast, Metka From Card LEFT OUTER JOIN Mast ON Card.idMast=Mast._id INNER JOIN Metka ON Card.idMet=Metka._id", null);
                CL=getFromDB(curs);
                TA(CL);
                Toast.makeText(this, "Все карты", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
                break;
            case 2:
                break;
        }
        return false;
    }

    public List<Cards> getFromDB(Cursor curs){
        List<Cards> cardsList = new ArrayList<>();
        List<String> Means =new ArrayList<>();
            while (curs.moveToNext()) {
                int _id = curs.getInt(curs.getColumnIndex("_id"));
                String Name = curs.getString(curs.getColumnIndex("Name"));
                int Pic = getResources().getIdentifier(curs.getString(curs.getColumnIndex("Pic")), "drawable", getPackageName());
                String Mast = curs.getString(curs.getColumnIndex("Mast"));
                String Metka = curs.getString(curs.getColumnIndex("Metka"));
Cursor crs= db.rawQuery("SELECT Mean From Means where idCard = "+_id, null);
                while (crs.moveToNext()) {
                String M = crs.getString(crs.getColumnIndex("Mean"));
                Means.add(M);
                }
                Cards c = new Cards(_id, Name, Pic, Mast, Metka, (List<String>) ((ArrayList<String>) Means).clone());
                cardsList.add(c);
                crs.close();
                Means.clear();
            }
            curs.close();
            return cardsList;

    }

    public List<Scheme> getSCHFromDB(Cursor curs){
        List<Scheme> schemesList = new ArrayList<>();
        while (curs.moveToNext()) {
            int _id = curs.getInt(curs.getColumnIndex("_id"));
            String Name = curs.getString(curs.getColumnIndex("Name"));
            int Pic = getResources().getIdentifier(curs.getString(curs.getColumnIndex("Pic")), "drawable", getPackageName());
            String Quest = curs.getString(curs.getColumnIndex("Quests"));
            Scheme c = new Scheme(_id, Name, Pic, Quest);
            schemesList.add(c);
        }
        curs.close();
        return schemesList;

    }



    public void TA( List<Cards> CL)
    {
        TarotAdapter.OnCardClickListener CardClickListener = new TarotAdapter.OnCardClickListener() {
        @Override
        public void onCardClick(Cards state, int position) {
            Dialog(state, position);
        }
    };
        this.CL=CL;
        TarotAdapter = new TarotAdapter(this, CL, CardClickListener);
        rcvCard.setLayoutManager(new GridLayoutManager(this,3));
        rcvCard.setAdapter(TarotAdapter);

    }

    public void SA( List<Scheme> SL)
    {
        SchemeAdapter.OnSchemeClickListener SchemeClickListener = new SchemeAdapter.OnSchemeClickListener() {
            @Override
            public void onSchemeClick(Scheme state, int position) {
                SchDialog(state, position);
            }
        };
        this.SL=SL;
        SchemeAdapter = new SchemeAdapter(this, SL, SchemeClickListener);
        rcvCard.setLayoutManager(new LinearLayoutManager(this));
        rcvCard.setAdapter(SchemeAdapter);

    }


    public void Dialog(Cards state, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        RelativeLayout cl = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialogitetatet, null);
        builder.setView(cl);
        final View inflate = getLayoutInflater().inflate(R.layout.dialogitetatet,
                null);
        ImageView imcard = inflate.findViewById(R.id.imcard);
        TextView title = inflate.findViewById(R.id.title);
        TextView mast = inflate.findViewById(R.id.mast);
        TextView mean = inflate.findViewById(R.id.mean);
        builder.setView(inflate);
        imcard.setImageResource(state.getPicResource());
        title.setText(state.getName());
        mast.setText(String.format("%s%s", mast.getText(), state.getMast()));
        mean.setText(String.format("%s%s", mean.getText(), state.getMeans().toString().replaceAll("^\\[|\\]$", "")));
        final AlertDialog ad = builder.show();
        ImageButton closeDialog = inflate.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ad.dismiss();
         }
      });

    }

    public void SchDialog(Scheme state, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.doutra, null);
        builder.setView(cl);
        final View inflate = getLayoutInflater().inflate(R.layout.doutra,
                null);
        ImageView schemPic = inflate.findViewById(R.id.schemPic);
        builder.setView(inflate);
        schemPic.setImageResource(state.getPicResource());
        final AlertDialog ad = builder.show();
        ImageButton closeimage = inflate.findViewById(R.id.closeimage);
        closeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad.dismiss();
            }
        });

    }

}