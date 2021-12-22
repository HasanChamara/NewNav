package com.example.templateproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InsertItem extends AppCompatActivity {

    EditText name, price;
    Button imgchoosebtn, insertbtn;
    ImageView img1;

    public static final String DBNAME ="ItemDB.db";

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

        init();

        sqLiteHelper = new SQLiteHelper(this, "ItemDB.db", null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Item (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR price VARCHAR, image BLOG)");

        imgchoosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        InsertItem.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY
                );
            }
        });

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sqLiteHelper.insertData(
                            name.getText().toString().trim(),
                            price.getText().toString().trim(),
                            ImageViewToByte(img1)
                    );

                    Toast.makeText(InsertItem.this, "Success", Toast.LENGTH_SHORT).show();

                }

                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    private byte[] ImageViewToByte(ImageView img1) {
        Bitmap bitmap = ((BitmapDrawable)img1.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte [] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galleryActivityResultLauncher.launch(intent);
                //startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(InsertItem.this, "You Don't Have Permition to Access!", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if (requestCode == REQUEST_CODE_GALLERY && requestCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                img1.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void init(){
        name = (EditText) findViewById(R.id.InsertName);
        price = (EditText) findViewById(R.id.InsertPrice);
        imgchoosebtn = (Button) findViewById(R.id.ImgChooseBtn);
        insertbtn = (Button) findViewById(R.id.InsertBtn);
        img1 = (ImageView) findViewById(R.id.img1);
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri imageUri = data.getData();

                        img1.setImageURI(imageUri);
                    }
                    else {
                        Toast.makeText(InsertItem.this, "Cancelled!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
    );

}