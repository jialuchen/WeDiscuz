package com.example.zootopia.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zootopia.entities.User;
import com.example.zootopia.ui.ViewHolder;
import com.example.zootopia.wediscuz.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Zootopia on 5/2/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class MemberAdapter extends BaseAdapter {
    // data for listview
    private List<User> data;
    // customize layout
    private LayoutInflater inflater;
    // hashmap to record the check condition of checkbox
    private static HashMap<Integer, Boolean> isSelected;

    public MemberAdapter(LayoutInflater inflater, List<User> data) {
        this.data = data;
        this.inflater = inflater;
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < data.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getUserID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.ui_member_listview, null);
            holder.memberCheckBox = (CheckBox) convertView.findViewById(R.id.member_listitem_checkBox);
            holder.icon = (ImageView) convertView.findViewById(R.id.member_listitem_image);
            holder.email = (TextView) convertView.findViewById(R.id.member_listitem_email);
            holder.name = (TextView) convertView.findViewById(R.id.member_listitem_name);
            // set tag for view
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // get User object
        User member = data.get(position);

        // Set content
        holder.icon.setImageResource(R.mipmap.ic_launcher);
        holder.email.setText(member.getEmail());
        holder.name.setText(member.getName());
        holder.memberCheckBox.setChecked(getIsSelected().get(position));

        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MemberAdapter.isSelected = isSelected;
    }
}
