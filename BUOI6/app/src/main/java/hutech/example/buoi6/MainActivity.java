package hutech.example.buoi6;

import static android.Manifest.permission_group.CAMERA;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hutech.example.buoi6.database.DBHeperDatabase;

public class MainActivity extends AppCompatActivity {
    EditText edtbookid, edtbookname, editsotrang, editgia, editmota;
    Button btnadd, btnxoa,btnedit, btnvedau, btnveke,btnvetruoc,btnvecuoi,btnlamlai,btnxemlist;
    DBHeperDatabase dbh;
    Cursor cursor;
    ImageView imgbook;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    Bitmap myBitmap;
    Uri picUri;
    String anhbook = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //database
        dbh = new DBHeperDatabase(this);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Thông tin sách");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        edtbookid = findViewById(R.id.edtbookid);
        edtbookname = findViewById(R.id.edtbookname);
        editsotrang = findViewById(R.id.editsotrang);
        editgia = findViewById(R.id.editgia);
        editmota = findViewById(R.id.editmota);
        //////////////////////////////// button
        btnadd = findViewById(R.id.btnadd);
        imgbook = findViewById(R.id.imgbook);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String mabook = edtbookid.getText().toString();
                    String ten = edtbookname.getText().toString();
                    String sotrang = editsotrang.getText().toString();
                    String gia = editgia.getText().toString();
                    String mota = editmota.getText().toString();
                    String str="";
                    if( myBitmap!=null){
                        str= BitMapToString(myBitmap);
                        Log.d("anhchuoi",str);
                    }else {
                        str="";
                    }
                    String sql = "insert into BOOKS values('" + mabook + "','" + ten + "','" + sotrang + "','" + gia + "','" + mota + "','')";

                    try {
                        SQLiteDatabase db = dbh.ketnoiWrite();
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), ListBookActivity.class);
                        startActivity(in);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ghi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

        });




        btnxoa = findViewById(R.id.btnxoa);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mabook = edtbookid.getText().toString();
                String ten = edtbookname.getText().toString();
                showDialogconfirm(ten,mabook);

            }
        });
        btnedit = findViewById(R.id.btnedit);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mabook = edtbookid.getText().toString();
                String ten = edtbookname.getText().toString();
                String sotrang = editsotrang.getText().toString();
                String gia = editgia.getText().toString();
                String mota = editmota.getText().toString();
                String str="";
                if( myBitmap!=null){
                    str= BitMapToString(myBitmap);
                    Log.d("anhchuoi",str);
                }else {
                    str="";
                }
                if(mabook.equals("") || ten.equals("")){
                    Toast.makeText(getApplicationContext(), "thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else {
                    String sql = "update books set BookName='"+ten+"',Page='"+sotrang+"',Price='"+gia+"',Description='"+mota+"',bookimage='"+str+"' where bookid='"+mabook+"'";
                    try {
                        SQLiteDatabase db = dbh.ketnoiWrite();
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Đã sửa thành công", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), ListBookActivity.class);
                        startActivity(in);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "câp nhật thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnvedau = findViewById(R.id.btnvedau);
        btnvedau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor.moveToFirst();
                    HienThiGiaoDien(cursor);
                } catch (Exception e) {

                }
            }
        });
        btnveke = findViewById(R.id.btnveke);
        btnveke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor.moveToNext();
                    HienThiGiaoDien(cursor);
                } catch (Exception e) {

                }
            }
        });
        btnvetruoc = findViewById(R.id.btnvetruoc);
        btnvetruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor.moveToPrevious();
                    HienThiGiaoDien(cursor);
                } catch (Exception e) {

                }
            }
        });
        btnvecuoi = findViewById(R.id.btnvecuoi);
        btnvecuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToLast();
                HienThiGiaoDien(cursor);
            }
        });
        btnlamlai = findViewById(R.id.btnlamlai);
        btnlamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtbookid.requestFocus();
                edtbookid.setText("");
                edtbookname.setText("");
                editsotrang.setText("");
                editgia.setText("");
                editmota.setText("");
            }
        });
        btnxemlist = findViewById(R.id.btnxemlist);
        btnxemlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),ListBookActivity.class);
                //gửi 300 từ lisview
                startActivityForResult(in,300);
            }
        });
