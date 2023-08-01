package ru.kewiapps.postrstoriesapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.kewiapps.postrstoriesapi.enums.models.story.StoryType;

@ConfigurationProperties(prefix = "database")
public class DatabaseProperties {
    private String storyCollectionName;
    private String storyIdFieldName;
    private String storyAuthorIdFieldName;
    private String storyTimestampFiledName;
    private String storyTypeFiledName;
    private String storyDownloadImageUriFiledName;

    public String getStoryCollectionName() {
        return storyCollectionName;
    }

    public String getStoryIdFieldName() {
        return storyIdFieldName;
    }

    public String getStoryAuthorIdFieldName() {
        return storyAuthorIdFieldName;
    }

    public String getStoryTimestampFiledName() {
        return storyTimestampFiledName;
    }

    public String getStoryTypeFiledName() {
        return storyTypeFiledName;
    }

    public String getStoryDownloadImageUriFiledName() {
        return storyDownloadImageUriFiledName;
    }

    public void setStoryCollectionName(String storyCollectionName) {
        this.storyCollectionName = storyCollectionName;
    }

    public void setStoryIdFieldName(String storyIdFieldName) {
        this.storyIdFieldName = storyIdFieldName;
    }

    public void setStoryAuthorIdFieldName(String storyAuthorIdFieldName) {
        this.storyAuthorIdFieldName = storyAuthorIdFieldName;
    }

    public void setStoryTimestampFiledName(String storyTimestampFiledName) {
        this.storyTimestampFiledName = storyTimestampFiledName;
    }

    public void setStoryTypeFiledName(String storyTypeFiledName) {
        this.storyTypeFiledName = storyTypeFiledName;
    }

    public void setStoryDownloadImageUriFiledName(String storyDownloadImageUriFiledName) {
        this.storyDownloadImageUriFiledName = storyDownloadImageUriFiledName;
    }
}
