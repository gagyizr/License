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

public class UsersList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> usersList;

    public UsersList(Activity context,List<User> usersList){

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

        User user = usersList.get(position);

        textViewName.setText(user.getFirstName());
        textViewDescription.setText(user.getLastName());

        return listViewItem;
    }
}
