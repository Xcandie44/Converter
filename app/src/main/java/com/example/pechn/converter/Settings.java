package com.example.pechn.converter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Settings extends AppCompatActivity {
    TextView tv1,tv2;
    String[] arr1 ;
    String[] arr2;
    ListView lv1,lv2;
    private CustomAdapter1 adapter1;
    private CustomAdapter2 adapter2;
    private SharedPreferences preferences,languagePreferences;
    SharedPreferences.Editor editor, editorLang;
    public static final String APP_PREFERENCES = "settings";
    public static final String THEME_PREFERENCES = "theme";
    public static final String LANGUAGE_PREFERENCES = "lang";
    RadioGroup radioGroup;
    public static Boolean b = true;
    int savedRadioIndex,themeId=0, languageId=0, color;
    RadioButton rb;
    private Locale locale;
    String lang;    CheckBox cb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        languagePreferences=getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE);
        if(languagePreferences.contains(LANGUAGE_PREFERENCES)) {
            lang = languagePreferences.getString("lang", "default");
            if (lang.equals("default")) {
                languageId = 0;
                lang = getResources().getConfiguration().locale.getCountry();
            } else if (lang.equals("ru")) {
                languageId = 1;
            } else if (lang.equals("en")) {
                languageId = 2;
            }
            locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, null);
        }
        if(preferences.contains(THEME_PREFERENCES)){
            themeId = preferences.getInt(THEME_PREFERENCES,0);
            if(themeId==0){
                setTheme(R.style.Light);
            }else if(themeId==1){
                setTheme(R.style.Dark);
            }else if(themeId==2){
                setTheme(R.style.Green);
            }
        }
        if(preferences.contains("sound")){
            b = preferences.getBoolean("sound",false);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        arr2= getResources().getStringArray(R.array.menu2);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        lv1 = findViewById(R.id.first_list);
        lv2 = findViewById(R.id.second_list);
        getSupportActionBar().hide();
        arr1 = getResources().getStringArray(R.array.menu);
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
        cb1 = findViewById(R.id.cb1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        onThemeClick();
                        break;
                    case 1:
                        onLanguageClick();
                        break;
                }
            }
        });

        editor = preferences.edit();
        editorLang = languagePreferences.edit();
        if(themeId==0) {
            color = getResources().getColor(R.color.blue);
            tv1.setTextColor(color);
            tv2.setTextColor(color);
        }else if(themeId==1){
            color = getResources().getColor(R.color.dark);
            tv1.setTextColor(color);
            tv2.setTextColor(color);
            }
    }


    public void onThemeClick(){
        final String[] themeArray = getResources().getStringArray(R.array.theme);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.themeTitle);
        builder
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
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
                    case 2:
                        editor.putInt(THEME_PREFERENCES,2);
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
        editor.putBoolean("sound",b);
        editor.apply();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("sound",b);
        intent.putExtra("Theme",themeId);
        intent.putExtra("lang",lang);
        finish();
        startActivity(intent);
    }

    public void onLanguageClick(){
        final String[] themeArray = getResources().getStringArray(R.array.language);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.languageTitle);
        builder
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }

                })
                .setSingleChoiceItems(themeArray, languageId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                languageId=0;
                                lang="default";
                                editorLang.putString(LANGUAGE_PREFERENCES,lang);
                                editorLang.apply();
                                dialog.cancel();
                                intent();
                                break;
                            case 1:
                                languageId=1;
                                lang="ru";
                                editorLang.putString(LANGUAGE_PREFERENCES,lang);
                                editorLang.apply();
                                dialog.cancel();
                                intent();
                                break;
                            case 2:
                                languageId=2;
                                lang="en";
                                editorLang.putString(LANGUAGE_PREFERENCES,lang);
                                editorLang.apply();
                                dialog.cancel();
                                intent();
                                break;
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
