package com.example.biblio_tech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    //change version when upgraded
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bibliotech.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Favori.TABLE_NAME + " (" +
                DBContract.Favori._ID_LIVRE + " INTEGER PRIMARY KEY)";
        db.execSQL(query);

        String query2 =  "CREATE TABLE " + DBContract.Lu.TABLE_NAME + " (" +
                DBContract.Lu._ID_LIVRE + " INTEGER PRIMARY KEY)";
        db.execSQL(query2);

        String query3 =  "CREATE TABLE " + DBContract.Lu.TABLE_NAME + " (" +
                DBContract.EnCours._ID_LIVRE + " INTEGER PRIMARY KEY)";
        db.execSQL(query3);

        String query4 =  "CREATE TABLE " + DBContract.Lu.TABLE_NAME + " (" +
                DBContract.ALire._ID_LIVRE + " INTEGER PRIMARY KEY)";
        db.execSQL(query4);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Favori.TABLE_NAME;
        db.execSQL(query);
        String query2= "DROP TABLE IF EXISTS " + DBContract.Lu.TABLE_NAME;
        db.execSQL(query2);
        String query3= "DROP TABLE IF EXISTS " + DBContract.EnCours.TABLE_NAME;
        db.execSQL(query3);
        String query4= "DROP TABLE IF EXISTS " + DBContract.ALire.TABLE_NAME;
        db.execSQL(query4);

        onCreate(db);
    }

    public void insertFavori(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(DBContract.Favori._ID_LIVRE,id);

        long newRowId = db.insert(DBContract.Favori.TABLE_NAME,null,row);
    }
    public void insertLu(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(DBContract.Lu._ID_LIVRE,id);

        long newRowId = db.insert(DBContract.Lu.TABLE_NAME,null,row);
    }
    public void insertEnCours(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(DBContract.EnCours._ID_LIVRE,id);

        long newRowId = db.insert(DBContract.EnCours.TABLE_NAME,null,row);
    }
    public void insertALire(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(DBContract.ALire._ID_LIVRE,id);

        long newRowId = db.insert(DBContract.ALire.TABLE_NAME,null,row);
    }

    public void deleteFavori(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DBContract.Favori._ID_LIVRE+"="+id;

        String[] selectionArgs ={String.valueOf(id)};

        int count = db.delete(DBContract.Favori.TABLE_NAME,selection,selectionArgs);
    }
    public void deleteLu(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DBContract.Lu._ID_LIVRE+"="+id;

        String[] selectionArgs ={String.valueOf(id)};

        int count = db.delete(DBContract.Lu.TABLE_NAME,selection,selectionArgs);
    }
    public void deleteEnCours(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DBContract.EnCours._ID_LIVRE+"="+id;

        String[] selectionArgs ={String.valueOf(id)};

        int count = db.delete(DBContract.EnCours.TABLE_NAME,selection,selectionArgs);
    }
    public void deleteALire(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DBContract.ALire._ID_LIVRE+"="+id;

        String[] selectionArgs ={String.valueOf(id)};

        int count = db.delete(DBContract.ALire.TABLE_NAME,selection,selectionArgs);
    }

    public List<Response> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        String [] livres = {
                DBContract.Favori._ID_LIVRE
        };

        Cursor cursor = db.query(
                DBContract.Favori.TABLE_NAME,
                livres,null, null, null, null,null
        );

        List<Response> responses = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt((int)cursor.getColumnIndex(DBContract.Favori._ID_LIVRE));

            Response r = new Response(id);
            responses.add(r);
        }
        cursor.close();
        return responses;
    }

}
