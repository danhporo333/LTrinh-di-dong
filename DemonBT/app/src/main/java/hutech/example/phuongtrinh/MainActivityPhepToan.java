package hutech.example.phuongtrinh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import hutech.example.phuongtrinh.model.Common;

public class MainActivityPhepToan extends AppCompatActivity {
    EditText hsa,hsb,hsx;
    Button btncong,btntru,btnnhan,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_phep_toan);
        //Ánh xạ đối tượng XML sang đối tượng java
        hsa = findViewById(R.id.hesoa);
        hsb = findViewById(R.id.hesob);
        hsx = findViewById(R.id.ketqua);
        btncong = findViewById(R.id.cong);
        btntru = findViewById(R.id.tru);
        btnnhan = findViewById(R.id.nhan);
        btnDelete = findViewById(R.id.btnLamlai);
        hsx.setEnabled(false);

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String a = hsa.getText().toString();
                    String b = hsb.getText().toString();
                    String x;
                    try {
                        x = Common.pheptoan(Integer.parseInt(a), Integer.parseInt(b));
                    } catch (Exception e1) {
                        try {
                            x = Common.pheptoan(Float.parseFloat(a), Float.parseFloat(b));
                        } catch (Exception e2) {
                            try {
                                x = Common.pheptoan(Double.parseDouble(a), Double.parseDouble(b));
                            } catch (Exception e3) {
                                x = Common.pheptoan(a, b);
                            }
                        }
                    }

                    hsx.setText(x);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String a = hsa.getText().toString();
                    String b = hsb.getText().toString();
                    String x;
                    try {
                        x = Common.pheptoantru(Integer.parseInt(a), Integer.parseInt(b));
                    } catch (Exception e1) {
                        try {
                            x = Common.pheptoantru(Float.parseFloat(a), Float.parseFloat(b));
                        } catch (Exception e2) {
                            try {
                                x = Common.pheptoantru(Double.parseDouble(a), Double.parseDouble(b));
                            } catch (Exception e3) {
                                x = Common.pheptoantru(a, b);
                            }
                        }
                    }

                    hsx.setText(x);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String a = hsa.getText().toString();
                    String b = hsb.getText().toString();
                    String x;
                    try {
                        x = Common.pheptoannhan(Integer.parseInt(a), Integer.parseInt(b));
                    } catch (Exception e1) {
                        try {
                            x = Common.pheptoannhan(Float.parseFloat(a), Float.parseFloat(b));
                        } catch (Exception e2) {
                            try {
                                x = Common.pheptoannhan(Double.parseDouble(a), Double.parseDouble(b));
                            } catch (Exception e3) {
                                x = Common.pheptoan(a, b);
                            }
                        }
                    }

                    hsx.setText(x);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsa.setText("");
                hsb.setText("");
                hsx.setText("");
                hsa.requestFocus();
            }
        });
    }
}