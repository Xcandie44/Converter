package com.example.pechn.converter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<SettingsList> {

    private Context mContext;
    private List<SettingsList> SettingsList = new ArrayList<>();

    public CustomAdapter(Context context,ArrayList<SettingsList> list){
        super(context,0,list);
        mContext = context;
        SettingsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.first_list_item,parent,false);
        }

        SettingsList currentSetting = SettingsList.get(position);

        TextView text1 =  listItem.findViewById(R.id.tv1);
        text1.setText(currentSetting.getText1());

        TextView text2 =  listItem.findViewById(R.id.tv2);
        text2.setText(currentSetting.getText2());
        return listItem;
    }
}
