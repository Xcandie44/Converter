package com.example.pechn.converter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    final String[] arr1 = new String[]{"Тема","Язык","Звук"};
    final String[] arr2 = new String[]{"Тема приложения","Необходимо полностью перезапустить\nприложение для принятия изменений","Звук нажатия на клавиатуру"};
    ListView lv1,lv2;
    private CustomAdapter1 adapter1;
    private CustomAdapter2 adapter2;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static final String APP_PREFERENCES = "settings";
    public static final String THEME_PREFERENCES = "theme";
    RadioGroup radioGroup;
    int savedRadioIndex,themeId=0;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(THEME_PREFERENCES)){
            themeId = preferences.getInt(THEME_PREFERENCES,0);
            if(themeId==0){
                setTheme(R.style.Light);
            }else if(themeId==1){
                setTheme(R.style.Dark);
            }
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        lv1 = findViewById(R.id.first_list);
        lv2 = findViewById(R.id.second_list);
        getSupportActionBar().hide();
        radioGroup = findViewById(R.id.radiogroup);
        rb = findViewById(R.id.dark);
        ArrayList<SettingsList1> arrayList1 = new ArrayList<>();
        arrayList1.add(new SettingsList1(arr1[0],arr2[0]));
        arrayList1.add(new SettingsList1(arr1[1],arr2[1]));
        adapter1 = new CustomAdapter1(this,arrayList1);
        lv1.setAdapter(adapter1);
        ArrayList<SettingsList2> arrayList2 = new ArrayList<>();
        arrayList2.add(new SettingsList2(arr1[2],arr2[2],true));
        adapter2 = new CustomAdapter2(this,arrayList2);
        lv2.setAdapter(adapter2);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        onThemeClick();
                        break;
                    case 1:
                        break;
                }
            }
        });
        editor = preferences.edit();


    }






    public void onThemeClick(){
        final String[] themeArray = {"Светлая","Темная"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_theme,null);
        builder.setTitle("Выберите тему");
       // builder.setView(view);
        builder
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }

                })
        .setSingleChoiceItems(themeArray, themeId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        editor.putInt(THEME_PREFERENCES,0);
                        editor.apply();
                        intent();
                        break;
                    case 1:
                        editor.putInt(THEME_PREFERENCES,1);
                        editor.apply();
                        intent();
                        break;
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void intent(){
        Intent intent = new Intent(this,Settings.class);
        finish();
        startActivity(intent);
    }


    public void onBackPressed(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Theme",themeId);
        finish();
        startActivity(intent);
    }

}
