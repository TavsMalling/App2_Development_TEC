package com.example.app2_development_tec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Kitten> kittens = new ArrayList<Kitten>();

    ListView lstKittens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kittens.add(new Kitten("Kitten doing pull ups", "Kitten, seemingly doing a pull up", R.drawable.kitten_pull_up));
        kittens.add(new Kitten("Kitten on drugs", "Kitten, with huge junky eyes", R.drawable.kitten_on_drugs));
        kittens.add(new Kitten("Kitten in glass", "Kitten sitting in a champagne glass", R.drawable.kitten_in_glass));
        kittens.add(new Kitten("Kitten with a rifle", "This is my rifle. There are many like it, but this one is mine.\n" +
                "\n Without me, my rifle is useless. Without my rifle, I am useless.", R.drawable.kitten_with_gun));

        KittenAdapter kittenAdapter = new KittenAdapter(kittens, this);

        lstKittens = findViewById(R.id.lstKittens);

        lstKittens.setAdapter(kittenAdapter);
    }
}