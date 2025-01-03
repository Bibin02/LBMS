package com.project.lbms.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String handleError(HttpServletRequest req){

        Object statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode == null) {
            return "error";
        }

        switch ((Integer)statusCode) {
            case 404 ->  { return "404"; }
            case 500 ->  { return "500"; }
            default  ->  { return "error"; }
        }


    }
}
