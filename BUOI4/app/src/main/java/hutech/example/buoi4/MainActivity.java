package hutech.example.buoi4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import hutech.example.buoi4.model.SinhVien;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<SinhVien> arraylistSV;
    public static CustomAdapter adapter;
    ListView lvsv;
    private static final int DELETE_STUDENT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lvsv = findViewById(R.id.lvsv);
        arraylistSV = new ArrayList<>();
        SinhVien gv1 = new SinhVien("001","Lê Trung Hiếu","Nam",2003,R.drawable.lehieu);
        SinhVien gv2 = new SinhVien("002","Nguyễn Hùng Dũng","nam",2003,R.drawable.hungdung);
        SinhVien gv3 = new SinhVien("003","Trần chí Thanh","Nam",2003,R.drawable.chithanh);
        SinhVien gv4 = new SinhVien("004","Lê Trọng Phi","Nam",2002,R.drawable.phi);
        SinhVien gv5 = new SinhVien("005","Phạm Thành Nguyễn","Nam",2003,R.drawable.thanhnguyen);
        SinhVien gv6 = new SinhVien("006","Đặng Phạm Nguyễn Nhật Kha","Nam",2003,R.drawable.kha);
        SinhVien gv7 = new SinhVien("007","Nguyễn Đăng Khoa","Nam",2003,R.drawable.nguyenkhoa);
        SinhVien gv8 = new SinhVien("008","Phạm Minh Đăng Khoa","Nam",2003,R.drawable.hoicham);
        SinhVien gv9 = new SinhVien("009","Bùi Thị Kim Ngọc","Nữ",2003,R.drawable.hoicham);
        SinhVien gv10 = new SinhVien("010","Nguyễn Minh Thư","Nữ",2002,R.drawable.hoicham);
        SinhVien gv11 = new SinhVien("011","Nguyễn Ngọc Quỳnh","Nữ",2003,R.drawable.hoicham);
        SinhVien gv12 = new SinhVien("012","Trương Thu Thảo","Nữ",2003,R.drawable.hoicham);
        SinhVien gv13 = new SinhVien("013","Đinh Yang Phiệt,","Nam",2003,R.drawable.phiet);
        SinhVien gv14 = new SinhVien("014","Cao Nguyên,","Nam",2003,R.drawable.caonguyen);

        arraylistSV.add(gv1);
        arraylistSV.add(gv2);
        arraylistSV.add(gv3);
        arraylistSV.add(gv4);
        arraylistSV.add(gv5);
        arraylistSV.add(gv6);
        arraylistSV.add(gv7);
        arraylistSV.add(gv8);
        arraylistSV.add(gv9);
        arraylistSV.add(gv10);
        arraylistSV.add(gv11);
        arraylistSV.add(gv12);
        arraylistSV.add(gv13);
        arraylistSV.add(gv14);
        adapter=new CustomAdapter(this,arraylistSV);
        lvsv.setAdapter(adapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DELETE_STUDENT && resultCode == RESULT_OK) {
            //cập nhật lại listview
            adapter.notifyDataSetChanged();
        }
    }
}

