package com.example.jammy.zhihudemo.Bean;

import java.util.List;

/**
 * Created by Jammy on 2016/7/17.
 */
public class StoryBase {
    String date;
    List<Story> stories;
    List<TopStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStory() {
        return stories;
    }

    public void setStory(List<Story> story) {
        this.stories = story;
    }

    public List<TopStory> getTop_story() {
        return top_stories;
    }

    public void setTop_story(List<TopStory> top_story) {
        this.top_stories = top_story;
    }
}
