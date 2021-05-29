package com.tech.imageUpload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfiguration {

  @Bean
  public AmazonS3 s3() {
    String accessKey = "AKIAYI5UXPJN3HUJA4CU";
    String secretKey = "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";
    AWSCredentials awsCred = new BasicAWSCredentials(accessKey, secretKey);
    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
  }
}
