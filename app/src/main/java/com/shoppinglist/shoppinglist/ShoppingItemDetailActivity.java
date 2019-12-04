package com.shoppinglist.shoppinglist;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

/**
 * An activity representing a single ShoppingItem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ShoppingItemListActivity}.
 */
public class ShoppingItemDetailActivity extends AppCompatActivity {

    DatabaseHelper db;
    Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingitem_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = getIntent().getStringExtra(ShoppingItemDetailFragment.ARG_ITEM_ID);

                db.removeData(Integer.parseInt(str));
                navigateUpTo(new Intent(getApplicationContext(), ShoppingItemListActivity.class));
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            arguments = new Bundle();
            arguments.putString(ShoppingItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ShoppingItemDetailFragment.ARG_ITEM_ID));

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                ShoppingItemDetailFragment fragment = new ShoppingItemDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.shoppingitem_detail_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                ShoppingItemDetailFragmentLandscape fragment = new ShoppingItemDetailFragmentLandscape();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.shoppingitem_detail_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        } else {
            arguments = savedInstanceState;
            arguments.putString(ShoppingItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ShoppingItemDetailFragment.ARG_ITEM_ID));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Create new fragment and transaction
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Fragment newFragment = new ShoppingItemDetailFragment();
            newFragment.setArguments(arguments);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.shoppingitem_detail_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            Fragment newFragment = new ShoppingItemDetailFragmentLandscape();
            newFragment.setArguments(arguments);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.shoppingitem_detail_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
