package com.example.gagyi.test;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListApplications extends ListActivity {

    private PackageManager packageManager = null;
    private List<ApplicationInfo> appList = null;
    private AppAdapter listAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_applications);

        packageManager = getPackageManager();

        new LoadApplications().execute();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {


        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = appList.get(position);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("games");


        String key = UUID.randomUUID().toString();
        Game testGame = new Game(key,(String)app.loadLabel(packageManager),app.packageName,"Ez egy jatek","Ez egy random description");
        myRef.child(key).setValue(testGame);


        //converting app icon from drawable to bitmap
        Bitmap imageToUpload = convertToBitmap(app.loadIcon(packageManager),app.loadIcon(packageManager).getIntrinsicWidth(),app.loadIcon(packageManager).getIntrinsicHeight());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageToUpload.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = FirebaseStorage.getInstance().getReference(key).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });



        //Intent intent = new Intent(ListApplications.this,EducatorInterface.class);
        //startActivity(intent);
        finish();

        //for starting apps
        /*try{

            Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

            if(intent != null){

                startActivity(intent);
            }
        }catch (ActivityNotFoundException e){
            Toast.makeText(ListApplications.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(ListApplications.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }*/


    }

    public Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
        Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        drawable.setBounds(0, 0, widthPixels, heightPixels);
        drawable.draw(canvas);

        return mutableBitmap;
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list){

        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();

        for(ApplicationInfo info : list){

            try{
                if(packageManager.getLaunchIntentForPackage(info.packageName) != null)
                    appList.add(info);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return  appList;
    }

    private class LoadApplications extends AsyncTask<Void,Void,Void>{

        private ProgressDialog progressDialog = null;


        @Override
        protected Void doInBackground(Void... params) {
            appList = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            listAdapter = new AppAdapter(ListApplications.this,R.layout.list_item,appList);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setListAdapter(listAdapter);
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ListApplications.this,null,"Loading apps info");
            super.onPreExecute();
        }
    }
}
