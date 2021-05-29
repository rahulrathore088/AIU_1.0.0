package com.tech.imageUpload.profileRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tech.imageUpload.datastore.FakeUserProfileDataStore;
import com.tech.imageUpload.profile.UserProfile;

@Repository
public class UserProfileDataAccessService {

  private final FakeUserProfileDataStore fakeUserProfileDataStore;
  
  @Autowired
  UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore){
    this.fakeUserProfileDataStore = fakeUserProfileDataStore;
  }
  
  public List<UserProfile> getUserProfiles(){
    return fakeUserProfileDataStore.getUserProfiles();
  }
}
