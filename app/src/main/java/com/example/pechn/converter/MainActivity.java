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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button b1, b2, bp;
    Boolean point=false, lenght=true;
    TextView tv1,tv2;
    double raw1 = 0,raw2=0,raw3=0;
    int t=1,ty=10;
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
    int id1,id2,themeId=0;
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
            }
        }else {
            Intent i = getIntent();
            themeId = i.getIntExtra("Theme", 0);
            if (themeId == 1) {
                setTheme(R.style.Dark);
            }
            editor.putInt(THEME_PREFERENCES,themeId);
            editor.apply();
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        bp = findViewById(R.id.b_point);
        df = new DecimalFormat("#.############");
        df.setRoundingMode(RoundingMode.CEILING);
        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.BLACK);
        tv1.setText("0");
        tv2.setText("0");
        String r;
        id1=R.id.menu6;
        id2=R.id.menu6;
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        soundPool.load(this,R.raw.click,0);

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
        if(b) {
            soundPool.play(1, 1, 1, 1, 0, 1);
        }
        switch (v.getId()){
            case R.id.b0:
                input("0");
                count(id1,id2);
                break;
            case R.id.b_point:
                input(",");
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

    public void onLongClick(View v){
        switch (v.getId()){
            case R.id.b_del:
                Toast.makeText(this,"del",Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelClick(){
        lenght=true;
        bp.setEnabled(true);
        ty=10;
        point=false;
        if(stroka.length()==1) {
            stroka = "";
            tv1.setText("0");
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
            if (number.equals(",")) {
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
            /*if (number != 10) {
                if (point == false) {
                    if (t != 1) {
                        raw1 *= 10;
                        raw1 += number;
                    } else raw1 += number;
                } else if (t != 1) {
                    number /= ty;
                    raw1 += number;
                    ty *= 10;
                }
                t++;
                tv1.setText(String.valueOf(raw1));
            } else tv1.setText(df.format(raw1) + ",");*/
    }

    public void Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.
                setMessage("Вы ввели слишком много символов")
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

    public void count(int id_1 ,int id_2 ){
        double coeff1=0,coeff2=0,coeff3=0;
        raw3=0;String raw = null;
        switch (id_1){
            case R.id.menu1:
                coeff1=COEFFICIENT_NANOMETER;
                break;
            case R.id.menu2:
                coeff1=COEFFICIENT_MICROMETER;
                break;
            case R.id.menu3:
                coeff1=COEFFICIENT_MILLIMETER;
                break;
            case R.id.menu4:
                coeff1=COEFFICIENT_CENTIMETER;
                break;
            case R.id.menu5:
                coeff1=COEFFICIENT_DECIMETER;
                break;
            case R.id.menu6:
                coeff1=COEFFICIENT_METER;
                break;
            case R.id.menu7:
                coeff1=COEFFICIENT_KILOMETER;
                break;
            case R.id.menu8:
                coeff1=COEFFICIENT_INCH;
                break;
            case R.id.menu9:
                coeff1=COEFFICIENT_FOOT;
                break;
            case R.id.menu10:
                coeff1=COEFFICIENT_YARD;
                break;
            case R.id.menu11:
                coeff1=COEFFICIENT_MILE;
                break;
            case R.id.menu12:
                coeff1=COEFFICIENT_SEAMILE;
                break;
        }
        switch (id_2){
            case R.id.menu1:
                coeff2=COEFFICIENT_NANOMETER;
                break;
            case R.id.menu2:
                coeff2=COEFFICIENT_MICROMETER;
                break;
            case R.id.menu3:
                coeff2=COEFFICIENT_MILLIMETER;
                break;
            case R.id.menu4:
                coeff2=COEFFICIENT_CENTIMETER;
                break;
            case R.id.menu5:
                coeff2=COEFFICIENT_DECIMETER;
                break;
            case R.id.menu6:
                coeff2=COEFFICIENT_METER;
                break;
            case R.id.menu7:
                coeff2=COEFFICIENT_KILOMETER;
                break;
            case R.id.menu8:
                coeff2=COEFFICIENT_INCH;
                break;
            case R.id.menu9:
                coeff2=COEFFICIENT_FOOT;
                break;
            case R.id.menu10:
                coeff2=COEFFICIENT_YARD;
                break;
            case R.id.menu11:
                coeff2=COEFFICIENT_MILE;
                break;
            case R.id.menu12:
                coeff2=COEFFICIENT_SEAMILE;
                break;
        }
            coeff3=coeff1/coeff2;
            raw3=raw1*coeff3;
            //raw = df.format(raw3);
            tv2.setText(df.format(raw3));

    }

    public void showPopupMenu1(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.first_menu);
        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item1) {

                        switch (item1.getItemId()) {
                            case R.id.menu1:
                                b1.setText("Нанометры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu2:
                                //raw2=0;
                                b1.setText("Микрометры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu3:
                                //raw2=0;
                                b1.setText("Миллиметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu4:
                                //raw2=0;
                                b1.setText("Сантиметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu5:
                                raw2=0;
                                b1.setText("Дециметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu6:
                                //raw2=0;
                                b1.setText("Метры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu7:
                                raw2=0;
                                b1.setText("Километры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu8:
                                //raw2=0;
                                b1.setText("Дюймы");
                              //  df = new DecimalFormat("#.######");
                                //df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu9:
                                //raw2=0;
                                b1.setText("Футы");
                                df = new DecimalFormat("#.######");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu10:
                               // raw2=0;
                                b1.setText("Ярды");
                               // df = new DecimalFormat("#.######");
                             //   df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu11:
                                //raw2=0;
                                b1.setText("Мили");
                                //df = new DecimalFormat("#.##################");
                               // df.setRoundingMode(RoundingMode.CEILING);
                                id1 = item1.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu12:
                                //raw2=0;
                                b1.setText("Морские мили");
                                //df = new DecimalFormat("#.###############");
                               // df.setRoundingMode(RoundingMode.CEILING);
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
        popupMenu.inflate(R.menu.second_menu);
        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item2) {

                        switch (item2.getItemId()) {
                            case R.id.menu1:
                               // raw2=0;
                                b2.setText("Нанометры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu2:
                                //raw2=0;
                                b2.setText("Микрометры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu3:
                                //raw2=0;
                                b2.setText("Миллиметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu4:
                                //raw2=0;
                                b2.setText("Сантиметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu5:
                                //raw2=0;
                                b2.setText("Дециметры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu6:
                                //raw2=0;
                                b2.setText("Метры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu7:
                                //raw2=0;
                                b2.setText("Километры");
                                df = new DecimalFormat("#.############");
                                df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu8:
                                //raw2=0;
                                b2.setText("Дюймы");
                               // df = new DecimalFormat("#.######");
                               // df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu9:
                                //raw2=0;
                                b2.setText("Футы");
                               // df = new DecimalFormat("#.######");
                               // df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu10:
                                //raw2=0;
                                b2.setText("Ярды");
                               // df = new DecimalFormat("#.######");
                                //df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu11:
                                //raw2=0;
                                b2.setText("Мили");
                               // df = new DecimalFormat("#.######");
                                //df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                                return true;
                            case R.id.menu12:
                                //raw2=0;
                                b2.setText("Морские мили");
                               // df = new DecimalFormat("#.######");
                               // df.setRoundingMode(RoundingMode.CEILING);
                                id2 = item2.getItemId();
                                count(id1,id2);
                            default:
                                return false;
                        }
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

}
