package hutech.example.quan_ly_sv;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import hutech.example.quan_ly_sv.database.DBHeperDatabase;
import hutech.example.quan_ly_sv.model.SinhVien;

public class MainDeTailActivity extends AppCompatActivity {
    TextView txtmasv, txthoten, txtnamsinh, txtgioitinh;
    Button btndetailsua;
    ImageView imgsv;
    String masv;
    DBHeperDatabase dbh;
    private static final int UPDATE_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_de_tail);

        dbh = new DBHeperDatabase(this);
        txtmasv = findViewById(R.id.masv);
        txthoten = findViewById(R.id.tensv);
        txtnamsinh = findViewById(R.id.namsinh);
        txtgioitinh = findViewById(R.id.gioitinh);
        imgsv = findViewById(R.id.imgsinhvien);
        btndetailsua = findViewById(R.id.btndetailsua);

        Toolbar toolbar = findViewById(R.id.toolbardetail);
        toolbar.setTitle("Thông tin chi tiết");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Nhan du lieu tu extra
        SinhVien sv = (SinhVien) getIntent().getSerializableExtra("Object");
        masv = sv.getMasv();
        String hoten = sv.getHovaten();
        String gt = sv.getGioitinh();
        int namsinh = sv.getNamsinh();
        String anhsv = sv.getAnhsv();

        txtmasv.setText(masv);
        txthoten.setText(hoten);
        txtgioitinh.setText(gt);
        txtnamsinh.setText("" + namsinh);
        imgsv.setImageBitmap(StringToBitMap(anhsv));

        btndetailsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateStudentActivity.class);
                intent.putExtra("masv", sv.getMasv());
                intent.putExtra("hovaten", sv.getHovaten());
                intent.putExtra("namsinh", String.valueOf(sv.getNamsinh()));
                intent.putExtra("gioitinh", sv.getGioitinh());
                intent.putExtra("anhsv", sv.getAnhsv());
                Log.d("anh","anh di: " +sv.getAnhsv());
                startActivityForResult(intent, UPDATE_REQUEST_CODE);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}