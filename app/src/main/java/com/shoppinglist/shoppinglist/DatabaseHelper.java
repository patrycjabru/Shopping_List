package com.shoppinglist.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shopping_list.db";
    public static final String TABLE_NAME = "saved_items";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM_NAME";
    public static final String COL_3 = "NUMBER_OF_RESULTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2 +" TEXT, "+COL_3+" INEGER)";
        db.execSQL(query);
        System.out.println("onCreate: "+query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        System.out.println("onUpgrade: "+query);
        onCreate(db);
    }

    public boolean insertData(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        System.out.println("Inserting data for item "+itemName);
        contentValues.put(COL_2, itemName);
        //TODO Add API call to get number of results
        long result = db.insert(TABLE_NAME,null,contentValues);
        System.out.println("Inserting row result: "+result);
        if (result == -1)
            return  false;
        return  true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }

    public boolean removeData(Integer id) {


        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println("Removing data for item "+id);

        boolean result =  db.delete(TABLE_NAME, COL_1 + "=" + id, null) > 0;

        if (result) {
            CellContent.removeItem(id);
        }

        return result;
    }
}
