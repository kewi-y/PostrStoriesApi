package ru.kewiapps.postrstoriesapi.models.story;

import ru.kewiapps.postrstoriesapi.enums.models.story.StoryType;

import java.sql.Timestamp;
import java.util.ArrayList;

public abstract class Story {
    private String id, authorId;
    private Timestamp timestamp;
    private StoryType storyType;

    public Story(String id,StoryType storyType ,String authorId, long timestamp){
        this.id = id;
        this.authorId = authorId;
        this.timestamp = new Timestamp(timestamp);
        this.storyType = storyType;
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public StoryType getStoryType() {
        return storyType;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = new Timestamp(timestamp);
    }

    @Override
    public String toString() {
        String stringClass = "Story{";
        stringClass += " id > " + id;
        stringClass += " authorId > " + authorId;
        stringClass += " timestamp > " + timestamp.getTime();
        stringClass += " storyType > " + storyType + " }";
        return stringClass;
    }
}
