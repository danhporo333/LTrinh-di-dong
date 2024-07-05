package hutech.example.buoi5.buoi4;

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

import hutech.example.buoi4.R;
import hutech.example.buoi5.buoi4.model.SinhVien;

public class MainDeTaiActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    TextView txtmasv, txthoten,txtnamsinh,txtgioitinh;
    ImageView imgsv;
    private ArrayList<SinhVien> arrayListSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_de_tai);
        txtmasv=findViewById(R.id.masv);
        txthoten=findViewById(R.id.tensv);
        txtnamsinh=findViewById(R.id.namsinh);
        txtgioitinh=findViewById(R.id.gioitinh);
        imgsv=findViewById(R.id.imgsinhvien);
        Toolbar toolbar =  findViewById(R.id.toolbardetail);
        toolbar.setTitle("Thông tin chi tiết");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //nhận dữ liệu về
        Intent in = getIntent();
        String masv= in.getStringExtra("MASV");
        String hoten= in.getStringExtra("TENSV");
        String gt = in.getStringExtra("GIOITINH");
        int namsinh = in.getIntExtra("NAMSINH", 0);
        int anhsv = in.getIntExtra("ANHSV",0);
        txtmasv.setText(masv);
        txthoten.setText(hoten);
        txtgioitinh.setText(gt);
        txtnamsinh.setText(""+namsinh);
        imgsv.setImageResource(anhsv);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        } else if (item.getItemId()==R.id.camera) {
            Toast.makeText(getApplicationContext(), "Camera", Toast.LENGTH_LONG).show();
        } else if (item.getItemId()==R.id.remove) {
            Intent in = getIntent();
            int position = in.getIntExtra("position", 0);
            CustomAdapter.Update(position, this);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}