package com.github.nf1213.catz;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    ListView cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm realm = Realm.getInstance(this);

        cats = (ListView) findViewById(R.id.cats);
        RealmResults<Cat> catResults = realm.where(Cat.class).findAll();
        cats.setAdapter(new CatAdapter(this, catResults, true ));
        cats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cat cat = (Cat) view.getTag(R.id.cats);
                Intent intent = new Intent(view.getContext(), CatViewActivity.class);
                intent.putExtra(CatViewActivity.CAT_ID, cat.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewCatActivity.class);
                startActivity(intent);
            }
        });
    }

    public class CatAdapter extends RealmBaseAdapter<Cat> {

        public CatAdapter(Context context,
                         RealmResults<Cat> realmResults,
                         boolean automaticUpdate) {
            super(context, realmResults, automaticUpdate);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1,
                        parent, false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Cat cat = realmResults.get(position);
            viewHolder.name.setText(cat.getName());
            if (cat.getGender() == 1) {
                convertView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
            } else if (cat.getGender() == 2) {
                convertView.setBackgroundColor(getResources().getColor(R.color.colorPink));
            }
            convertView.setTag(R.id.cats, cat);
            return convertView;
        }

        private class ViewHolder {
            TextView name;
        }
    }
}
