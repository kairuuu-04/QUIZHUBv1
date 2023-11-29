package com.appdet.quizhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuizHub.db";
    private static final String TABLE_USERS = "QuizHub_Users";
    private static final String COLUMN_ID = "User_Id";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_FULLNAME = "Fullname";
    private static final String COLUMN_DATE_OF_BIRTH = "Date_Of_Birth";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_GRADE_LEVEL = "Grade_level";
    private static final String COLUMN_GAME_LEVEL = "Game_level";
    private static final String COLUMN_SCORE = "Score";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_FULLNAME + " TEXT," +
                COLUMN_DATE_OF_BIRTH + " TEXT," +
                COLUMN_AGE + " INTEGER," +
                COLUMN_GENDER + " TEXT," +
                COLUMN_GRADE_LEVEL + " TEXT," +
                COLUMN_GAME_LEVEL + " TEXT," +
                COLUMN_SCORE + " INTEGER" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean InsertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FULLNAME, user.getFullname());
        values.put(COLUMN_DATE_OF_BIRTH, user.getDateOfBirth());
        values.put(COLUMN_AGE, user.getAge());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_GRADE_LEVEL, user.getGradeLevel());
        values.put(COLUMN_GAME_LEVEL, user.getGameLevel());
        values.put(COLUMN_SCORE, user.getScore());
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public void updateData(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USERS, values, whereClause, whereArgs);
        db.close();
    }



}
