package hutech.example.buoi6;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import hutech.example.buoi6.common.common;
import hutech.example.buoi6.database.DBHeperDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button btnregister, btnreset;
    EditText edtuser, edtpass, edtconfirm, edtfullname;
    RadioButton rdnam, rdnu;
    Spinner spnamsinh;
    String ns;
    ArrayList<String> arrayList;
    DBHeperDatabase dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        dbh = new DBHeperDatabase(this);
        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle("Đăng kí tài khoản");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Initialize views
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        edtconfirm = findViewById(R.id.edtconfirm);
        edtfullname = findViewById(R.id.edtfullname);
        btnregister = findViewById(R.id.btnregister);
        btnreset = findViewById(R.id.btnreset);
        rdnam = findViewById(R.id.rdnam);
        rdnu = findViewById(R.id.rdnu);
        spnamsinh = findViewById(R.id.spnamsinh);

        // Set up spinner
        arrayList = new ArrayList<>();
        for (int i = 1980; i < 2020; i++) {
            arrayList.add("" + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnamsinh.setAdapter(arrayAdapter);

        // bắt sự kiện nút tạo tài khoản
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p1 = edtpass.getText().toString();
                String p2 = edtconfirm.getText().toString();
                if(!p1.equals(p2)){
                    Toast.makeText(getApplicationContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                    edtpass.requestFocus();
                    edtpass.setText("");
                    edtconfirm.setText("");
                }
                else {
                    if(p1.equals("") || p2.equals("")){
                        Toast.makeText(getApplicationContext(),"Mật khẩu không thể để trống",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String u = edtuser.getText().toString();
                        if(u.length() == 10 || common.isNumeric(u) ){
                            String p3 = edtfullname.getText().toString();
                            String gt = "Nam";
                            if (rdnu.isChecked()) {
                                gt = "Nữ";
                            }
                            Log.d("infortk", u + " " + p1 + " " + p2 + " " + p3 + " " + gt + " " + ns);

                            try {
                                String sql = "insert into Login values('"+u+"','"+p1+"','"+p2+"','"+gt+"','"+ns+"')";
                                SQLiteDatabase db = dbh.getWritableDatabase();
                                db.execSQL(sql);
                                Toast.makeText(getApplicationContext(), "tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            } catch (Exception e){
                                Toast.makeText(getApplicationContext(), "tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "tài khoản không hơp lệ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
        // bắt nút làm lại
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtuser.requestFocus();
                edtuser.setText("");
                edtpass.setText("");
                edtconfirm.setText("");
                edtfullname.setText("");
                rdnam.setChecked(true);
                spnamsinh.setSelection(0);
            }
        });

        //hiện thị năm sinh
        spnamsinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ns = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
