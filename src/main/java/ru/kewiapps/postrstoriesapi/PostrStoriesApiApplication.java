package ru.kewiapps.postrstoriesapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.kewiapps.postrstoriesapi.properties.MainAppProperties;
import ru.kewiapps.postrstoriesapi.repositroy.StoriesRepository;

import java.io.*;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@EnableScheduling
@EnableConfigurationProperties(MainAppProperties.class)
@SpringBootApplication
public class PostrStoriesApiApplication {
    static long storyDeletingTime;
    public static void main(String[] args){
        InputStream serviceAccount = PostrStoriesApiApplication.class.getResourceAsStream("/adminServiceAccountKey.json");
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mediaiocloud-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigurableApplicationContext context = SpringApplication.run(PostrStoriesApiApplication.class, args);
        MainAppProperties mainAppProperties = context.getBean(MainAppProperties.class);
        storyDeletingTime = mainAppProperties.getStoryDeletingTime();
    }
    @Scheduled(initialDelayString = "${storyCheckingInitialDelay}",fixedRateString = "${storyCheckingTime}")
    public void deletingStoryTask() throws ExecutionException, InterruptedException {
        StoriesRepository storiesRepository = StoriesRepository.getInstance();
        Date date = new Date();
        storiesRepository.deleteStoriesBeforeTimestamp(date.getTime() - storyDeletingTime);
    }

}
