package com.example.buoi4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buoi4.model.CustomAdapter;
import com.example.buoi4.model.SinhVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_DELETE_STUDENT = 1;
    private static ArrayList<SinhVien> arrlistSV;
    public static CustomAdapter adapter;
    ListView lvsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lvsv = findViewById(R.id.lvsv);
        arrlistSV = new ArrayList<>();

        SinhVien gv1 = new SinhVien("sv001", "Nguyễn Hùng Dũng", "Nam", 2003, R.drawable.dung);
        SinhVien gv2 = new SinhVien("sv002", "Lê Trung Hiếu", "Nam", 2003, R.drawable.hieu);
        SinhVien gv3 = new SinhVien("sv003", "Trần Chí Thanh", "Nam", 2003, R.drawable.thanh);
        SinhVien gv4 = new SinhVien("sv004", "Lê Trọng Phi", "Nam", 2002, R.drawable.phi);
        SinhVien gv5 = new SinhVien("sv005", "Phạm Thành Nguyễn", "Nam", 2003, R.drawable.thanhnguyen);
        SinhVien gv6 = new SinhVien("sv006", "Nguyễn Nhật Kha", "Nam", 2003, R.drawable.kha);
        SinhVien gv7 = new SinhVien("sv007", "Nguyễn Đăng Khoa", "Nam", 2003, R.drawable.khoa);
        SinhVien gv8 = new SinhVien("sv008", "Phạm Minh Đăng Khoa", "Nam", 2003, R.drawable.unknown);
        SinhVien gv9 = new SinhVien("sv009", "Bùi Thị Kim Ngọc", "Nữ", 2003, R.drawable.unknown);
        SinhVien gv10 = new SinhVien("sv010", "Nguyễn Minh Thư", "Nữ", 2002, R.drawable.unknown);
        SinhVien gv11 = new SinhVien("sv011", "Nguyễn Ngọc Quỳnh", "Nữ", 2003, R.drawable.unknown);
        SinhVien gv12 = new SinhVien("sv012", "Trương Thu Thảo", "Nữ", 2003, R.drawable.unknown);
        SinhVien gv13 = new SinhVien("sv013", "Đinh Yang Phiệt", "Nam", 2003, R.drawable.phiet);
        SinhVien gv14 = new SinhVien("sv014", "Cao Nguyên", "Nam", 2003, R.drawable.nguyen);
        SinhVien gv15 = new SinhVien("sv015", "Dương Tất Danh", "Nam", 2003, R.drawable.danh);

        arrlistSV.add(gv1);
        arrlistSV.add(gv2);
        arrlistSV.add(gv3);
        arrlistSV.add(gv4);
        arrlistSV.add(gv5);
        arrlistSV.add(gv6);
        arrlistSV.add(gv7);
        arrlistSV.add(gv8);
        arrlistSV.add(gv9);
        arrlistSV.add(gv10);
        arrlistSV.add(gv11);
        arrlistSV.add(gv12);
        arrlistSV.add(gv13);
        arrlistSV.add(gv14);
        arrlistSV.add(gv15);

        adapter = new CustomAdapter(this, arrlistSV);
        lvsv.setAdapter(adapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DELETE_STUDENT && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }
}