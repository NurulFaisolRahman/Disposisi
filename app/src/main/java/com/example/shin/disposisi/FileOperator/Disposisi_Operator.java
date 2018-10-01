package com.example.shin.disposisi.FileOperator;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shin.disposisi.R;
import com.example.shin.disposisi.Server;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class Disposisi_Operator extends Fragment implements View.OnClickListener {

    View v;
    Button c_file, kirim;
    EditText nomor_surat,surat_dari,tanggal_surat,diterima_tanggal,nomor_agenda,perihal;
    TextView choose;
    RadioGroup radio;
    RadioButton radio_button;
    private static final int STORAGE_PERMISSION_CODE = 2342;
    private static final int PICK_FILE_REQUEST = 22;
    private Uri filePath;
    private Bitmap bitmap;
    ImageView image;
    private static final String UPLOAD_URL = Server.IP+"upload.php";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Disposisi_Operator() {


    }

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.disposisi_operator,container,false);
        c_file = v.findViewById(R.id.choose);
        kirim = v.findViewById(R.id.kirim);
        nomor_surat = v.findViewById(R.id.nomor_surat);
        surat_dari = v.findViewById(R.id.surat_dari);
        tanggal_surat = v.findViewById(R.id.tanggal_surat);
        diterima_tanggal = v.findViewById(R.id.diterima_tanggal);
        nomor_agenda = v.findViewById(R.id.nomor_agenda);
        perihal = v.findViewById(R.id.perihal);
        image = v.findViewById(R.id.image_file);
        radio = v.findViewById(R.id.radio);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radio_button= (RadioButton) v.findViewById(radioGroup.getCheckedRadioButtonId());
            }
        });

        requestStoragePermission();
        c_file.setOnClickListener(this);
        kirim.setOnClickListener(this);
        return v;

    }

    private void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission( getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return;
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==STORAGE_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Permession granted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getContext(), "Permession not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            }catch (IOException e){

            }
        }
    }

    private String getPath(Uri uri){
        Cursor cursor = getActivity().getContentResolver().query(uri, null,null,null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();
        cursor = getActivity().getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+
                " = ? ", new String[]{document_id},null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private void uploadImage(){
        String path = getPath(filePath);
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();
            //Creating a multi part request
            new MultipartUploadRequest(getContext(), uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("nomor_surat", nomor_surat.getText().toString()) //Adding text parameter to the request
                    .addParameter("surat_dari", surat_dari.getText().toString())
                    .addParameter("tanggal_surat", tanggal_surat.getText().toString())
                    .addParameter("diterima_tanggal", diterima_tanggal.getText().toString())
                    .addParameter("nomor_agenda", nomor_agenda.getText().toString())
                    .addParameter("sifat",radio_button.getText().toString())
                    .addParameter("perihal", perihal.getText().toString())
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(getContext(), exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == kirim){
            uploadImage();
            nomor_surat.setText("");
            surat_dari.setText("");
            tanggal_surat.setText("");
            diterima_tanggal.setText("");
            nomor_agenda.setText("");
            perihal.setText("");
        }
        if (v == c_file) {
            Toast.makeText(getContext(),"pilih gambar", Toast.LENGTH_SHORT).show();
            showFileChooser();
        }
    }
}



