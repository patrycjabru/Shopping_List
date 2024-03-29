package com.shoppinglist.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Objects;

/**
 * A fragment representing a single ShoppingItem detail screen.
 * This fragment is either contained in a {@link ShoppingItemListActivity}
 * in two-pane mode (on tablets) or a {@link ShoppingItemDetailActivity}
 * on handsets.
 */
public class ShoppingItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy name this fragment is presenting.
     */
    private Product mItem;

    private DatabaseHelper db;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShoppingItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy name specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load name from a name provider.
            try {
                mItem = db.getById(Product.class, Objects.requireNonNull(getArguments().getString(ARG_ITEM_ID)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shoppingitem_detail, container, false);

        // Show the dummy name as text in a TextView.
        if (mItem != null) {
            String numberOfItems = mItem.getNumberOfItems() == null ? "loading..." : mItem.getNumberOfItems();
            String itemDetails = "Number of items: " + numberOfItems;
            ((TextView) rootView.findViewById(R.id.shoppingitem_detail)).setText(itemDetails);
            Button webButton = rootView.findViewById(R.id.webButton);
            webButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemNameUri = mItem.getProductName().replace(" ", "%20");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://allegro.pl/listing?string=" + itemNameUri));
                    startActivity(browserIntent);
                }
            });
        }



        return rootView;
    }
}
