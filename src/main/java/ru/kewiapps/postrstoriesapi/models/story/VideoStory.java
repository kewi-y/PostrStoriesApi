package ru.kewiapps.postrstoriesapi.models.story;

import ru.kewiapps.postrstoriesapi.enums.models.story.StoryType;

public class VideoStory extends Story{
    private String downloadVideoUri;
    public VideoStory(String id, String authorId, String downloadVideoUri, long timestamp) {
        super(id, StoryType.VIDEO_STORY, authorId, timestamp);
        this.downloadVideoUri = downloadVideoUri;
    }

    public String getDownloadVideoUri() {
        return downloadVideoUri;
    }
}
