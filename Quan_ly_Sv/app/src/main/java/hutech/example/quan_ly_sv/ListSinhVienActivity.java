package hutech.example.quan_ly_sv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import hutech.example.quan_ly_sv.database.CustomAdapter;
import hutech.example.quan_ly_sv.database.DBHeperDatabase;
import hutech.example.quan_ly_sv.model.SinhVien;

public class ListSinhVienActivity extends AppCompatActivity {
    ListView lvSinhvien;
    ArrayList<SinhVien> arrsv;
    CustomAdapter adapter;
    DBHeperDatabase dbh;
    EditText edtsearch;

    TextView txtnum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_sinh_vien);

        //toolbar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Danh sách các sinh viên");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        txtnum = findViewById(R.id.txtnum);

        lvSinhvien = findViewById(R.id.lvlistsinhvien);
        arrsv = new ArrayList<>();
        dbh = new DBHeperDatabase(this);
        showDataListView("");
        edtsearch = findViewById(R.id.edtsearch);
        edtsearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String s = edtsearch.getText().toString();
                showDataListView(s);
                return false;
            }
        });
    }

    private void showDataListView(String s){
        arrsv.clear();
        SQLiteDatabase db = dbh.ketNoIsRead();
        Cursor cursor = db.rawQuery("SELECT * FROM SINHVIEN where (masv like '%"+s+"%') or  (hovaten like '%"+s+"%')", null);
        try {
            while (cursor.moveToNext()){
                SinhVien sv = new SinhVien
                        (cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4));
                arrsv.add(sv);
            }
            adapter = new CustomAdapter(this, arrsv);
            lvSinhvien.setAdapter(adapter);
            updateStudentCount();
        } finally {
            cursor.close();
            db.close();
        }
    }

    public void updateStudentCount() {
        txtnum.setText("" + arrsv.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == R.id.add) {
            Intent in = new Intent(getApplicationContext(), AddStudentActivity.class);
            startActivity(in);
        } else if (item.getItemId()==R.id.logout) {
            SharedPreferences settings = getSharedPreferences("RememberLogin", MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==200)
        {
            showDataListView("");

        }
    }
}