package com.example.pechn.converter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

import static com.example.pechn.converter.Settings.APP_PREFERENCES;
import static com.example.pechn.converter.Settings.THEME_PREFERENCES;

public class MainActivity extends AppCompatActivity {
    LinearLayout l1;
    Button b1_menu, b2_menu,b3_menu, bp,b_del,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Boolean point=false, lenght=true;
    TextView tv1,tv2;
    double raw1 = 0,raw2=0,raw3=0;
    int t=1,ty=10,color,color_gray,color_keyboard,text_color;
    EditText et1;
    final static double COEFFICIENT_METER = 1;
    final static double COEFFICIENT_KILOMETER = 1000;
    final static double COEFFICIENT_DECIMETER = 0.1;
    final static double COEFFICIENT_CENTIMETER = 0.01;
    final static double COEFFICIENT_MILLIMETER = 0.001;
    final static double COEFFICIENT_MICROMETER = 0.000001;
    final static double COEFFICIENT_NANOMETER = 0.000000001;
    final static double COEFFICIENT_INCH = 0.0254;
    final static double COEFFICIENT_FOOT = 0.3048;
    final static double COEFFICIENT_YARD = 0.9144;
    final static double COEFFICIENT_MILE = 1609.344;
    final static double COEFFICIENT_SEAMILE = 1852;
    int id1,id2,id3=R.id.lenght,themeId=0;
    DecimalFormat df;
    SharedPreferences preferences, langPreferences;
    SharedPreferences.Editor editor ,editorLang;
    public static final String APP_PREFERENCES = "settings";
    public static final String THEME_PREFERENCES = "theme";
    public static final String LANGUAGE_PREFERENCES = "lang";
    String lang, stroka="";
    Locale locale;
    SoundPool soundPool;
    boolean b;
    TouchListener tl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        langPreferences = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        if(preferences.contains("sound")){
            b = preferences.getBoolean("sound",true);
        }else {
            Intent i = getIntent();
            b = i.getBooleanExtra("sound",true);
            editor.putBoolean("sound",b);
            editor.apply();
        }
        if (langPreferences.contains(LANGUAGE_PREFERENCES)){
            lang = langPreferences.getString("lang", "default");
            if (lang.equals("default")) {
                lang = getResources().getConfiguration().locale.getCountry();
            }
        }else {
            Intent i = getIntent();
            lang = i.getStringExtra("lang");
            editorLang.putString(LANGUAGE_PREFERENCES,lang);
            editorLang.apply();
        }
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
        if(preferences.contains(THEME_PREFERENCES)){
            themeId = preferences.getInt(THEME_PREFERENCES,0);
            if(themeId==0){
                setTheme(R.style.Light);
            }else if(themeId==1){
                setTheme(R.style.Dark);
            }else if(themeId==2) {
                setTheme(R.style.Green);
            }
        }else {
            Intent i = getIntent();
            themeId = i.getIntExtra("Theme", 0);
            if (themeId == 1) {
                setTheme(R.style.Dark);
            }else if (themeId == 2) {
                setTheme(R.style.Green);
            }
            editor.putInt(THEME_PREFERENCES,themeId);
            editor.apply();
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().hide();
        l1 = findViewById(R.id.linear1);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b_del = findViewById(R.id.b_del);
        b1_menu = findViewById(R.id.button1);
        b2_menu = findViewById(R.id.button2);
        b3_menu = findViewById(R.id.button3);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        bp = findViewById(R.id.b_point);
        df = new DecimalFormat("#.############");
        df.setRoundingMode(RoundingMode.CEILING);
        tv1.setText(R.string.enter);
        tv2.setText("0");
        String r;
        id1=R.id.menu6;
        id2=R.id.menu6;
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundPool.load(this,R.raw.click,0);
        b_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                stroka="";
                raw1=0;
                tv1.setText(R.string.enter);
                tv2.setText("0");
                return false;
            }
        });
        TouchListener tl = new TouchListener();
        b1_menu.setOnTouchListener(tl);
        b2_menu.setOnTouchListener(tl);
        b3_menu.setOnTouchListener(tl);
        bp.setOnTouchListener(tl);
        b_del.setOnTouchListener(tl);
        b0.setOnTouchListener(tl);
        b1.setOnTouchListener(tl);
        b2.setOnTouchListener(tl);
        b3.setOnTouchListener(tl);
        b4.setOnTouchListener(tl);
        b5.setOnTouchListener(tl);
        b6.setOnTouchListener(tl);
        b7.setOnTouchListener(tl);
        b8.setOnTouchListener(tl);
        b9.setOnTouchListener(tl);
        if(themeId==0){
            text_color = getResources().getColor(R.color.dark);
            color_keyboard=getResources().getColor(R.color.Dark_white);
            color =getResources().getColor(R.color.blue);
            color_gray=getResources().getColor(R.color.gray);
            l1.setBackgroundColor(getResources().getColor(R.color.white));
            setButtonColor(color_keyboard,text_color,color);
        }else if(themeId==1){
            text_color = getResources().getColor(R.color.white);
            color_keyboard=getResources().getColor(R.color.dark);
            color =getResources().getColor(R.color.orange);
            color_gray=getResources().getColor(R.color.gray);
            l1.setBackgroundColor(getResources().getColor(R.color.black));
            setButtonColor(color_keyboard,text_color,color);

        }else if(themeId==2){
            text_color = getResources().getColor(R.color.dark);
            color_keyboard=getResources().getColor(R.color.Dark_white);
            color =getResources().getColor(R.color.green);
            color_gray=getResources().getColor(R.color.gray);
            l1.setBackgroundColor(getResources().getColor(R.color.white));
            setButtonColor(color_keyboard,text_color,color);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent = new Intent(this,Settings.class);
                finish();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.b0:
                input("0");
                count(id1,id2);
                break;
            case R.id.b_point:
                input(".");
                point=true;
                bp.setEnabled(false);
                break;
            case R.id.b_del:
                onDelClick();
                break;
            case R.id.b1:
                input("1");
                count(id1,id2);
                break;
            case R.id.b2:
                input("2");
                count(id1,id2);
                break;
            case R.id.b3:
                input("3");
                count(id1,id2);
                break;
            case R.id.b4:
                input("4");
                count(id1,id2);
                break;
            case R.id.b5:
                input("5");
                count(id1,id2);
                break;
            case R.id.b6:
                input("6");
                count(id1,id2);
                break;
            case R.id.b7:
                input("7");
                count(id1,id2);
                break;
            case R.id.b8:
                input("8");
                count(id1,id2);
                break;
            case R.id.b9:
                input("9");
                count(id1,id2);
                break;
        }
    }

    public void onDelClick(){
        lenght=true;
        bp.setEnabled(true);
        ty=10;
        point=false;
        if(stroka.length()==1) {
            stroka = "";
            tv1.setText("Введите число");
            tv2.setText("0");
        }else if(stroka==""){
        }else {
            stroka = stroka.substring(0, stroka.length() - 1);
            tv1.setText(stroka);
            raw1=Double.valueOf(stroka);
            count(id1, id2);
        }
    }

    public void input(String number) {
        if(lenght) {
            if(number.equals(".")&&stroka.length()==0){
                stroka = "0.";
                tv1.setText(stroka);
            }else if(bp.isEnabled()==true && stroka.length()==1 && stroka.equals("0")&& !number.equals(".")){
                stroka = "0." + number;
                tv1.setText(stroka);
                bp.setEnabled(false);
                raw1= Double.valueOf(stroka);
            }else if (number.equals(".")) {
                stroka = stroka + number;
                tv1.setText(stroka);
            } else {
                stroka = stroka + number;
                tv1.setText(stroka);
                raw1 = Double.valueOf(stroka);
            }
        }
        if(stroka.length()>=16){
            lenght=false;
            Alert();
        }
    }

    public void Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.
                setMessage(R.string.alert)
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();

    }

    public void onClick1(View v){
        showPopupMenu1(v);
    }

    public void onClick2(View v){
        showPopupMenu2(v);
    }

    public void onClick3(View v){
        showPopupMenu3(v);
    }

    public void count(int id_1 ,int id_2 ){
        double coeff1=0,coeff2=0,coeff3=0;
        int c;
        raw3=0;String raw = null;
        switch (id_1){
            case R.id.menu1:
                coeff1=0.000000001;
                break;
            case R.id.menu2:
                coeff1=0.000001;
                break;
            case R.id.menu3:
                coeff1=0.001;
                break;
            case R.id.menu4:
                coeff1=0.01;
                break;
            case R.id.menu5:
                coeff1=0.1;
                break;
            case R.id.menu6:
                coeff1=1;
                break;
            case R.id.menu7:
                coeff1=1000;
                break;
            case R.id.menu8:
                coeff1=0.0254;
                break;
            case R.id.menu9:
                coeff1=0.3048;
                break;
            case R.id.menu10:
                coeff1=0.9144;
                break;
            case R.id.menu11:
                coeff1=1609.344;
                break;
            case R.id.menu12:
                coeff1=1852;
                break;
            case R.id.milliliter:
                coeff1 = 1;
                break;
            case R.id.cc:
                coeff1 = 1;
                break;
            case R.id.liter:
                coeff1 = 1000;
                break;
            case R.id.cbm:
                coeff1 = 1000000;
                break;
        }
        switch (id_2){
            case R.id.menu1:
                coeff2=0.000000001;
                break;
            case R.id.menu2:
                coeff2=0.000001;
                break;
            case R.id.menu3:
                coeff2=0.001;
                break;
            case R.id.menu4:
                coeff2=0.01;
                break;
            case R.id.menu5:
                coeff2=0.1;
                break;
            case R.id.menu6:
                coeff2=1;
                break;
            case R.id.menu7:
                coeff2=1000;
                break;
            case R.id.menu8:
                coeff2=0.0254;
                break;
            case R.id.menu9:
                coeff2=0.3048;
                break;
            case R.id.menu10:
                coeff2=0.9144;
                break;
            case R.id.menu11:
                coeff2=1609.344;
                break;
            case R.id.menu12:
                coeff2=1852;
                break;
            case R.id.milliliter:
                coeff2 = 1;
                break;
            case R.id.cc:
                coeff2 = 1;
                break;
            case R.id.liter:
                coeff2 = 1000;
                break;
            case R.id.cbm:
                coeff2 = 1000000;
                break;
        }
            coeff3=coeff1/coeff2;
            raw3=raw1*coeff3;
            raw = df.format(raw3);
            tv2.setText(raw);

    }

    public void showPopupMenu1(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        switch (id3){
            case R.id.lenght:
                popupMenu.inflate(R.menu.first_menu);
                break;
            case R.id.amount:
                popupMenu.inflate(R.menu.second_menu);
                break;
        }
        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item1) {

                        switch (item1.getItemId()) {
                            case R.id.menu1:
                                b1_menu.setText(R.string.nanometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu2:
                                b1_menu.setText(R.string.micrometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu3:
                                b1_menu.setText(R.string.millimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu4:
                                b1_menu.setText(R.string.centimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu5:
                                b1_menu.setText(R.string.decimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu6:
                                b1_menu.setText(R.string.meters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu7:
                                raw2=0;
                                b1_menu.setText(R.string.kilometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu8:
                                b1_menu.setText(R.string.inches);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu9:
                                b1_menu.setText(R.string.foots);
                                df = new DecimalFormat("#.######");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu10:
                                b1_menu.setText(R.string.yards);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu11:
                                b1_menu.setText(R.string.miles);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu12:
                                b1_menu.setText(R.string.seamiles);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.milliliter:
                                b1_menu.setText(R.string.milliliter);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.cc:
                                b1_menu.setText(R.string.cubicCentimeter);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.liter:
                                b1_menu.setText(R.string.liter);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.cbm:
                                b1_menu.setText(R.string.cubicMeter);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.show();
    }

    public void showPopupMenu2(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        switch (id3){
            case R.id.lenght:
                popupMenu.inflate(R.menu.first_menu);
                break;
            case R.id.amount:
                popupMenu.inflate(R.menu.second_menu);
                break;
        }
        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item2) {

                        switch (item2.getItemId()) {
                            case R.id.menu1:
                                b2_menu.setText(R.string.nanometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu2:
                                b2_menu.setText(R.string.micrometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu3:
                                b2_menu.setText(R.string.millimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu4:
                                b2_menu.setText(R.string.centimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu5:
                                b2_menu.setText(R.string.decimeters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu6:
                                b2_menu.setText(R.string.meters);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu7:
                                b2_menu.setText(R.string.kilometers);
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu8:
                                b2_menu.setText(R.string.inches);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu9:
                                b2_menu.setText(R.string.foots);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu10:
                                b2_menu.setText(R.string.yards);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu11:
                                b2_menu.setText(R.string.miles);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu12:
                                b2_menu.setText(R.string.seamiles);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.milliliter:
                                b2_menu.setText(R.string.milliliter);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.cc:
                                b2_menu.setText(R.string.cubicCentimeter);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.liter:
                                b2_menu.setText(R.string.liter);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.cbm:
                                b2_menu.setText(R.string.cubicMeter);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.show();
    }

    public void showPopupMenu3(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.notaion_menu);
        popupMenu.
                setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item3) {
                        switch (item3.getItemId()) {
                            case R.id.lenght:
                                id3=item3.getItemId();
                                id1 = R.id.menu6;
                                id2 = R.id.menu6;
                                stroka="";
                                raw1=0;
                                tv1.setText(R.string.enter);
                                tv2.setText("0");
                                b3_menu.setText(R.string.lenght);
                                b1_menu.setText(R.string.meters);
                                b2_menu.setText(R.string.meters);
                                break;
                            case R.id.amount:
                                id3=item3.getItemId();
                                id1 = R.id.milliliter;
                                id2 = R.id.milliliter;
                                stroka="";
                                raw1=0;
                                tv1.setText(R.string.enter);
                                tv2.setText("0");
                                b3_menu.setText(R.string.amount);
                                b1_menu.setText(R.string.milliliter);
                                b2_menu.setText(R.string.milliliter);
                                break;
                        }
                        return false;
                    }
                });
        popupMenu.show();
    }

   @Override
    public void onConfigurationChanged(Configuration newConfig) {
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
        super.onConfigurationChanged(newConfig);
    }


    class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(b) {
                        soundPool.play(1, 1, 1, 1, 0, 1);
                    }
                    switch (v.getId()){
                        case R.id.button1:
                            b1_menu.setBackgroundColor(color_gray);
                            break;
                        case R.id.button2:
                            b2_menu.setBackgroundColor(color_gray);
                            break;
                        case R.id.button3:
                            b3_menu.setBackgroundColor(color_gray);
                            break;
                        case R.id.b0:
                            b0.setBackgroundColor(color_gray);
                            break;
                        case R.id.b_point:
                            bp.setBackgroundColor(color_gray);
                            break;
                        case R.id.b_del:
                            b_del.setBackgroundColor(color_gray);
                            break;
                        case R.id.b1:
                            b1.setBackgroundColor(color_gray);
                            break;
                        case R.id.b2:
                            b2.setBackgroundColor(color_gray);
                            break;
                        case R.id.b3:
                            b3.setBackgroundColor(color_gray);
                            break;
                        case R.id.b4:
                            b4.setBackgroundColor(color_gray);
                            break;
                        case R.id.b5:
                            b5.setBackgroundColor(color_gray);
                            break;
                        case R.id.b6:
                            b6.setBackgroundColor(color_gray);
                            break;
                        case R.id.b7:
                            b7.setBackgroundColor(color_gray);
                            break;
                        case R.id.b8:
                            b8.setBackgroundColor(color_gray);
                            break;
                        case R.id.b9:
                            b9.setBackgroundColor(color_gray);
                            break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    switch (v.getId()){
                        case R.id.button1:
                            if(themeId==1) {
                                b1_menu.setBackgroundColor(color_keyboard);
                            }else{
                                b1_menu.setBackgroundColor(color);
                            }
                            break;
                        case R.id.button2:
                            if(themeId==1) {
                                b2_menu.setBackgroundColor(color_keyboard);
                            }else{
                                b2_menu.setBackgroundColor(color);
                            }
                            break;
                        case R.id.button3:
                            if(themeId==1) {
                                b3_menu.setBackgroundColor(color_keyboard);
                            }else{
                                b3_menu.setBackgroundColor(color);
                            }
                            break;
                        case R.id.b0:
                            b0.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b_point:
                            bp.setBackgroundColor(color);
                            break;
                        case R.id.b_del:
                            b_del.setBackgroundColor(color);
                            break;
                        case R.id.b1:
                            b1.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b2:
                            b2.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b3:
                            b3.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b4:
                            b4.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b5:
                            b5.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b6:
                            b6.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b7:
                            b7.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b8:
                            b8.setBackgroundColor(color_keyboard);
                            break;
                        case R.id.b9:
                            b9.setBackgroundColor(color_keyboard);
                            break;
                    }
                    break;
            }
            return false;
        }
    }
    public void setButtonColor(int color_keyboard,int textColor, int color){
        if(themeId==1) {
            b1_menu.setBackgroundColor(color_keyboard);
            b2_menu.setBackgroundColor(color_keyboard);
            b3_menu.setBackgroundColor(color_keyboard);
        }else{
            b1_menu.setBackgroundColor(color);
            b2_menu.setBackgroundColor(color);
            b3_menu.setBackgroundColor(color);
        }
        b1_menu.setTextColor(textColor);
        b2_menu.setTextColor(textColor);
        b3_menu.setTextColor(textColor);
        tv1.setTextColor(textColor);
        tv2.setTextColor(textColor);
        b_del.setBackgroundColor(color);
        b_del.setTextColor(textColor);
        bp.setBackgroundColor(color);
        bp.setTextColor(textColor);
        b0.setBackgroundColor(color_keyboard);
        b0.setTextColor(textColor);
        b1.setBackgroundColor(color_keyboard);
        b1.setTextColor(textColor);
        b2.setBackgroundColor(color_keyboard);
        b2.setTextColor(textColor);
        b3.setBackgroundColor(color_keyboard);
        b3.setTextColor(textColor);
        b4.setBackgroundColor(color_keyboard);
        b4.setTextColor(textColor);
        b5.setBackgroundColor(color_keyboard);
        b5.setTextColor(textColor);
        b6.setBackgroundColor(color_keyboard);
        b6.setTextColor(textColor);
        b7.setBackgroundColor(color_keyboard);
        b7.setTextColor(textColor);
        b8.setBackgroundColor(color_keyboard);
        b8.setTextColor(textColor);
        b9.setBackgroundColor(color_keyboard);
        b9.setTextColor(textColor);
    }

}
