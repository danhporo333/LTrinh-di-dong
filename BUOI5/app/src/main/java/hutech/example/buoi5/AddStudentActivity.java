package hutech.example.buoi5;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hutech.example.buoi5.model.SinhVien;

public class AddStudentActivity extends AppCompatActivity {
    EditText edtmasv,edttensv,edtnamsinh;
    RadioButton rdnam,rdnu;
    Button btnluu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar =  findViewById(R.id.toolbar_addsv);
        toolbar.setTitle("Thêm sinh viên mới");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        edtmasv=findViewById(R.id.edtmasv);
        edttensv=findViewById(R.id.edttensv);
        edtnamsinh=findViewById(R.id.edtnamsinh);
        rdnam=findViewById(R.id.ranam);
        rdnu=findViewById(R.id.rdnu);
        btnluu=findViewById(R.id.btnluu);
        rdnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdnu.setChecked(false);
            }
        });
        rdnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdnam.setChecked(false);

            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = edtmasv.getText().toString();
                String tensv = edttensv.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                String gt = "Nam";
                if (rdnu.isChecked())
                    gt = "Nữ";
                SinhVien SV = new SinhVien(masv,tensv,gt,Integer.parseInt(namsinh),R.drawable.hoicham);
                Intent in = new Intent();
                in.putExtra("data",SV);
                setResult(4, in);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
