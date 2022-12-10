package com.example.app2_development_tec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class KittenAdapter extends BaseAdapter{
    int count;
    private final List<Kitten> kittens;
    private MainActivity mainActivity;

    public KittenAdapter(List<Kitten> kittens, MainActivity mainActivity) {
        this.kittens = kittens;
        this.mainActivity = mainActivity;
    }


    @Override
    public int getCount() {
        return kittens.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        LayoutInflater inflater  = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.kitten_layout, null);

        Kitten kitten = kittens.get(pos);

        ImageView imgKitten = v.findViewById(R.id.imgKitten);
        imgKitten.setImageResource(kitten.getKittenImage());

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        TextView txtDescription = v.findViewById(R.id.txtDescription);

        txtTitle.setText(kitten.getTitle());
        txtDescription.setText(kitten.getDescription());

        return v;
    }
}
