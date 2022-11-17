package com.example.controllers;

import com.example.entities.Feed;
import com.example.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @QueryMapping
    Iterable<Feed> feeds(){
        return feedRepository.findAll();
    }

    @QueryMapping
    Feed feedById(@Argument long id) {
        return feedRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Feed with this ID not found!"));
    }

}
