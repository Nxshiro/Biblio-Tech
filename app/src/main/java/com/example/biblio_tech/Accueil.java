package com.example.biblio_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Accueil extends AppCompatActivity {

    private Button fav, lu, enCours, aLire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        fav = (Button)findViewById(R.id.bfav);
        lu = (Button)findViewById(R.id.blu);
        enCours = (Button)findViewById(R.id.bcours);
        aLire = (Button)findViewById(R.id.blire);
    }

    public void search(View v){
        Intent i = new Intent (Accueil.this, Liste.class);
        startActivity(i);
    }

    public void go(View v){

        switch (v.getId()){
            case R.id.bfav:
                Intent i = new Intent(Accueil.this, Favori.class);
                startActivity(i);
                break;
            case R.id.blu:
                Intent i2 = new Intent(Accueil.this, Lu.class);
                startActivity(i2);
                break;
            case R.id.bcours:
                Intent i3 = new Intent(Accueil.this, EnCours.class);
                startActivity(i3);
                break;
            case R.id.blire:
                Intent i4 = new Intent(Accueil.this, ALire.class);
                startActivity(i4);
                break;
            default:
                break;
        }
    }

}