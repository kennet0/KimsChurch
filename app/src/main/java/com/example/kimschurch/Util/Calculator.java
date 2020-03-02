package com.example.kimschurch.Util;

import android.icu.util.ChineseCalendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Calculator {
    public static String calAge(String birthday){
        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        int subBirthday = Integer.parseInt(birthday.substring(0,4));
        int year = calendar.get(Calendar.YEAR);
        String age = Integer.toString(year - subBirthday + 1);
        return age;
    }

    public static String calWeek(String date){
        String[] splitDate = date.split("/");

        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month-1,day);
        int thisWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        return year + "." + month + " " + thisWeek + "주차";
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String Lunar2Solar(String birthday) {
        String yyyymmdd = birthday.replace("-",""); ;
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        if (yyyymmdd == null)
            return "";

        String date = yyyymmdd.trim();
        if (date.length() != 8) {
            if (date.length() == 4)
                date = date + "0101";
            else if (date.length() == 6)
                date = date + "01";
            else if (date.length() > 8)
                date = date.substring(0, 8);
            else
                return "";
        }

        cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0, 4)) + 2637);
        cc.set(ChineseCalendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
        cc.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

        cal.setTimeInMillis(cc.getTimeInMillis());

        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);

        StringBuffer ret = new StringBuffer();
        ret.append(String.format("%04d", y));
        ret.append(String.format("%02d", m));
        ret.append(String.format("%02d", d));

        String lunarBirthday = ret.toString();
        String year = lunarBirthday.substring(0,4);
        String month = lunarBirthday.substring(4,6);
        String day = lunarBirthday.substring(6,8);

        return year + "-" + month + "-" + day;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String Solar2Lunar(String birthday) {

        String yyyymmdd = birthday.replace("-","");
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        if (yyyymmdd == null) { return ""; }

        String date = yyyymmdd.trim() ;
        if( date.length() != 8 ) {
            if( date.length() == 4 )
                date = date + "0101" ;
            else if( date.length() == 6 )
                date = date + "01" ;
            else if( date.length() > 8 )
                date = date.substring(0,8) ;
            else
                return "" ;
        }

        cal.set( Calendar.YEAR, Integer.parseInt(date.substring(0,4)) ) ;
        cal.set( Calendar.MONTH, Integer.parseInt(date.substring(4,6))-1 ) ;
        cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)) ) ;

        cc.setTimeInMillis( cal.getTimeInMillis() ) ;

        // ChinessCalendar.YEAR 는 1~60 까지의 값만 가지고 ,
        // ChinessCalendar.EXTENDED_YEAR 는 Calendar.YEAR 값과 2637 만큼의 차이를 가진다.
        int y = cc.get(ChineseCalendar.EXTENDED_YEAR)-2637 ;
        int m = cc.get(ChineseCalendar.MONTH)+1 ;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH) ;

        StringBuffer ret = new StringBuffer() ;
        if( y < 1000 )          ret.append( "0" ) ;
        else if( y < 100 )      ret.append( "00" ) ;
        else if( y < 10 )       ret.append( "000" ) ;
        ret.append( y ) ;

        if( m < 10 ) ret.append( "0" ) ;
        ret.append( m ) ;

        if( d < 10 ) ret.append( "0" ) ;
        ret.append( d ) ;

        String solarBirthday = ret.toString();
        String year = solarBirthday.substring(0,4);
        String month = solarBirthday.substring(4,6);
        String day = solarBirthday.substring(6,8);

        return year + "-" + month + "-" + day;
    }

}
