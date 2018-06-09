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

public class ActivityList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public ActivityList(Activity context, List<User> userList){

        super(context,R.layout.activitylist_layout, userList);
        this.context = context;
        this.userList = userList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.activitylist_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView)listViewItem.findViewById(R.id.textViewDescripton);

        User user = userList.get(position);

        textViewName.setText(user.getCurrentActivity());
        textViewDescription.setText(user.getProblemToSolve());

        return listViewItem;
    }
}
