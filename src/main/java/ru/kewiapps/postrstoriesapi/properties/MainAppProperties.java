package ru.kewiapps.postrstoriesapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "main")
public class MainAppProperties {
    private long storyDeletingTime;

    public long getStoryDeletingTime() {
        return storyDeletingTime;
    }

    public void setStoryDeletingTime(long storyDeletingTime) {
        this.storyDeletingTime = storyDeletingTime;
    }
}
