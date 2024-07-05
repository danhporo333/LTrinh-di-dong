package hutech.example.buoi4;

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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import hutech.example.buoi4.model.SinhVien;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private static ArrayList<SinhVien> arrsv;
    private final Activity context;
    private static final int DELETE_STUDENT = 1;
    int lastPosition = -1;
    public CustomAdapter(Activity context, ArrayList<SinhVien> arrsv) {
        super(context, R.layout.item_sv,arrsv);
        this.context=context;
        this.arrsv = arrsv;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_sv, null,true);
        TextView txtmasv = rowView.findViewById(R.id.txtmasv);
        TextView txttensv = rowView.findViewById(R.id.txttensv);
        ImageView imganhsv = (ImageView) rowView.findViewById(R.id.imgSv);
        TextView textgt = (TextView) rowView.findViewById(R.id.txtgioitinh);
        SinhVien sv = arrsv.get(position);
        txtmasv.setText(sv.getMasv());
        txttensv.setText(sv.getHovaten());
        textgt.setText(sv.getGioitinh());
        imganhsv.setImageResource(sv.getAnhsv());

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, MainDeTaiActivity.class);
                in.putExtra("position",position);//được sử dụng để xác định vị trí của một mục được nhấn trong
                in.putExtra("MASV",sv.getMasv());
                in.putExtra("TENSV",sv.getHovaten());
                in.putExtra("GIOITINH",sv.getGioitinh());
                in.putExtra("ANHSV",sv.getAnhsv());
                in.putExtra("NAMSINH",sv.getNamsinh());
                context.startActivityForResult(in,DELETE_STUDENT);
            }
        });
        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Thông Báo");
                b.setMessage("Bạn chắc chắn muốn xóa sinh viên viên" +sv.getHovaten()+"này không");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        arrsv.remove(sv);//xóa sinh viên trong mảng arrsv
                        Toast.makeText(context, "Xóa sinh viên thành công", Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();//cập nhật lại mảng
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
    public static void Update(int position, Activity context) {
        SinhVien sv = arrsv.get(position);
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có đồng ý xóa sinh viên "+sv.getHovaten()+" không ?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                arrsv.remove(position);
                context.setResult(RESULT_OK);
                Toast.makeText(context, "Xóa sinh viên thành công", Toast.LENGTH_LONG).show();
                context.finish();
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
