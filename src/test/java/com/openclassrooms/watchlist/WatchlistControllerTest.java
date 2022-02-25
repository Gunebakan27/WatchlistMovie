package com.openclassrooms.watchlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchlistControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testShowWatchlistItemForm() throws Exception {
		
		mockMvc.perform(get("/watchlistItemForm"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("watchlistItemForm"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("watchlistItem"));
	}
	
	@Test
	public void testSubmitWatchlistItemForm() throws Exception {
		mockMvc.perform(post("/watchlistItemForm"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/watchlist"));
	}
	@Test
	public void testGetWatchlist() throws Exception{
		mockMvc.perform(get("/watchlist"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("watchlist"))
				.andExpect(model().size(2))
				.andExpect(model().attributeExists("numberOfMovies"));
	}
}