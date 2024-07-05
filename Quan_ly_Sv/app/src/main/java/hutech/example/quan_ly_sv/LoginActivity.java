package hutech.example.quan_ly_sv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hutech.example.quan_ly_sv.Common.common;
import hutech.example.quan_ly_sv.database.DBHeperDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText edttendangnhap,edtpassword;
    TextView taotaikhoan;
    Button btnlogin,lamlai;
    CheckBox cbnhomatkhau;
    DBHeperDatabase dbh;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        dbh = new DBHeperDatabase(this);
        settings = getSharedPreferences("RememberLogin",0);
        edttendangnhap = findViewById(R.id.edttendangnhap);
        edtpassword = findViewById(R.id.edtpassword);
        taotaikhoan = findViewById(R.id.taotaikhoan);
        btnlogin = findViewById(R.id.btnlogin);
        lamlai = findViewById(R.id.lamlai);
        cbnhomatkhau = findViewById(R.id.cbnhomatkhau);
        taotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(in);
            }
        });

        String username = settings.getString("user", "");
        String password = settings.getString("pass", "");
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "vui lòng nhập tên đăng nhập và và mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            // có data rồi thì nó sẽ vào main lun
            Intent in = new Intent(getApplicationContext(), ListSinhVienActivity.class);
            startActivity(in);
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = edttendangnhap.getText().toString();
                String p = edtpassword.getText().toString();
                if(u.length() == 10 && common.isNumeric(u)){
                    String sql = "Select * from Login where username='"+u+"' and password ='"+p+"'";
                    SQLiteDatabase db = dbh.ketNoIsRead();
                    Cursor cs = db.rawQuery(sql,null);
                    if(cs.moveToNext()){
                        if(cbnhomatkhau.isChecked()){
                            settings.edit().putString("user" ,u).apply();
                            settings.edit().putString("pass" ,p).apply();
                        }
                        Intent in = new Intent(getApplicationContext(),ListSinhVienActivity.class);
                        startActivity(in);
//                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "mày chưa có tài khoản hãy cút ra tạo tài khoản",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "tài khoản không hơp lệ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}