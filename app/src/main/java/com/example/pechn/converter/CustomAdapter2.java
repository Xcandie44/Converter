package com.example.pechn.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<SettingsList2> {

    private Context mContext;
    private List<SettingsList2> SettingsList2 = new ArrayList<>();

    public CustomAdapter2(Context context, ArrayList<SettingsList2> list){
        super(context,0,list);
        mContext = context;
        SettingsList2 = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.second_list_item,parent,false);
        }

        final SettingsList2 currentSetting = SettingsList2.get(position);

        TextView text1 =  listItem.findViewById(R.id.tv1);
        text1.setText(currentSetting.getText1());

        TextView text2 =  listItem.findViewById(R.id.tv2);
        text2.setText(currentSetting.getText2());

        final CheckBox cb1 = listItem.findViewById(R.id.cb1);
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb1.isChecked()){
                    currentSetting.setBox(true);
                }else {
                    currentSetting.setBox(false);
                }
            }
        });
        cb1.setTag(position);
        cb1.setChecked(currentSetting.isBox());



        return listItem;
    }

}
