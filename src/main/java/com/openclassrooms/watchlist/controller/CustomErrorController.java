package com.openclassrooms.watchlist.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    public String getErrorPath() {
        return "/error";
    }
    @GetMapping("/error")
    public ModelAndView handleError(HttpServletResponse response){
        int code=response.getStatus();
//       String code= RequestDispatcher.ERROR_STATUS_CODE;
        System.out.println("Error with status code "+code+" happened!");

        return new ModelAndView("error");
    }
}
