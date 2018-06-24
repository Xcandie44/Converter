package com.example.pechn.converter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    final String[] arr1 = new String[]{"Тема","Язык"};
    final String[] arr2 = new String[]{"Тема","Язык"};
    ListView lv1;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        lv1 = findViewById(R.id.first_list);
        ArrayList<SettingsList> arrayList = new ArrayList<>();
        arrayList.add(new SettingsList(arr1[0],arr2[0]));
        arrayList.add(new SettingsList(arr1[1],arr2[1]));
        adapter = new CustomAdapter(this,arrayList);
        lv1.setAdapter(adapter);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle("ghguftyf");
        return builder.create();
    }
}
