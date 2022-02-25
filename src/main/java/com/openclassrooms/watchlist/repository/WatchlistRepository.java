package com.openclassrooms.watchlist.repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;

import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository {

    private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
    private static int index = 1;

    public List<WatchlistItem>getList(){
        return watchlistItems;
    }
    public void addItem(WatchlistItem watchlistItem){
        watchlistItem.setId(index++);
        watchlistItems.add(watchlistItem);
    }
    public WatchlistItem findById(Integer id){
        for (WatchlistItem watchlisItem:watchlistItems
             ) {
            if (watchlisItem.getId().equals(id)){
                return watchlisItem;
            }

        }return null;
    }
    public WatchlistItem findByTitle(String title){
        for (WatchlistItem watchlistItem:watchlistItems
             ) {
            if (watchlistItem.getTitle().equals(title)){
                return watchlistItem;
            }

        }return null;
    }
}