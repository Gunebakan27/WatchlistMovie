package com.openclassrooms.watchlist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exception.DuplicateTitleException;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class WatchlistController {
	
private WatchlistService watchlistService;

	@Autowired
	public WatchlistController(WatchlistService watchlistService) {
		this.watchlistService = watchlistService;
	}

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
		String viewName="watchlistItemForm";
		Map<String, Object> model = new HashMap<String, Object>();
		WatchlistItem watchlistItem=watchlistService.findWatchlistItemById(id);
		
		if (watchlistItem==null) {
			model.put("watchlistItem", new WatchlistItem());
		}else {
			model.put("watchlistItem", watchlistItem);
		}
		
		return new ModelAndView(viewName, model );
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return new ModelAndView("watchlistItemForm");
		}

		try {
			watchlistService.addOrUpdateWatchlistItem(watchlistItem);
		} catch (DuplicateTitleException e) {
			bindingResult.rejectValue("title","","This title already exists on your watchlist");
			return new ModelAndView("watchlistItemForm");
		}


		RedirectView redirect=new RedirectView();
		redirect.setUrl("/watchlist");
				 return new ModelAndView(redirect);
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
			
			String viewName = "watchlist";
			Map<String, Object> model = new HashMap<String, Object>();
			
//			watchlistItems.clear();
//			watchlistItems.add(new WatchlistItem("Lion King","8.5","high","Hakuna matata!"));
//			watchlistItems.add(new WatchlistItem("Frozen","7.5","medium","Let it go!"));
//			watchlistItems.add(new WatchlistItem("Cars","7.1","low","Go go go!"));
//			watchlistItems.add(new WatchlistItem("Wall-E","8.4","high","You are crying!"));
			
			model.put("watchlistItems", watchlistService.getWachlistItems());
			model.put("numberOfMovies", watchlistService.getWatchlistItemSize());
			
			return new ModelAndView(viewName , model);
	}

}