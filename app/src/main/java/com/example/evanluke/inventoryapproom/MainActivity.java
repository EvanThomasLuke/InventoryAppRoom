package com.example.evanluke.inventoryapproom;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private ItemViewModel mItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter adapter = new ItemListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter.setOnItemClickListener(new ItemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //String title = movies.get(position).getName();
                //Movie clickedMovie = movies.get(position);
                //Toast.makeText(MainActivity.this, title, Toast.LENGTH_LONG).show();

                //Use this to retrieve the item
                //then pass it to detail activity
                int id = adapter.getId(position);

                // I think just launch intent with id then do query call from
                //editActivity this line below
                //mItemViewModel.getItem(id);
                
                //  // TODO: 5/9/18 or send the item object update it there and send back here and then update db

                //Launch detail view passing movie as an extra
                Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
                //had to cast Movie object to Serializable idk if it will work
                intent.putExtra("ITEM_DETAIL_KEY", id);
                startActivity(intent);



            }
        });



        // Get a new or existing ViewModel from the ViewModelProvider.
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mItemViewModel.getAllWords().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable final List<Item> items) {
                // Update the cached copy of the words in the adapter.
                adapter.setItems(items);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            //Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY));

            Bundle extras = data.getExtras();
            Item item = new Item(extras.getString("EXTRA_REPLY"), extras.getInt("EXTRA_REPLY_INT", 0));
            //Item item = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY), data.getIntExtra(NewItemActivity.EXTRA_REPLY, 0));
            mItemViewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
