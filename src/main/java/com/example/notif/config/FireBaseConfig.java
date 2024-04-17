package com.example.notif.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.notif.NotifApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
@EnableConfigurationProperties(FireBaseProp.class)
public class FireBaseConfig {
    @Value("${firebase.path}")
    private String firebasePath;
    final FireBaseProp firebaseProperties;

    public FireBaseConfig(FireBaseProp firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    FirebaseApp firebaseApp() throws IOException {
        // ClassLoader classLoader = NotifApplication.class.getClassLoader();
        // File file = new
        // File(classLoader.getResource("FireBaseServiceAccount.json")).getParentFile();
        // FileInputStream serviceAccount = new FileInputStream(firebasePath);

        InputStream serviceAccount = getClass().getResourceAsStream(firebasePath);

        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        return FirebaseApp.initializeApp(firebaseOptions);
    }

    // @Bean
    // GoogleCredentials googleCredentials() throws IOException {
    // if (firebaseProperties.getServiceAgent() != null) {
    // try (InputStream is = firebaseProperties.getServiceAgent().getInputStream())
    // {
    // return GoogleCredentials.fromStream(is);
    // }
    // } else {
    // // Use standard credentials chain. Useful when running inside GKE
    // return GoogleCredentials.getApplicationDefault();
    // }
    // }

}
