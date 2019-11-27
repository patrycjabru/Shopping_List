package com.shoppinglist.shoppinglist.dummy;

import android.database.Cursor;

import com.shoppinglist.shoppinglist.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CellContent {


    public static final List<CellItem> ITEMS = new ArrayList<CellItem>();

    public static final Map<String, CellItem> ITEM_MAP = new HashMap<String, CellItem>();

    private static void getItemsFromDatabase(DatabaseHelper db) {
        Cursor cursor = db.getAllData();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            System.out.println("Getting items from database... "+id+name);
            CellItem cellItem = new CellItem(id, name, makeDetails(1));
            ITEMS.add(cellItem);
            ITEM_MAP.put(id, cellItem);
        }
    }

    public static List<CellItem> getItems(DatabaseHelper db) {

        getItemsFromDatabase(db);
        return ITEMS;
    }

    private static CellItem createDummyItem(int position) {
        return new CellItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of name.
     */
    public static class CellItem {
        public final String id;
        public final String name;
        public final String details;

        public CellItem(String id, String content, String details) {
            this.id = id;
            this.name = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
