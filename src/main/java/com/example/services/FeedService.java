package com.example.services;

import com.example.entities.Feed;
import com.example.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedRepository repository;

    private FeedService() {}

    public void saveIfNotExist(Feed feed, FeedRepository feedRepository){
        List<Feed> allFeeds = feedRepository.findAll();

        List<String> allFeedTitles = allFeeds.stream().map(Feed::getTitle).toList();

        boolean feedExists = allFeedTitles.contains(feed.getTitle());
        if (!feedExists) {
            feedRepository.save(feed);
        }
    }

    public void saveIfNotExist(List<Feed> feedList, FeedRepository feedRepository){
        List<Feed> allFeeds = feedRepository.findAll();

        List<String> allDbFeedTitles = allFeeds.stream().map(Feed::getTitle).toList();

        List<Feed> removedDupTitles = feedList.stream().filter(feed -> !allDbFeedTitles.contains(feed.getTitle())).toList();

        if (!removedDupTitles.isEmpty()){
            feedRepository.saveAll(removedDupTitles);
        }
    }
}
