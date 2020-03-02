package com.example.kimschurch.Util;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.PhoneNumberUtils;

import androidx.core.app.ActivityCompat;

import com.example.kimschurch.DTO.MemberDTO;

public class Etc {

    public static void phoneCall(final Context context, final MemberDTO memberDTO){

        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);

        AlertDialog showdialog = new AlertDialog.Builder(context)
                .setTitle(memberDTO.getName())
                .setMessage(memberDTO.getPhone() + " 통화하시겠습니까?")
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PhoneNumberUtils.formatNumber(memberDTO.getPhone())));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    return;
                                }
                                context.startActivity(intent);
                            }
                        })
                .setNegativeButton(
                        "아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.dismiss();
                            }
                        }).create();
        showdialog.show();
    }

}
