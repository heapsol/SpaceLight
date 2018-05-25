package com.the.lightspace.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Cool Programmer on 9/13/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "LightSpace";

    // Tables names
    private static final String TABLE_FAVORITES = "favorites";

    // Table "tattoo" attributes
    private static final String KEY_ID = "id";
    private static final String KEY_VIDEO_ID = "video_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_THUMBNAIL_URL = "thumbnail_url";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PUBLISHED_AT = "published_at";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_FAVORITE_VIDEOS = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORITES + "("
//                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_VIDEO_ID + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_THUMBNAIL_URL + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PUBLISHED_AT + " TEXT)";
        Log.d("Tag==", TABLE_FAVORITE_VIDEOS);
        sqLiteDatabase.execSQL(TABLE_FAVORITE_VIDEOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);

        // create new tables
        onCreate(sqLiteDatabase);
    }

    // add new record to "tattoo" table
    public void add(DbModel model) {
        try {
            if (checkDuplicate(model.getVideoID()) == 0) {
                SQLiteDatabase database = this.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(KEY_VIDEO_ID, model.getVideoID().toString());
                contentValues.put(KEY_TITLE, model.getTitle().toString());
                contentValues.put(KEY_THUMBNAIL_URL, model.getThumbnailUrl().toString());
                contentValues.put(KEY_DESCRIPTION, model.getDescription().toString());
                Log.e("date", model.getPubishedAt().toString());
                contentValues.put(KEY_PUBLISHED_AT, model.getPubishedAt().toString());

                database.insert(TABLE_FAVORITES, null, contentValues);
                database.close();
            } else {
//            Toast.makeText(MainA, "Data Already exists", Toast.LENGTH_SHORT).show();
                Log.e("Fav Existance", "Already Exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getting All records from "tattoo" table
    public ArrayList<DbModel> getAllFavorites() {

        ArrayList<DbModel> tattooModelArrayList = new ArrayList<DbModel>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DbModel model = new DbModel();

                model.videoID = cursor.getString(0);
                model.title = cursor.getString(1);
                model.thumbnailUrl = cursor.getString(2);
                model.description = cursor.getString(3);
                model.pubishedAt = cursor.getString(4);

                // Adding contact to list
                tattooModelArrayList.add(model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return tattooModelArrayList;
    }

    // delete one record from "tattoo" table
    public void delete(String VideoID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FAVORITES + " WHERE " + KEY_VIDEO_ID + "='" + VideoID + "'");
        db.close();
    }

    public int checkDuplicate(String VideoID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        try {
            String query = "select count(*) from " + TABLE_FAVORITES + " WHERE " + KEY_VIDEO_ID + "='"
                    + VideoID + "'";
//                    + " AND "+KEY_SEARCH_URL+"='"+link+"'";
            c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }


}
