package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.model;

/**
 * Created by Ibrahim AbdelGawad on 4/10/2018.
 */

public class User {

    private String id;
    private String username;
    private String name;
    private ProfileImage profileImage;
    private Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}