package hutech.example.phuongtrinh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import hutech.example.phuongtrinh.model.Common;

public class MainActivityPTB1 extends AppCompatActivity {
    EditText hsa,hsb,hsx;
    Button btngiaib1,btnlamlaib1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ptb1);
        //Ánh xạ đối tượng XML sang đối tượng java
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
        hsa=findViewById(R.id.edta);
        hsb=findViewById(R.id.edtb);
        hsx=findViewById(R.id.edtx);
        btngiaib1=findViewById(R.id.btngiai);
        btnlamlaib1=findViewById(R.id.btnlamlai);
        hsx.setEnabled(false);//dùng để làm mờ và ko cho nhập vào  kết quả
        //Bắt sự kiện
        btngiaib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ClickOk","ok");
//                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                try {
                    String a = hsa.getText().toString();
                    String b = hsb.getText().toString();
                    String x = Common.PTB1(Float.parseFloat(a),Float.parseFloat(b));
                    hsx.setText(x);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnlamlaib1.setOnClickListener(new View.OnClickListener() {
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
