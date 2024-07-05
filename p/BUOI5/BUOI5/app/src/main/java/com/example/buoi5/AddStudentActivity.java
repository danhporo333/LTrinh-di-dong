package com.example.buoi5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buoi5.model.SinhVien;

public class AddStudentActivity extends AppCompatActivity {
    TextView txtmasv, txttensv, txtnamsinh;
    RadioButton rdinam, rdinu;
    Button btnluu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar =  findViewById(R.id.toolbaraddsv);
        toolbar.setTitle("Thêm sinh viên mới");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txtmasv=findViewById(R.id.txtmasv_add);
        txttensv=findViewById(R.id.txttensv_add);
        
        txtnamsinh=findViewById(R.id.txtnam_add);
        rdinam=findViewById(R.id.rdnam);
        rdinu=findViewById(R.id.rdnu);
        btnluu=findViewById(R.id.btnluu_add);
        rdinam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdinu.setChecked(false);
            }
        });
        rdinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdinam.setChecked(false);
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = txtmasv.getText().toString();
                String tensv = txttensv.getText().toString();
                String namsinh = txtnamsinh.getText().toString();
                String gt = "Nam";
                if (rdinu.isChecked())
                    gt = "Nữ";
                SinhVien SV = new SinhVien(masv,tensv,gt,Integer.parseInt(namsinh),R.drawable.unknown);
                Intent in = new Intent();
                in.putExtra("data",SV);
                setResult(4, in);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}