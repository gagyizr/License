package com.example.gagyi.test;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zalan on 3/19/2018.
 */

public class ParentList extends ArrayAdapter<Parent> {

    private Activity context;
    private List<Parent> usersList;

    public ParentList(Activity context,List<Parent> usersList){

        super(context,R.layout.nameslist_layout , usersList);
        this.context = context;
        this.usersList = usersList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.activitylist_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView)listViewItem.findViewById(R.id.textViewDescripton);

        Parent parenttemp = usersList.get(position);

        textViewName.setText(parenttemp.getFirstName());
        textViewDescription.setText(parenttemp.getLastName());

        return listViewItem;
    }
}
