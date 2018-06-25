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

public class CustomAdapter1 extends ArrayAdapter<SettingsList1> {

    private Context mContext;
    private List<SettingsList1> SettingsList1 = new ArrayList<>();

    public CustomAdapter1(Context context, ArrayList<SettingsList1> list){
        super(context,0,list);
        mContext = context;
        SettingsList1 = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem==null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.first_list_item,parent,false);
        }

        SettingsList1 currentSetting = SettingsList1.get(position);

        TextView text1 =  listItem.findViewById(R.id.tv1);
        text1.setText(currentSetting.getText1());

        TextView text2 =  listItem.findViewById(R.id.tv2);
        text2.setText(currentSetting.getText2());
        return listItem;
    }
}
