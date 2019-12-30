package com.codingelab.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 4/28/2019.
 */

public class SearchAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Contacts> listItemStorage;

    public SearchAdapter(Context context, List<Contacts> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItemStorage = customizedListView;

    }

    @Override
    public int getCount() { return listItemStorage.size(); }

    @Override
    public Object getItem(int position) { return listItemStorage.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { ViewHolder listViewHolder;
        if(convertView == null){ listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.colmn_row, parent, false); listViewHolder.dictionaryWord = (TextView)convertView.findViewById(R.id.SecondText); convertView.setTag(listViewHolder); }else{ listViewHolder = (ViewHolder)convertView.getTag();

        } listViewHolder.dictionaryWord.setText(listItemStorage.get(position).getName());
        return convertView;

    }

    static class ViewHolder{
        TextView dictionaryWord;
    }
}
