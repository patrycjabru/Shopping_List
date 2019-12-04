package com.shoppinglist.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Objects;


public class ShoppingItemDetailFragmentLandscape extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy name this fragment is presenting.
     */
    private CellContent.CellItem mItem;


    private DatabaseHelper db;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShoppingItemDetailFragmentLandscape() {
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
                mItem = CellContent.getById(db, Integer.valueOf(Objects.requireNonNull(getArguments().getString(ARG_ITEM_ID))));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shoppingitem_detail_landscape, container, false);

        // Show the dummy name as text in a TextView.
        if (mItem != null) {
            String numberOfItems = mItem.details == null ? "loading..." : mItem.details;
            String itemDetails = "HORIZONTAL\nNumber of items: " + numberOfItems;
            ((TextView) rootView.findViewById(R.id.shoppingitem_detail_landscape_text)).setText(itemDetails);
            Button webButton = rootView.findViewById(R.id.webButtonLandscape);
            webButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemNameUri = mItem.name.replace(" ", "%20");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://allegro.pl/listing?string=" + itemNameUri));
                    startActivity(browserIntent);
                }
            });
        }

        return rootView;
    }
}
