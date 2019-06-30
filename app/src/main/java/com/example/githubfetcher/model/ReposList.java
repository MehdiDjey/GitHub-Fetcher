package com.example.githubfetcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReposList {
    @SerializedName("items")
    private List<Repos> items;

    public List<Repos> getItems() {
        return items;
    }

    public void setItems(ArrayList<Repos> items) {
        this.items = items;
    }


    public int getSize() {
        return items.size();
    }

    @Override
    public String toString() {
        return
                "items=" + items;
    }


}
