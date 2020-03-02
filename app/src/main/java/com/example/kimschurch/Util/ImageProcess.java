package com.example.kimschurch.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class ImageProcess {

    //비트맵 스트링 변환
    public static String bitmapToString(Bitmap bitmap) {

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);    //bitmap compress
        byte [] arr=baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }

    //비트맵 사이즈
    public static Bitmap bitmapSize(Bitmap bitmap, int size) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, size, height / (width / size), true);
    }




    //이미지 회전
    public static Bitmap rotateImage(Bitmap src, float degree) {
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }








    // 서클이미지 로드
    public static class LoadCircleImage extends AsyncTask<String,Void, Bitmap>{
        de.hdodenhof.circleimageview.CircleImageView imageView;
        public LoadCircleImage(de.hdodenhof.circleimageview.CircleImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String pnum = strings[0];
            String urlLink = "http://112.186.116.16:6011/KimsChurch/Upload/" + pnum + ".jpg";
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

    //일반이미지 로드
    public static class LoadImage extends AsyncTask<String,Void, Bitmap>{
        ImageView imageView;
        public LoadImage (ImageView ivResult){
            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String pnum = strings[0];
            String urlLink = "http://112.186.116.16:6011/KimsChurch/Upload/" + pnum + ".jpg";
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