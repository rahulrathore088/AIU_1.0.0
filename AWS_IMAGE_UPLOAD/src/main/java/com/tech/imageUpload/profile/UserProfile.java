package com.tech.imageUpload.profile;

import java.util.Optional;
import java.util.UUID;

public class UserProfile {

  private UUID userProfileId;

  private String userName;

  private String userProfileImageLink;

  public UserProfile(UUID userProfileId, String userName, String userProfileImageLink) {
    this.userProfileId = userProfileId;
    this.userName = userName;
    this.userProfileImageLink = userProfileImageLink;
  }

  public UUID getUserProfileId() {
    return userProfileId;
  }

  public void setUserProfileId(UUID userProfileId) {
    this.userProfileId = userProfileId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Optional<String> getUserProfileImageLink() {
    return Optional.ofNullable(userProfileImageLink);
  }

  public void setUserProfileImageLink(String userProfileImageLink) {
    this.userProfileImageLink = userProfileImageLink;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    result = prime * result + ((userProfileId == null) ? 0 : userProfileId.hashCode());
    result =
        prime * result + ((userProfileImageLink == null) ? 0 : userProfileImageLink.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserProfile other = (UserProfile) obj;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    if (userProfileId == null) {
      if (other.userProfileId != null)
        return false;
    } else if (!userProfileId.equals(other.userProfileId))
      return false;
    if (userProfileImageLink == null) {
      if (other.userProfileImageLink != null)
        return false;
    } else if (!userProfileImageLink.equals(other.userProfileImageLink))
      return false;
    return true;
  }

}
