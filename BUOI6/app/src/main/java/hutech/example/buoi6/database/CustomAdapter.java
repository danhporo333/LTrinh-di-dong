package hutech.example.buoi6.database;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hutech.example.buoi6.MainActivity;
import hutech.example.buoi6.R;
import hutech.example.buoi6.model.Book;

public class CustomAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> arrbook;
    private final Activity context;
    private int lastPosition = -1;

    public CustomAdapter(Activity context, ArrayList<Book> arrsv) {
        super(context, R.layout.item_book, arrsv);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.arrbook = arrsv;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_book, null, true);
        Book book = arrbook.get(position);

        TextView txtmasach = rowView.findViewById(R.id.txtmasach);
        ImageView imgbook = (ImageView) rowView.findViewById(R.id.imganhsach);
        TextView txttensach = (TextView) rowView.findViewById(R.id.txttensach);
        TextView txtgia = (TextView) rowView.findViewById(R.id.txtgia);
        txtmasach.setText(arrbook.get(position).getBookid());
        txttensach.setText(arrbook.get(position).getBookname());
        txtgia.setText("" + arrbook.get(position).getBookprice());
        Button btnchitiet = rowView.findViewById(R.id.btnchitiet);

//        Log.d("chuoihinhanh",""+StringToBitMap(book.getAnhbook()));
        imgbook.setImageBitmap(StringToBitMap(book.getAnhbook()));
        btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra("MESSAGE",book.getBookid());
                context.setResult(200,intent);
                context.finish();//finishing activity
            }
        });

        Button btnxoa = rowView.findViewById(R.id.btnxoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , MainActivity.class);
                String masach = book.getBookid();
                String tensach = book.getBookname();
                try {
                    String sqldelete = "delete from BOOKS where BookID='"+masach+"'";
                    DBHeperDatabase dbh = new DBHeperDatabase(context);
                    SQLiteDatabase db = dbh.ketnoiWrite();
                    db.execSQL(sqldelete);
                    arrbook.remove(position);
                    notifyDataSetChanged();

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
    ;
}
