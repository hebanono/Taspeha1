package com.example.ecs.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by heba on 23/11/2016.
 */
public class DbConnection extends SQLiteOpenHelper {
    public static final int version =12;
    public static final String name = "link";

     Context context;

    public DbConnection(Context context)
    {
        super(context, name, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Taspeh(ID PRIMARY KEY,Arabic TEXT ,English TEXT) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS User(Utaspeh TEXT )");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Taspeh");
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    private long check;

    public void insert(int id,String arabic, String eng) {

        if (arabic == null || eng == null) {
            Log.i("error", "data is not valid try again");
        } else {
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();
                 cv.put("ID",id);
                cv.put("Arabic", arabic);
                cv.put("English", eng);
                long check = db.insert("Taspeh", null, cv);

                Log.i("message5555", String.valueOf(check));
                db.close();
                if (check > 0) {
                    Log.i("message", "successfully inserted");
                } else {
                    Log.i("error", "data is not inserted");
                }
            } catch (Exception e) {
                e.getCause();
            }


        }


    }

    public ArrayList<String> select(int id) {
        ArrayList<String> get = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select Arabic,English from Taspeh where ID="+id, null);

        if (c.moveToFirst()) {
            do {
                String a = c.getString(c.getColumnIndex("Arabic"));
                String e = c.getString(c.getColumnIndex("English"));
                get.add(a);
                get.add(e);
            } while (c.moveToNext());


        }
        return get;
    }
    public void insertuser_taspeh(String taspeh)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Utaspeh",taspeh);
        long check = db.insert("User", null, cv);
    }



    public ArrayList<String> getUtaspeh()
    {
        ArrayList<String> get = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select Utaspeh from User ", null);

        if (c.moveToFirst()) {
            do {

                String a = c.getString(c.getColumnIndex("Utaspeh"));
                get.add(a);

            } while (c.moveToNext());


        }
        return get;
    }
    }

