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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new ListeFragment());
        ft.commit();
    }
    public void search(View v){
        Intent i = new Intent (Accueil.this, Liste.class);
        startActivity(i);
    }

    public void goTo(@NonNull View v) {
        if (v.getId()==R.id.bfav || v.getId()==R.id.blire || v.getId()==R.id.blu||v.getId()==R.id.bcours){
            Intent i = new Intent(this, ListeFragment.class);
            startActivity(i);
        }
    }
}