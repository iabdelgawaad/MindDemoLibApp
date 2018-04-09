package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.model;

/**
 * Created by Ibrahim AbdelGawad on 4/10/2018.
 */

public class Category {

    private Integer id;
    private String title;
    private Integer photoCount;
    private Links_ links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}