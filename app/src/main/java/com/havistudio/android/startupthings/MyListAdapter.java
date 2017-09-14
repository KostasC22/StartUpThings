package com.havistudio.android.startupthings;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by kostas on 22/08/2017.
 */

public class MyListAdapter extends RealmBaseAdapter<StartUp> implements ListAdapter {

    public static final String TAG = MyListAdapter.class.getName();

    private static class ViewHolder {
        TextView mText;
        TextView mText2;
        TextView mText3;
        CheckBox deleteCheckBox;
    }

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<Integer>();


    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }

    public MyListAdapter(Context context, OrderedRealmCollection<StartUp> data) {
        super(data);
        Log.i(TAG,"data:"+data.size());
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return adapterData.size();
    }

    @Override
    public StartUp getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mText = (TextView) convertView.findViewById(R.id.textview);
            viewHolder.mText2 = (TextView) convertView.findViewById(R.id.textview2);
            viewHolder.mText3 = (TextView) convertView.findViewById(R.id.textview3);
            viewHolder.deleteCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Log.i(TAG,adapterData.toString());
        if (adapterData != null) {
            final StartUp item = adapterData.get(position);
            viewHolder.mText.setText(item.getCategory());
            viewHolder.mText2.setText(item.getPackageName());
            viewHolder.mText3.setText(item.getDelay()+"");

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
