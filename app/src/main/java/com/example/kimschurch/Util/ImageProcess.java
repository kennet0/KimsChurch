package com.example.kimschurch.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class ImageProcess {

    public String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);    //bitmap compress
        byte [] arr=baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
         
    }

    public static class LoadCircleImage extends AsyncTask<String,Void, Bitmap>{
        de.hdodenhof.circleimageview.CircleImageView imageView;
        public LoadCircleImage(de.hdodenhof.circleimageview.CircleImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);
        }
    }

    public static class LoadImage extends AsyncTask<String,Void, Bitmap>{
        ImageView imageView;
        public LoadImage (ImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);
        }
    }

}