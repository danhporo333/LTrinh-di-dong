package hutech.example.buoi6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHeperDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MBook.db";
    private static final int DATABASE_VESION = 2;

    public DBHeperDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null ,DATABASE_VESION);
    }
    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqllogin = "CREATE TABLE Login(username text PRIMARY KEY, password text,fullname text,gender text,namsinh int);";
        db.execSQL(sqllogin);
        String sqlbooks = "CREATE TABLE BOOKS(BookID text PRIMARY KEY, BookName text, Page integer, Price Float, Description text, bookimage text);";
        db.execSQL(sqlbooks);
        String sqlinsert="INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về java','');";
        db.execSQL(sqlinsert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS Login");
    db.execSQL("DROP TABLE IF EXISTS BOOKS");
    //tiến hành tạo bảng mới
        onCreate(db);
    }
    //select
    public SQLiteDatabase ketnoiisRead (){
        SQLiteDatabase db = getReadableDatabase();
        return db;
    }

    public SQLiteDatabase ketnoiWrite() {
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }

}
