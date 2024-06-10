package com.example.flipkart;

import java.util.ArrayList;

public class Constant {


    public static ArrayList<BannerItem> getBanner() {
        ArrayList<BannerItem> list = new ArrayList<>();
        list.add(new BannerItem(R.drawable.logo)) ;
        list.add(new BannerItem(R.drawable.flip));
        list.add(new BannerItem(R.drawable.logo)) ;
        list.add(new BannerItem(R.drawable.flip));
        return list;
    }

    public static ArrayList<SuggestItem> getSuggest() {
        ArrayList<SuggestItem> list = new ArrayList<>();

        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        return list;
    }


    public static ArrayList<SuggestItem> getSuggestItems() {
        ArrayList<SuggestItem> list = new ArrayList<>();
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));
        list.add(new SuggestItem(R.drawable.down));

        return list;
    }
}
