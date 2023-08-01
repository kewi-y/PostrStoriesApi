package ru.kewiapps.postrstoriesapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kewiapps.postrstoriesapi.models.story.ImageStory;
import ru.kewiapps.postrstoriesapi.models.story.Story;
import ru.kewiapps.postrstoriesapi.repositroy.StoriesRepository;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("")
public class StoriesApiController {
    private static StoriesApiController instance;
    private StoriesRepository storiesRepository;
    public static StoriesApiController getInstance() {
        if(instance == null){
            instance = new StoriesApiController();
        }
        return instance;
    }
    private StoriesApiController(){
        storiesRepository = StoriesRepository.getInstance();
    }
    @GetMapping(value = "getStoryById")
    public Story getStoryById(@RequestParam String storyId){
        try {
            Story story = storiesRepository.getStoryById(storyId);
            System.out.println("Returnable story >>: " + story.toString());
            return story;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "addImageStory")
    public String addImageStory(@RequestBody ImageStory imageStory){
        try {
            return storiesRepository.addImageStory(imageStory);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "getStoriesByAuthorId")
    public ArrayList<String> getStoriesByAuthorId(@RequestParam String authorId){
        try {
            return storiesRepository.getStoryIdListByAuthor(authorId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "test")
    public ResponseEntity<String> testRequest(){
        return ResponseEntity.ok("Test response");
    }

}
