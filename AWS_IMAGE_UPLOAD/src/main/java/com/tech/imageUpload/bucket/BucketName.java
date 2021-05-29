package com.tech.imageUpload.bucket;

public enum BucketName {
  PROFILE_IMAGE("rahulcode-image-upload");
  
  private final String bucketName;
  
  BucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getBucketName() {
    return bucketName;
  }
  
}
