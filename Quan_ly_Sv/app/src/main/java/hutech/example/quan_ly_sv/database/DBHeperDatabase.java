package hutech.example.quan_ly_sv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHeperDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PLSQL.db";
    private static final int DATABASE_VESION = 2;
    public DBHeperDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null ,DATABASE_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqllogin = "CREATE TABLE login(username text PRIMARY KEY, password text, fullname text,gender text,namsinh int);";
        db.execSQL(sqllogin);
        String sqlsv = "CREATE TABLE SINHVIEN(MaSV text PRIMARY KEY, HovaTen text, gioitinh text, namsinh INTEGER, anhsv text);";
        db.execSQL(sqlsv);
        String sqlInsert = "INSERT INTO SINHVIEN VALUES(1, 'Dương Thành Nam', 'Nam', 2005, '');";
        db.execSQL(sqlInsert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        db.execSQL("DROP TABLE IF EXISTS SINHVIEN");
        //tiến hành tạo bảng mới
        onCreate(db);
    }

    public SQLiteDatabase ketNoIsRead (){
        SQLiteDatabase db = getReadableDatabase();
        return db;
    }

    public SQLiteDatabase ketNoiWrite() {
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }
    public void xoaSinhVien(String maSV) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("SINHVIEN", "MaSV=?", new String[]{maSV});
        db.close();

    }
    public void capNhatSinhVien(String originalMasv, String masv, String hovaten, String gioitinh, int namsinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSV", masv);
        values.put("HovaTen", hovaten);
        values.put("gioitinh", gioitinh);
        values.put("namsinh", namsinh);

        db.update("SINHVIEN", values, "MaSV = ?", new String[]{originalMasv});
        db.close();
    }
}
