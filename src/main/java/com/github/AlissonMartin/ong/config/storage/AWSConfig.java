package com.github.AlissonMartin.ong.config.storage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

  @Value("${cloud.aws.s3.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.s3.secretKey}")
  private String secretKey;

  public AWSCredentials credentials() {
    AWSCredentials credentials = new BasicAWSCredentials(
            accessKey,
            secretKey
    );
    return credentials;
  }

  @Bean
  public AmazonS3 amazonS3() {
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials()))
            .withRegion(Regions.SA_EAST_1)
            .build();

    return s3client;

  }
}