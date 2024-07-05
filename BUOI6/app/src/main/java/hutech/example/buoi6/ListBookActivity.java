package hutech.example.buoi6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import hutech.example.buoi6.database.CustomAdapter;
import hutech.example.buoi6.database.DBHeperDatabase;
import hutech.example.buoi6.model.Book;

public class ListBookActivity extends AppCompatActivity {
    ListView lvbooks;
    ArrayList<Book> arrbook;
    CustomAdapter adapter;
    DBHeperDatabase dbh;
    EditText edtsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_book);
        Toolbar toolbar =  findViewById(R.id.toolbar2);
        toolbar.setTitle("Danh sách các quyển sách");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lvbooks = findViewById(R.id.lvlistbook);
        arrbook = new ArrayList<>();
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
        arrbook.clear();
        SQLiteDatabase db = dbh.ketnoiisRead();
        Cursor cursor = db.rawQuery("SELECT * FROM BOOKS where (bookid like '%"+s+"%') or  (bookname like '%"+s+"%')", null);
        try {
            while (cursor.moveToNext()){
                Book b=new Book(cursor.getString(0),cursor.getString(1),Integer.parseInt(cursor.getString(2)),Float.parseFloat(cursor.getString(3)),cursor.getString(4),cursor.getString(5));
                arrbook.add(b);
            }
            adapter = new CustomAdapter(this, arrbook);
            lvbooks.setAdapter(adapter);
        } finally {
            cursor.close();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
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