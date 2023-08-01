package ru.kewiapps.postrstoriesapi.models.story;

import ru.kewiapps.postrstoriesapi.enums.models.story.StoryType;

import java.sql.Timestamp;

public class ImageStory extends Story{
    private String downloadImageUri;
    public ImageStory(String id,String authorId,String downloadImageUri ,long timestamp) {
        super(id, StoryType.IMAGE_STORY, authorId, timestamp);
        this.downloadImageUri = downloadImageUri;
    }

    public String getDownloadImageUri() {
        return downloadImageUri;
    }
}
