package com.github.nf1213.catz;

import io.realm.RealmObject;

/**
 * Created by nicole on 1/28/16.
 */
public class Cat extends RealmObject {
    private String name;
    private int gender;
    private String description;

    public Cat() { }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getGender() { return gender; }

    public void setGender(int gender) { this.gender = gender; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
