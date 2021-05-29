package com.tech.imageUpload.profileService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tech.imageUpload.bucket.BucketName;
import com.tech.imageUpload.fileStore.FileStore;
import com.tech.imageUpload.profile.UserProfile;
import com.tech.imageUpload.profileRepo.UserProfileDataAccessService;

@Service
public class UserProfileService {

  private final UserProfileDataAccessService accessService;
  private final FileStore fileStore;

  @Autowired
  UserProfileService(UserProfileDataAccessService accessService, FileStore fileStore) {
    this.accessService = accessService;
    this.fileStore = fileStore;
  }

  public List<UserProfile> getUserProfiles() {
    return accessService.getUserProfiles();
  }

  public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {

    isFileEmpty(file);
    isImage(file);
    UserProfile user = getUserProfileOrThrow(userProfileId);
    Map<String, String> metadata = extractMetadata(file);

    String path =
        String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
    String fileName = String.format("%s-%s", file.getName(), UUID.randomUUID());

    try {
      fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private Map<String, String> extractMetadata(MultipartFile file) {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Content-Type", file.getContentType());
    metadata.put("Content-Length", String.valueOf(file.getSize()));
    return metadata;
  }

  private UserProfile getUserProfileOrThrow(UUID userProfileId) {
    UserProfile user = accessService.getUserProfiles().stream()
        .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId)).findFirst()
        .orElseThrow(() -> new IllegalStateException(
            String.format("User profile %s not found", userProfileId)));
    return user;
  }

  private void isImage(MultipartFile file) {
    if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType())
        .contains(file.getContentType())) {
      throw new IllegalStateException("File must be an image ["+ file.getContentType() +"].");
    }
  }

  private void isFileEmpty(MultipartFile file) {
    if (file.isEmpty()) {
      throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
    }
  }
}
