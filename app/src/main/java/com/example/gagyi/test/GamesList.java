package com.example.gagyi.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by zalan on 3/19/2018.
 */

public class GamesList extends ArrayAdapter<Game> {

    private Activity context;
    private List<Game> gamesList;

    public GamesList(Activity context, List<Game> gamesList){

        super(context,R.layout.game_list_item , gamesList);
        this.context = context;
        this.gamesList = gamesList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.game_list_item,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.game_app_name);
        TextView textViewPackage = (TextView)listViewItem.findViewById(R.id.game_app_package);
        final ImageView imageViewIcon = (ImageView) listViewItem.findViewById(R.id.game_app_icon);

        Game game = gamesList.get(position);

        textViewName.setText(game.getName());
        textViewPackage.setText(game.getDescription());
        //



        FirebaseStorage.getInstance().getReference().child("4f79db25-74f6-42af-b3db-ee89c869b937").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //imageViewIcon.setImageBitmap(getBitmapFromURL(uri.toString()));
                //new GetImageFromURL(imageViewIcon).execute(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        return listViewItem;
    }

    /*
    public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {

        ImageView imgV;

        public GetImageFromURL(ImageView imgV){
            this.imgV = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String URL = strings[0];
            Bitmap bitmap = null;
            try{
                InputStream srt = new java.net.URI(URL).getPath();
                bitmap = BitmapFactory.decodeStream(srt);
            }catch (Exception E){
                E.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }*/
}
