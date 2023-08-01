package ru.kewiapps.postrstoriesapi.repositroy;

import ru.kewiapps.postrstoriesapi.models.story.ImageStory;
import ru.kewiapps.postrstoriesapi.models.story.Story;
import ru.kewiapps.postrstoriesapi.services.DatabaseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class StoriesRepository {
    private static StoriesRepository instance;
    private DatabaseService databaseService;
    private Date date;
    public static StoriesRepository getInstance() {
        if(instance == null){
            instance = new StoriesRepository();
        }
        return instance;
    }
    private StoriesRepository(){
        databaseService = DatabaseService.getInstance();
        date = new Date();
        //TODO: implement other services
    }
    public Story getStoryById(String id) throws ExecutionException, InterruptedException {
        System.out.println("Getting story by id >>: " + id);
        return databaseService.getStoryById(id);
    }
    public void deleteStoriesBeforeTimestamp(long timestamp) throws ExecutionException, InterruptedException {
        ArrayList<Story> storiesList = databaseService.getStoryListBeforeTimestamp(timestamp);
        for(Story story : storiesList){
            databaseService.deleteStory(story.getId());
        }
    }
    public ArrayList<String> getStoryIdListByAuthor(String authorId) throws ExecutionException, InterruptedException {
        return databaseService.getStoryIdListByAuthorId(authorId);
    }
    public String addImageStory(ImageStory imageStory) throws ExecutionException, InterruptedException {
        //TODO: write method using firebase
        System.out.println("<<< Adding story >>>");
        System.out.println("<<< STORY TYPE >>>: " + imageStory.getStoryType());
        System.out.println("id >>: " + imageStory.getId());
        System.out.println("author id >>: " + imageStory.getAuthorId());
        System.out.println("download image uri >>: " + imageStory.getDownloadImageUri());
        System.out.println("timestamp >>: "+ imageStory.getTimestamp().toString());
        imageStory.setTimestamp(date.getTime());
        return databaseService.addStory(imageStory);
    }
    public String addVideoStory(){
        //TODO: write method using firebase
        return " ";
    }
}
