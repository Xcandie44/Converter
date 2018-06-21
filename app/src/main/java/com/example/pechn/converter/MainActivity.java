package com.example.pechn.converter;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    Button b1, b2, bp;
    Boolean point=false, tvc=true;
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
    int id1,id2;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        bp = findViewById(R.id.b_point);
       // et1 = findViewById(R.id.editText3);
        df = new DecimalFormat("#.############");
        df.setRoundingMode(RoundingMode.CEILING);
        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.BLACK);
        tv1.setText("0");
        tv2.setText("0");
      //  tv.setText(df.format(raw1));
        //raw1 = et1.getText();
        String r;
       // r = et1.getText().toString();
       // raw1 = Double.valueOf(r);
        id1=R.id.menu6;
        id2=R.id.menu6;
        //tv.setText(Double.toString(raw*(COEFFICIENT_KILOMETER/COEFFICIENT_DECIMETER)));
    }

    public void onTvClick(View v){
        switch(v.getId()){
            case R.id.textView1:
                raw1=0;
                point=false;
                tvc=true;
                tv1.setTextColor(Color.RED);
                break;
            case R.id.textView2:
                point=false;
                raw2=0;
                tvc=false;
                tv2.setTextColor(Color.RED);
                break;
        }
        if(tvc==true){
            tv1.setTextColor(Color.RED);
            tv2.setTextColor(Color.BLACK);
        }else {
            tv2.setTextColor(Color.RED);
            tv1.setTextColor(Color.BLACK);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.b0:
                input(0);
                count(id1,id2);
                break;
            case R.id.b_point:
                point=true;
                input(10);
                bp.setEnabled(false);
                break;
            case R.id.b_del:
                bp.setEnabled(true);
                ty=10;
                point=false;
                if(tvc==true) {
                    raw1=0;
                    tv1.setText("0");
                }else {
                    raw2=0;
                    tv2.setText("0");
                }
                count(id1,id2);
                break;
            case R.id.b1:
                input(1);
                count(id1,id2);
                break;
            case R.id.b2:
                input(2);
                count(id1,id2);
                break;
            case R.id.b3:
                input(3);
                count(id1,id2);
                break;
            case R.id.b4:
                input(4);
                count(id1,id2);
                break;
            case R.id.b5:
                input(5);
                count(id1,id2);
                break;
            case R.id.b6:
                input(6);
                count(id1,id2);
                break;
            case R.id.b7:
                input(7);
                count(id1,id2);
                break;
            case R.id.b8:
                input(8);
                count(id1,id2);
                break;
            case R.id.b9:
                input(9);
                count(id1,id2);
                break;
        }
    }

    public void input(double number) {
        if (tvc == true) {
            if (number != 10) {
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
                tv1.setText(df.format(raw1));
            } else tv1.setText(df.format(raw1) + ",");
        } else if (tvc == false) {
            if (number != 10) {
                if (point == false) {
                    if (t != 1) {
                        raw2 *= 10;
                        raw2 += number;
                    } else raw2 += number;
                } else if (t != 1) {
                    number /= ty;
                    raw2 += number;
                    ty *= 10;
                }
                t++;
                tv2.setText(df.format(raw2));
            } else
                tv2.setText(df.format(raw2) + ",");
        }

    }

    public void onClick1(View v){
        showPopupMenu1(v);
    }

    public void onClick2(View v){
        showPopupMenu2(v);
    }

    public void count(int id_1 ,int id_2 ){
        double coeff1=0,coeff2=0,coeff3=0;
        raw3=0;
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
        if(tvc==false){
            coeff3=coeff2/coeff1;
            raw3=raw2*coeff3;
            tv1.setText(df.format(raw3));
        }else {
            coeff3=coeff1/coeff2;
            raw3=raw1*coeff3;
            tv2.setText(df.format(raw3));
        }
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
}