//        hienthidongdautien();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == R.id.logout) {
            SharedPreferences settings = getSharedPreferences("RememberLogin", MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(in);
        } else if (item.getItemId() == R.id.camera) {
            String mabook = edtbookid.getText().toString();
            String ten = edtbookname.getText().toString();
            if(!mabook.equals("") || !ten.equals("") ){
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                permissions.add(CAMERA);
                permissionsToRequest = findUnAskedPermissions(permissions);
                startActivityForResult(getPickImageChooserIntent(), 200);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (permissionsToRequest.size() > 0)
                        requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                }
            } else {
                Toast.makeText(getApplicationContext(), "vui lòng điền dữ liệu trước khi chụp hình", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogconfirm(String s, String bookid){
        //Tạo đối tượng
        AlertDialog.Builder b = new AlertDialog.Builder(this);
//Thiết lập tiêu đề
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có đồng ý xóa mục  "+s+" này không ?");
// Nút Ok
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                String sql="delete from books where bookid='"+bookid+"'";
                try {
                    SQLiteDatabase    db1 = dbh.ketnoiisRead();
                    db1.execSQL(sql);
                    cursor = db1.rawQuery("SELECT * FROM BOOKS",null);
                    db1.close();
                    Toast.makeText(getApplicationContext(),"xóa Thành công", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(),ListBookActivity.class);
                    startActivity(in);
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Không Thành công", Toast.LENGTH_LONG).show();

                }
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

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }

        }
        allIntents.add(0,captureIntent);

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);

        }



        Intent chooserIntent = Intent.createChooser(captureIntent, "Select source");
        //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, captureIntent);

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));
        Log.v("allIntents",""+allIntents.size());
        return chooserIntent;
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }
    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }
    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    // nhận hình ảnh trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "requestCode: " + requestCode + ", resultCode: " + resultCode);
        Bitmap bitmap;
        if (resultCode == Activity.RESULT_OK) {
            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    imgbook.setImageBitmap(myBitmap);
                    String chuoi = BitMapToString(myBitmap);
                    Log.d("chuoimahoa",chuoi);
                    anhbook =chuoi;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bitmap = (Bitmap) data.getExtras().get("data");
                myBitmap = bitmap;
                String chuoi = BitMapToString(myBitmap);
                Log.d("chuoimahoa",chuoi);
                imgbook.setImageBitmap(myBitmap);
            }
        }
        //nhan duoc 300 hien thi du liệu
        if (requestCode == 300 && resultCode == 200  && data!=null) {
            // Nhận dữ liệu từ Intent
            String masach = data.getStringExtra("MESSAGE");
            Log.d("onActivityResult", "Received masach: " + masach);

            SQLiteDatabase db = dbh.ketnoiisRead();
            cursor = db.rawQuery("select * from books", null);
            try {
                Cursor cs = db.rawQuery("select * from books where bookid='" + masach + "'", null);
                if (cs.moveToNext()) {
                    HienThiGiaoDien(cs);
                }
            } catch (Exception ex) {

            }
        }
    }

    public Uri getPickImageResultUri(Intent data) {
        Uri outputFileUri = null;
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return outputFileUri;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }


    private void hienthidongdautien(){
        SQLiteDatabase db = dbh.ketnoiisRead();
        cursor = db.rawQuery("SELECT * FROM BOOKS",null);
        try {
            cursor.moveToFirst();
            HienThiGiaoDien(cursor);
//                    edtbookid.setText(anhbook);
        } catch (Exception e) {

        }
    }

    private void HienThiGiaoDien(Cursor cursor){
        String mabook = cursor.getString(0);
        String tens = cursor.getString(1);
        String sotrang = cursor.getString(2);
        String gia = cursor.getString(3);
        String mota = cursor.getString(4);
        String anhbook = cursor.getString(5);
        edtbookid.setText(mabook);
        edtbookname.setText(tens);
        editsotrang.setText(sotrang);
        editgia.setText(gia);
        editmota.setText(mota);
        imgbook.setImageBitmap(StringToBitMap(anhbook));
        Log.d("masvv", "Received masv: " + mabook);
    }
}