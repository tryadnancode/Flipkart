package com.example.flipkart;

import java.util.ArrayList;

public class Constant {


    public static ArrayList<BannerItem> getBanner() {
        ArrayList<BannerItem> list = new ArrayList<>();
        list.add(new BannerItem(R.drawable.img_3)) ;
        list.add(new BannerItem(R.drawable.img_4));
        list.add(new BannerItem(R.drawable.img_5)) ;
        list.add(new BannerItem(R.drawable.img_6));
        return list;
    }


    public static ArrayList<SuggestItem> getSuggestItems() {
        ArrayList<SuggestItem> list = new ArrayList<>();
        list.add(new SuggestItem(R.drawable.img_7));
        list.add(new SuggestItem(R.drawable.img_8));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.img_9));
        list.add(new SuggestItem(R.drawable.img_2));
        return list;
    }
    public static ArrayList<GroceryItemCategory> getGroceryItem() {
        ArrayList<GroceryItemCategory> list = new ArrayList<>();
        list.add(new GroceryItemCategory(R.drawable.img_12,"Oil"));
        list.add(new GroceryItemCategory(R.drawable.img_13,"Dry Fruits"));
        list.add(new GroceryItemCategory(R.drawable.img_14,"Shoap"));
        list.add(new GroceryItemCategory(R.drawable.img_15,"HandWash"));
        list.add(new GroceryItemCategory(R.drawable.img_16,"Masala"));
        list.add(new GroceryItemCategory(R.drawable.img_17,"Aata"));
        list.add(new GroceryItemCategory(R.drawable.img_18,"Biscuits"));
        list.add(new GroceryItemCategory(R.drawable.img_19,"Cold Drinks"));
        list.add(new GroceryItemCategory(R.drawable.img_20,"Chocolate"));
        list.add(new GroceryItemCategory(R.drawable.img_21,"Ghee"));
        list.add(new GroceryItemCategory(R.drawable.img_22,"Fast Food"));
        list.add(new GroceryItemCategory(R.drawable.img_23,"Others"));

        return list;
    }
}
