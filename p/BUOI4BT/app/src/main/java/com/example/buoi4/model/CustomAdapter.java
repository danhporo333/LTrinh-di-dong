package com.example.buoi4.model;


import static android.app.Activity.RESULT_OK;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.buoi4.MainDetailActivity;
import com.example.buoi4.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private static ArrayList<SinhVien> arrsv;
    private final Activity context;
    private static final int REQUEST_CODE_DELETE_STUDENT = 1;
    public CustomAdapter(Activity context, ArrayList<SinhVien> arrsv){
        super(context, R.layout.item_sv, arrsv);
        this.context=context;
        this.arrsv=arrsv;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_sv, null,true);
        TextView txtmasv = rowView.findViewById(R.id.txtmasv);
        ImageView imvanhsv = (ImageView) rowView.findViewById(R.id.imgsv);
        TextView txttensv = (TextView) rowView.findViewById(R.id.txttensv);
        TextView txtgioitinh = (TextView) rowView.findViewById(R.id.txtgt);
        SinhVien sv = arrsv.get(position);
        txtmasv.setText(sv.getMasv());
        txttensv.setText(sv.getTensv());
        txtgioitinh.setText(sv.getGioitinh());
        imvanhsv.setImageResource(sv.getAnhsv());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,MainDetailActivity.class);
                in.putExtra("position",position);
                in.putExtra("Masv",sv.getMasv());
                in.putExtra("Tensv",sv.getTensv());
                in.putExtra("Gt",sv.getGioitinh());
                in.putExtra("Anhsv",sv.getAnhsv());
                in.putExtra("Namsinh",sv.getNamsinh());
                context.startActivityForResult(in, REQUEST_CODE_DELETE_STUDENT);
            }
        });

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Xác nhận");
                b.setMessage("Bạn có đồng ý xóa sinh viên "+sv.getTensv()+" không ?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        arrsv.remove(sv);
                        notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog al = b.create();
                al.show();
                return false;
            }
        });
        return rowView;
    };
    public static void Update(int position, Activity Context){
        SinhVien sv = arrsv.get(position);
        AlertDialog.Builder b = new AlertDialog.Builder(Context);

        b.setTitle("Xác nhận");
        b.setMessage("Bạn có đồng ý xóa sinh viên "+sv.getTensv()+" không ?");

        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                arrsv.remove(position);
                Context.setResult(RESULT_OK);
                Context.finish();
            }
        });

        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog al = b.create();

        al.show();
    }
}

