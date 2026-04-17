package com.example.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles all page-level HTTP requests for the portfolio site.
 */
@Controller
public class PortfolioController {

    /**
     * Serves the homepage / index.
     * Thymeleaf resolves this to src/main/resources/templates/index.html
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
