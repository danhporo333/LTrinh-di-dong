package hutech.example.phuongtrinh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import hutech.example.phuongtrinh.model.Common;

public class MainActivityPTB2 extends AppCompatActivity {
    EditText hsa,hsb,hsc,result;
    Button buttongiaib2,buttonlamlaib2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ptb2);
        //ánh xạ đối tượng XML sang đối tượng java
        hsa = findViewById(R.id.ptb2soa);
        hsb = findViewById(R.id.ptb2sob);
        hsc = findViewById(R.id.ptb2soc);
        result = findViewById(R.id.editkq);
        result.setEnabled(false);
        buttongiaib2 = findViewById(R.id.buttongiai);
        buttonlamlaib2 = findViewById(R.id.btlamlai);

        //Bắt sự kiện
        buttongiaib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ClickOk","ok");
//                Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                try {
                    String a = hsa.getText().toString();
                    String b = hsb.getText().toString();
                    String c = hsc.getText().toString();
                    String x = Common.PTB2(Float.parseFloat(a),Float.parseFloat(b),Float.parseFloat(c));
                    result.setText(x);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonlamlaib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hsa.setText("");
                hsb.setText("");
                hsc.setText("");
                result.setText("");
                hsa.requestFocus();
            }
        });
    }
}