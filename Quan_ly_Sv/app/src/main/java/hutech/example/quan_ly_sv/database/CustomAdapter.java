package hutech.example.quan_ly_sv.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hutech.example.quan_ly_sv.ListSinhVienActivity;
import hutech.example.quan_ly_sv.MainDeTailActivity;
import hutech.example.quan_ly_sv.R;
import hutech.example.quan_ly_sv.model.SinhVien;


public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private ArrayList<SinhVien> arrsv;
    private final Activity context;
    private int lastPosition = -1;

    public CustomAdapter(Activity context, ArrayList<SinhVien> arrsv) {
        super(context, R.layout.item_sv, arrsv);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.arrsv = arrsv;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_sv, null, true);
        View rowView2 = inflater.inflate(R.layout.activity_list_sinh_vien, null, true);
        SinhVien sv = arrsv.get(position);

        TextView txtmasv = rowView.findViewById(R.id.txtmasv);
        TextView txttensv = rowView.findViewById(R.id.txttensv);
        ImageView imganhsv = (ImageView) rowView.findViewById(R.id.imgSv);
        TextView textgt = (TextView) rowView.findViewById(R.id.txtgioitinh);

        TextView myTextView = rowView2.findViewById(R.id.txtnum);
        Button btnchitiet = rowView.findViewById(R.id.btnchitiet);


        txtmasv.setText(sv.getMasv());
        txttensv.setText(sv.getHovaten());
        textgt.setText(sv.getGioitinh());
        imganhsv.setImageBitmap(StringToBitMap(sv.getAnhsv()));
        btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, MainDeTailActivity.class);
                intent.putExtra("Object",sv);
                context.startActivity(intent);
//                context.finish();//finishing activity
                Log.d("CustomAdapter", "Sending MESSAGE: " + sv.getMasv());
            }
        });

        // nhấn giữ để xóa
        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sinh viên này?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa sinh viên từ cơ sở dữ liệu
                        DBHeperDatabase dbh = new DBHeperDatabase(context);
                        dbh.xoaSinhVien(sv.getMasv());
                        // Xóa sinh viên từ danh sách và cập nhật lại ListView
                        arrsv.remove(position);
                        notifyDataSetChanged();
//                        ((ListSinhVienActivity) context).updateStudentCount();

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng dialog và không làm gì
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        // bắt sự kiện nút xóa
        Button btnxoa = rowView.findViewById(R.id.btnxoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListSinhVienActivity.class);
                String masv = sv.getMasv();
                String tensv = sv.getHovaten();
                try {
                    String sqldelete = "delete from SINHVIEN where MaSV='"+masv+"'";
                    DBHeperDatabase dbh = new DBHeperDatabase(context);
                    SQLiteDatabase db = dbh.ketNoiWrite();
                    db.execSQL(sqldelete);
                    arrsv.remove(position);
                    notifyDataSetChanged();
                    ((ListSinhVienActivity) context).updateStudentCount();
                } catch (Exception e){

                }
            }
        });
        return rowView;
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}

