package com.example.kimschurch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ImageProcess {

    public String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);    //bitmap compress
        byte [] arr=baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
         
    }

//    public static void send2Server(File file){
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("files", "fname", RequestBody.create(MultipartBody.FORM, file))
//                .build();
//
//        Request request = new Request.Builder()
//                .url("http://112.186.116.16:6011/Upload.php")// Server URL 은 본인 IP를 입력
//                .post(requestBody)
//                .build();
//
//        Log.e("filename",file.getName());
//        OkHttpClient client = new OkHttpClient();
//        client.newCall(request)
//                .enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    Log.e("TEST : ", response.body().string());
//                    }
//                });
//
//        }

//    public static Bitmap getImageFromURL(String strImageURL){
//        Bitmap imgBitmap = null;
//
//        try {
//            URL url = new URL(strImageURL);
//            URLConnection conn = url.openConnection();
//            conn.connect();
//
//            int nSize = conn.getContentLength();
//            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(),nSize);
//            imgBitmap = BitmapFactory.decodeStream(bis);
//            bis.close();
//
//
//        }catch (Exception e){
//
//        }
//        return imgBitmap;
//    }

    public static class LoadImage extends AsyncTask<String,Void, Bitmap>{
        de.hdodenhof.circleimageview.CircleImageView imageView;
        public LoadImage (de.hdodenhof.circleimageview.CircleImageView ivResult){
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