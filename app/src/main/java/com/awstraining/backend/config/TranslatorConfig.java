package com.awstraining.backend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class TranslatorConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKey:#{null}}")
    private String snsAccessKey;

    @Value("${aws.secretKey:#{null}}")
    private String snsSecretKey;

    // TODO: lab2
    //  3. Think how to connect with AWS Service from your local pc.
    @Bean
    AmazonTranslate configureTranslatorClient() {
        if (snsAccessKey != null && snsSecretKey != null) {
            BasicAWSCredentials credentials = new BasicAWSCredentials(snsAccessKey, snsSecretKey);
            AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
            return AmazonTranslateClient.builder()
                    .withCredentials(credentialsProvider)
                    .withRegion(awsRegion)
                    .build();
        }
        return AmazonTranslateClient.builder().build();

    }
}
