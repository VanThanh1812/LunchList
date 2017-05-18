package com.lunchlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vanthanhbk on 11/04/2017.
 */

public class RestaurantHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String TAG = "SQLite";
    // Phiên bản
    private static final int DATABASE_VERSION = 1;
    //tên database
    private static final String DATABASE_NAME = "DATA";
    //tên bảng
    private static final String TABLE_RESTAURENT = "TABLE_RESTAURENT";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NOTE = "note";

    public RestaurantHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String script_restaurent = "CREATE TABLE if not exists " + TABLE_RESTAURENT + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_TYPE + " TEXT,"
            + COLUMN_NOTE + " TEXT"
            + ");";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("SQLite", "CREATE DATA");
        sqLiteDatabase.execSQL(script_restaurent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "on upgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_RESTAURENT);
        onCreate(sqLiteDatabase);
    }

    // insert
    public void insertRestaurent(Items res){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, res.getName());
        values.put(COLUMN_ADDRESS, res.getNumber());
        values.put(COLUMN_TYPE, res.getUnit_price());
        values.put(COLUMN_NOTE, res.getBarCode());

        db.insert(TABLE_RESTAURENT, null, values);
        db.close();
    }

    public Cursor getAllRestaurent(String orderBy){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =db.query(TABLE_RESTAURENT,new String[]{COLUMN_ID,
                COLUMN_NAME,
                COLUMN_ADDRESS,
                COLUMN_TYPE,
                COLUMN_NOTE
        },null,null,null,null,orderBy);

        if (cursor != null) cursor.moveToFirst();
        else Log.d(TAG,"khong co gia tri thoa man");
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getRestaurentbyID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESTAURENT, new String[]{COLUMN_ID,
                COLUMN_NAME,
                COLUMN_ADDRESS,
                COLUMN_TYPE,
                COLUMN_NOTE
        }, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) cursor.moveToFirst();
        else Log.d(TAG, "khong co gia tri thoa man");
        cursor.moveToFirst();
        return cursor;
    }

    public int updateById(Items items, long id){
        Log.d(TAG,"update");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,id);
        values.put(COLUMN_NAME, items.getName());
        values.put(COLUMN_ADDRESS, items.getNumber());
        values.put(COLUMN_TYPE, items.getUnit_price());
        values.put(COLUMN_NOTE, items.getBarCode());

        return db.update(TABLE_RESTAURENT,values,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
    }

    public void delete(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURENT, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    /*
    * public int updateImageRoom (ImageRoom imageRoom){
        Log.d(TAG,"them anh cho nha tro");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IMAGE_COLUMN_ID,imageRoom.getImage_id());
        values.put(IMAGE_COLUMN_IMAGELINK,imageRoom.getImage_link());
        values.put(IMAGE_COLUMN_ROOMID,imageRoom.getRoom_id());

        return db.update(TABLE_IMAGEROOM,values,IMAGE_COLUMN_ID+"=?",new String[]{String.valueOf(imageRoom.getImage_id())});
    }
    * */
}
