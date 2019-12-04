package com.shoppinglist.shoppinglist;

import android.database.Cursor;

import com.j256.ormlite.table.DatabaseTable;
import com.shoppinglist.shoppinglist.DatabaseHelper;

import java.sql.SQLException;
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

    public static List<CellItem> getItems(DatabaseHelper db) throws SQLException {
        List<CellItem> items = new ArrayList<CellItem>();
        List<Product> products = db.getAll(Product.class);

        for(Product product : products) {
            Integer id = product.getId();
            String name = product.getProductName();
            String numberOfItems = product.getNumberOfItems();

            System.out.println("Getting items from database... "+id + " " +name);
            CellItem cellItem = new CellItem(id.toString(), name, numberOfItems);

            items.add(cellItem);
            ITEM_MAP.put(id.toString(), cellItem);
        }
        return items;
    }

    public static CellItem getById(DatabaseHelper db, Integer id) throws SQLException {
        List<CellItem> items = new ArrayList<CellItem>();
        Product product = db.getById(Product.class, id);


        System.out.println("Getting item from database... "+id + " " +product.getProductName());
        return new CellItem(id.toString(), product.getProductName(), product.getNumberOfItems());
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
