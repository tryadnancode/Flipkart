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
}
