package com.example.buoi5.model;


import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.buoi5.MainDetailActivity;
import com.example.buoi5.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private static ArrayList<SinhVien> arrsv;
    private final Activity context;
    int lastPosition = -1;
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

        ///////
        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,MainDetailActivity.class);
                in.putExtra("position", position);
                in.putExtra("objectSV",sv);
                context.startActivityForResult(in, 1);
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
                arrsv.remove(sv);
                Context.setResult(2);
                Context.finish();
            }
        });

        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog al = b.create();

        al.show();
    }
}

