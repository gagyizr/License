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

public class GamesList extends ArrayAdapter<Game> {

    private Activity context;
    private List<Game> gamesList;

    public GamesList(Activity context,List<Game> gamesList){

        super(context,R.layout.gameslist_layout , gamesList);
        this.context = context;
        this.gamesList = gamesList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem =  inflater.inflate(R.layout.gameslist_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView)listViewItem.findViewById(R.id.textViewDescripton);

        Game game = gamesList.get(position);

        textViewName.setText(game.getName());
        textViewDescription.setText(game.getDescription());

        return listViewItem;
    }
}
