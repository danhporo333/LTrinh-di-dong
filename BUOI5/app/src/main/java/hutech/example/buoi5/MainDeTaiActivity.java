package hutech.example.buoi5;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import hutech.example.buoi5.model.SinhVien;

public class MainDeTaiActivity extends AppCompatActivity {

    TextView txtmasv, txthoten, txtnamsinh, txtgioitinh;
    ImageView imgsv;
    String masv;
    private ArrayList<SinhVien> arrayListSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_de_tai);

        txtmasv = findViewById(R.id.masv);
        txthoten = findViewById(R.id.tensv);
        txtnamsinh = findViewById(R.id.namsinh);
        txtgioitinh = findViewById(R.id.gioitinh);
        imgsv = findViewById(R.id.imgsinhvien);

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
        int anhsv = sv.getAnhsv();

        txtmasv.setText(masv);
        txthoten.setText(hoten);
        txtgioitinh.setText(gt);
        txtnamsinh.setText("" + namsinh);
        imgsv.setImageResource(anhsv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        else if (item.getItemId() == R.id.camera) {
            Toast.makeText(getApplicationContext(), "Camera", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.remove) {
//            Intent in = getIntent();
//            int position = in.getIntExtra("position", 0);
//            CustomAdapter.Update(position,this);
            Intent in = new Intent();
            in.putExtra("MESSAGE",masv);
            setResult(200,in);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}