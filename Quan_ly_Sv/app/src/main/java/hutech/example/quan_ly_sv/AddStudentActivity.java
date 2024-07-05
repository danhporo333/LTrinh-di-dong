package hutech.example.quan_ly_sv;

import static android.Manifest.permission_group.CAMERA;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
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
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hutech.example.quan_ly_sv.database.DBHeperDatabase;

public class AddStudentActivity extends AppCompatActivity {
    EditText edtmasv,edttensv,edtnamsinh;
    RadioButton rdnam,rdnu;
    Button btnluu, btnreset1;
    DBHeperDatabase dbh;
    Bitmap myBitmap;
    Uri picUri;
    String anhsv = "";
    ImageView imgsv;
    Cursor cursor;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        //database
        dbh = new DBHeperDatabase(this);
        Toolbar toolbar =  findViewById(R.id.toolbar_updatesv);
        toolbar.setTitle("Thêm sinh viên mới");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        edtmasv = findViewById(R.id.edtmasv);
        edttensv = findViewById(R.id.edttensv);
        edtnamsinh = findViewById(R.id.edtnamsinh);
        rdnam=findViewById(R.id.ranam);
        rdnu=findViewById(R.id.rdnu);
        btnluu=findViewById(R.id.btnupdate);
        btnreset1 = findViewById(R.id.btnreset1);
        imgsv = findViewById(R.id.imgsv);
        rdnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdnu.setChecked(false);
            }
        });
        rdnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdnam.setChecked(false);

            }
        });

        // bắt sự kiện nút lưu
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = edtmasv.getText().toString();
                String hovaten = edttensv.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                String gt = "Nam";
                if (rdnu.isChecked()) {
                    gt = "Nữ";
                }
                String str="";
                if( myBitmap!=null){
                    str= BitMapToString(myBitmap);
                    Log.d("anhchuoi",str);
                }else {
                    str="";
                }

                if (masv.isEmpty() || hovaten.isEmpty() || namsinh.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String sql = "insert into SINHVIEN values('" + masv + "','" + hovaten + "','" + gt + "','" + namsinh + "','"+str+"')";
                    try {
                        SQLiteDatabase db = dbh.ketNoiWrite();
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), ListSinhVienActivity.class);
                        startActivity(in);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(), "ghi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnreset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmasv.requestFocus();
                edtmasv.setText("");
                edttensv.setText("");
                edtnamsinh.setText("");
                rdnam.setChecked(false);
                rdnu.setChecked(false);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == R.id.camera) {
            String masv = edtmasv.getText().toString();
            String tensv = edttensv.getText().toString();
            if(!masv.equals("") || !tensv.equals("")) {
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
                    imgsv.setImageBitmap(myBitmap);
                    String chuoi = BitMapToString(myBitmap);
                    Log.d("chuoimahoa",chuoi);
                    anhsv = chuoi;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bitmap = (Bitmap) data.getExtras().get("data");
                myBitmap = bitmap;
                String chuoi = BitMapToString(myBitmap);
                Log.d("chuoimahoa",chuoi);
                imgsv.setImageBitmap(myBitmap);
            }
        }
    }


}