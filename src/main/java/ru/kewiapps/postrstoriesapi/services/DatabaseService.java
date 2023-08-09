package ru.kewiapps.postrstoriesapi.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import ru.kewiapps.postrstoriesapi.enums.models.story.StoryType;
import ru.kewiapps.postrstoriesapi.models.story.ImageStory;
import ru.kewiapps.postrstoriesapi.models.story.Story;
import ru.kewiapps.postrstoriesapi.models.story.VideoStory;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class DatabaseService {
    private static DatabaseService instance;
    private CollectionReference collectionReference;
    private Firestore database;
    public static DatabaseService getInstance() {
        if(instance == null){
            instance = new DatabaseService();
        }
        return instance;
    }
    private DatabaseService(){
        database = FirestoreClient.getFirestore();
        collectionReference = database.collection("stories");
    }
    public String addStory(Story story) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = collectionReference.add(getMapOfStory(story));
        return future.get().getId();
    }
    public Story getStoryById(String storyId) throws ExecutionException, InterruptedException {
        collectionReference = database.collection("stories");
        Query sortingByIdQuery = collectionReference.whereEqualTo("id",storyId).limit(1);
        ApiFuture<QuerySnapshot> sortingByIdFutureSnapshot = sortingByIdQuery.get();
        QuerySnapshot sortingByIdSnapshot = sortingByIdFutureSnapshot.get();
        List<QueryDocumentSnapshot> storiesSnapshotList = sortingByIdSnapshot.getDocuments();
        return getStoryFromMap(storiesSnapshotList.get(0).getData());
    }
    public ArrayList<String> getStoryIdListByAuthorId(String authorId) throws ExecutionException, InterruptedException {
        ArrayList<String> storiesIdList = new ArrayList<>();
        collectionReference = database.collection("stories");
        Query sortingByAuthorIdQuery = collectionReference.whereEqualTo("authorId", authorId);
        ApiFuture<QuerySnapshot> sortingByAuthorIdFutureSnapshot = sortingByAuthorIdQuery.get();
        QuerySnapshot sortingByAuthorIdSnapshot = sortingByAuthorIdFutureSnapshot.get();
        List<QueryDocumentSnapshot> storiesSnapshotList = sortingByAuthorIdSnapshot.getDocuments();
        if(storiesSnapshotList.size() > 0){
            for(QueryDocumentSnapshot snapshot : storiesSnapshotList){
                storiesIdList.add(getStoryFromMap(snapshot.getData()).getId());
            }
        }
        return storiesIdList;
    }
    public ArrayList<Story> getStoryListBeforeTimestamp(long timestamp) throws ExecutionException, InterruptedException {
        ArrayList<Story> storiesList = new ArrayList<>();
        collectionReference = database.collection("stories");
        Query sortingByTimestampQuery = collectionReference.whereLessThanOrEqualTo("timestamp",timestamp);
        ApiFuture<QuerySnapshot> sortingByTimestampFutureSnapshot = sortingByTimestampQuery.get();
        QuerySnapshot sortingByTimestampSnapshot = sortingByTimestampFutureSnapshot.get();
        List<QueryDocumentSnapshot> storiesSnapshotList = sortingByTimestampSnapshot.getDocuments();
        if(storiesSnapshotList.size() > 0){
            for(QueryDocumentSnapshot snapshot : storiesSnapshotList){
                storiesList.add(getStoryFromMap(snapshot.getData()));
            }
        }
        return storiesList;
    }
    public String deleteStory(String storyId) throws ExecutionException, InterruptedException {
        collectionReference = database.collection("stories");
        Query sortingByIdQuery = collectionReference.whereEqualTo("id",storyId).limit(1);
        ApiFuture<QuerySnapshot> sortingByIdFutureSnapshot = sortingByIdQuery.get();
        QuerySnapshot sortingByIdSnapshot = sortingByIdFutureSnapshot.get();
        List<QueryDocumentSnapshot> storiesSnapshotList = sortingByIdSnapshot.getDocuments();
        if(storiesSnapshotList.size() > 0){
            DocumentReference storyReference = storiesSnapshotList.get(0).getReference();
            ApiFuture<WriteResult> deletingStoryResult = storyReference.delete();
            return deletingStoryResult.get().getUpdateTime().toString();
        }
        else {
            return null;
        }
    }
    private Map<String,Object> getMapOfStory(Story story){
        HashMap<String,Object> storyMap = new HashMap<>();
        storyMap.put("id",story.getId());
        storyMap.put("authorId",story.getAuthorId());
        storyMap.put("timestamp",story.getTimestamp().getTime());
        storyMap.put("storyType",story.getStoryType().toString());
        if(story.getStoryType() == StoryType.IMAGE_STORY){
            ImageStory imageStory = (ImageStory) story;
            storyMap.put("downloadImageUri",imageStory.getDownloadImageUri());
            return storyMap;
        }
        if(story.getStoryType() == StoryType.VIDEO_STORY){
            VideoStory videoStory = (VideoStory) story;
            storyMap.put("downloadVideoUri",videoStory.getDownloadVideoUri());
            return storyMap;
        }
        else {
            return null;
        }
    }
    private Story getStoryFromMap(Map<String,Object> storyMap){
        Story story;
        if(storyMap.get("storyType").equals(StoryType.IMAGE_STORY.toString())){
            story = new ImageStory((String) storyMap.get("id"),(String) storyMap.get("authorId"),
                    (String) storyMap.get("downloadImageUri"),(long) storyMap.get("timestamp"));
            return story;
        }
        else if(storyMap.get("storyType").equals(StoryType.VIDEO_STORY.toString())){
            story = new VideoStory((String) storyMap.get("id"),(String) storyMap.get("authorId"),
                    (String) storyMap.get("downloadVideoUri"),(long) storyMap.get("timestamp"));
            return story;
        }
        else {
            return null;
        }
    }
}
