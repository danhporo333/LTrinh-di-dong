package hutech.example.phuongtrinh;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainListViewActivity extends AppCompatActivity {
    private ListView lvItem;
    List<String> data;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_list_view);
        lvItem=findViewById(R.id.lvmenu);
        data = new ArrayList<>();
        data.add("ax+b=0");
        data.add("ax^2+bx+c=0");
        data.add("phép toán");
        data.add("Máy tính");
        data.add("Máy tính 1");
        data.add("Máy tính 2 ");
        Log.d("sizearry",""+data.size());
        Log.d("sizearry",""+data.get(1));
        Log.d("sizearry",""+data.remove(3));
        for (int i = 0;i< data.size();i++){
            Log.d("dataarry",""+data.get(i));
        }
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, data);

        lvItem.setAdapter(adapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Items", ""+data.get(position));
                if(position==0) {
                    //Mở cửa sổ (activity)
                    Intent in = new Intent(getApplicationContext(), MainActivityPTB1.class);
                    startActivity(in);
                }
                if(position==1) {
                    Intent in = new Intent(getApplicationContext(), MainActivityPTB2.class);
                    startActivity(in);
                }
                if(position==2) {
                    Intent in = new Intent(getApplicationContext(), MainActivityPhepToan.class);
                    startActivity(in);
                }
            }
        });
        //LongLick
        lvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogconfirm(data.get(position),position);
                return false;
            }
        });
    }
    private void showDialogconfirm(String s,int p){
        //Tạo đối tượng
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b.setTitle("Xác nhận");
        b.setMessage("Bạn có đồng ý xóa mục  "+s+" này không ?");
// Nút Ok
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Xóa 1 phần tử của ListView
                data.remove(p);
                //cập nhật lại listview
                adapter.notifyDataSetChanged();
            }
        });
//Nút Cancel
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
//Tạo dialog
        AlertDialog al = b.create();
//Hiển thị
        al.show();
    }
}
