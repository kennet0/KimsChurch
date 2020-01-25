//package com.example.kimschurch;
//
//import android.graphics.Bitmap;
//import android.util.Base64;
//import android.util.Log;
//
//import java.io.ByteArrayOutputStream;
//import java.net.URLEncoder;
//
//public class BitMapToString {
//    public void BitMapToString(Bitmap bitmap){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
//        byte [] arr = baos.toByteArray();
//        String image = Base64.encodeToString(arr, Base64.DEFAULT);
//        String temp ="";
//        try {
//            temp="&imagedevice" + URLEncoder.encode(image, "utf-8");
//        }catch (Exception e){
//            Log.e("exception", e.toString());
//        }
//    }
//}
