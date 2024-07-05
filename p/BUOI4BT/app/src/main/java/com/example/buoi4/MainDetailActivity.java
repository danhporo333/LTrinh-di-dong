package com.example.buoi4;

import android.app.Activity;
import android.content.Context;
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

import com.example.buoi4.model.CustomAdapter;

public class MainDetailActivity extends AppCompatActivity {

    TextView txtmasv,txttensv,txtgioitinh,txtnamsinh;
    ImageView imgsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_detail);
        Toolbar toolbar =  findViewById(R.id.toolbardetail);
        toolbar.setTitle("Thông tin chi tiết");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        txtmasv=findViewById(R.id.txtmasv1);
        txttensv=findViewById(R.id.txttensv1);
        txtgioitinh=findViewById(R.id.txtgt1);
        txtnamsinh=findViewById(R.id.txtnamsinh1);
        imgsv= findViewById(R.id.imgsv1);

        // Nhan du lieu tu extra
        Intent in = getIntent();
        String masv = in.getStringExtra("Masv");
        String tensv = in.getStringExtra("Tensv");
        String gt = in.getStringExtra("Gt");
        int namsinh = in.getIntExtra("Namsinh", 0);
        int img = in.getIntExtra("Anhsv",0);

        txtmasv.setText(masv);
        txttensv.setText(tensv);
        txtgioitinh.setText(gt);
        txtnamsinh.setText(""+namsinh);
        imgsv.setImageResource(img);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        else if (item.getItemId() == R.id.camera) {
            Toast.makeText(getApplicationContext(), "Camera", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.remove) {
            Intent in = getIntent();
            int position = in.getIntExtra("position", 0);
            CustomAdapter.Update(position, this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}