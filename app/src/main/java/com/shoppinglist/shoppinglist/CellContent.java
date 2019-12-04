package com.shoppinglist.shoppinglist;

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

    public static final Map<String, CellItem> ITEM_MAP = new HashMap<String, CellItem>();

    public static List<CellItem> getItems(DatabaseHelper db) {
        List<CellItem> items = new ArrayList<CellItem>();
        Cursor cursor = db.getAllData();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String numberOfItems = cursor.getString(2);

            System.out.println("Getting items from database... "+id + " " +name);
            CellItem cellItem = new CellItem(id, name, numberOfItems);

            items.add(cellItem);
            ITEM_MAP.put(id, cellItem);
        }
        return items;
    }

    public static CellItem getById(DatabaseHelper db, String id) {
        List<CellItem> items = new ArrayList<CellItem>();
        Cursor cursor = db.getById(id);

        cursor.moveToFirst();
        String name = cursor.getString(1);
        String numberOfItems = cursor.getString(2);

        System.out.println("Getting item from database... "+id + " " +name);
        return new CellItem(id, name, numberOfItems);
    }

    public static void removeItem(Integer id) {
        ITEM_MAP.remove(id.toString());
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
