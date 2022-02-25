package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {
    @Mock
 private WatchlistRepository watchlistRepositoryMock;

    @Mock
  private  MovieRatingService movieRatingServiceMock;

    @InjectMocks
  private WatchlistService watchlistService;

    @Test
   public void testGetWachlistItemsReturnsAllItemsFromRepository() {
        //Arrange
        WatchlistItem item1= new WatchlistItem("Star wars","7.7","M","");
        WatchlistItem item2= new WatchlistItem("Star Treck","8.8","M","");
        List<WatchlistItem> mockItems= Arrays.asList(item1,item2);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        //Act

        List<WatchlistItem>result=watchlistService.getWachlistItems();
        //Assert
        assertTrue(result.size()==2);
        assertTrue(result.get(0).getTitle().equals("Star wars"));
        assertTrue(result.get(1).getTitle().equals("Star Treck"));
    }

    @Test
    public void getWatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {
        //Arrange
        WatchlistItem item1= new WatchlistItem("Star wars","7.7","M","");

        List<WatchlistItem> mockItems= Arrays.asList(item1);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");
        //Act
        List<WatchlistItem>result=watchlistService.getWachlistItems();
        //Assert
        assertTrue(result.get(0).getRating().equals("10"));
    }

    @Test
    public void findWatchlistItemById() {
    }

    @Test
    public void addOrUpdateWatchlistItem() {
    }
}