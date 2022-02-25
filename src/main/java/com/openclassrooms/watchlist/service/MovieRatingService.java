package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieRatingService {
String apiURL="http://www.omdbapi.com/?apikey=f01656af&t=";
public String getMovieRating(String title){
   try{
       RestTemplate template=new RestTemplate();
       ResponseEntity<ObjectNode> response = template.getForEntity(apiURL + title, ObjectNode.class);
       ObjectNode jsonNodes = response.getBody();
       return jsonNodes.path("imdbRating").asText();
   }catch(Exception e){
       System.out.println("Something went wrong while callinh OMDb API"+e.getMessage());
       return null;
   }

}
}
