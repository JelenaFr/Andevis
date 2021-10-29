package com.assignment.andevis.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class AppErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
