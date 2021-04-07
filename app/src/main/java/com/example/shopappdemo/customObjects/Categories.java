package com.example.shopappdemo.customObjects;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    ArrayList<String> categoryList = new ArrayList<>();

    public List<String> getCategoryList(){

        categoryList.add("Apps & Games");
        categoryList.add("Baby");
        categoryList.add("Beauty & Personal Care");
        categoryList.add("Cell Phones & Accessories");
        categoryList.add("Clothing, Shoes and Jewelry");
        categoryList.add("Computers");
        categoryList.add("Electronics");
        categoryList.add("Garden & Outdoor");
        categoryList.add("Handmade");
        categoryList.add("Home & Kitchen");
        categoryList.add("Industrial & Scientific");
        categoryList.add("Luggage & Travel Gear");
        categoryList.add("Movies & TV");
        categoryList.add("Musical Instruments");
        categoryList.add("Office Products");
        categoryList.add("Sports & Outdoors");
        categoryList.add("Pet Supplies");
        categoryList.add("Toys & Games");

        return categoryList;
    }
}
