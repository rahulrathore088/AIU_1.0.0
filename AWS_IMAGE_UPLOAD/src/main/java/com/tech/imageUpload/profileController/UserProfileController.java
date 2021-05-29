package com.tech.imageUpload.profileController;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tech.imageUpload.profile.UserProfile;
import com.tech.imageUpload.profileService.UserProfileService;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {
  
  private final UserProfileService profileService;
  
  @Autowired
  UserProfileController(UserProfileService profileService){
    this.profileService = profileService;
  }
  
  @GetMapping
  public List<UserProfile> getUserProfiles(){
    return profileService.getUserProfiles();
  }
  
  @PostMapping(path = "{userProfileId}/image/upload",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
      @RequestParam("file") MultipartFile file) {
    profileService.uploadUserProfileImage(userProfileId, file);

  }
}
