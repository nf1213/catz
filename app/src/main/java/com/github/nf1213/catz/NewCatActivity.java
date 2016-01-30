package com.github.nf1213.catz;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;

public class NewCatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name;
    Spinner gender;
    EditText description;

    String nameVal;
    int genderVal;
    String descriptionVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cat);

        name = (EditText) findViewById(R.id.name);
        gender = (Spinner) findViewById(R.id.gender);
        description = (EditText) findViewById(R.id.description);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameVal = name.getText().toString();
                descriptionVal = description.getText().toString();

                if (TextUtils.isEmpty(nameVal)) {
                    name.setError(getString(R.string.name_error));
                } else {
                    Realm realm = Realm.getInstance(v.getContext());
                    realm.beginTransaction();
                    Cat cat = realm.createObject(Cat.class);
                    cat.setName(nameVal);
                    cat.setGender(genderVal);
                    cat.setDescription(descriptionVal);
                    realm.commitTransaction();
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        genderVal = Integer.valueOf(
                Arrays.asList(getResources().
                getStringArray(R.array.gender_values))
                .get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        genderVal = 0;
    }
}
