package com.aditya.controller;

import com.aditya.dto.Request;
import com.aditya.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shortUrl")
    public ResponseEntity<String> createShortendUrl(@RequestBody Request request) {
        String shortUrl = urlService.shortenUrl(request.getUrl());
        return ResponseEntity.status(HttpStatus.OK).body(shortUrl);
    }

    @GetMapping("/{id}")
    public ModelAndView redirectUrl(@PathVariable("id") String hash, ModelMap model) {
        String longUrl = urlService.getLongUrl(hash);
        return new ModelAndView("redirect:"+longUrl);
    }
}
