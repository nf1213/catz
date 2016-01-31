package com.github.nf1213.catz;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;

public class CatViewActivity extends AppCompatActivity {

    public static final String CAT_ID = "catId";

    Cat cat;
    TextView name;
    TextView gender;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);

        final Realm realm = Realm.getInstance(this);

        name = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
        description = (TextView) findViewById(R.id.description);

        if (getIntent() != null) {
            int catId = getIntent().getIntExtra(CAT_ID, -1);
            cat = realm.where(Cat.class)
                    .equalTo("id", catId)
                    .findFirst();

            name.setText(cat.getName());
            gender.setText(Arrays.asList(getResources().getStringArray(R.array.genders)).get(cat.getGender()));
            description.setText(cat.getDescription());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                cat.removeFromRealm();
                realm.commitTransaction();
                finish();
            }
        });
    }
}
