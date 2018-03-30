package com.epam.brest.model;

import java.util.Objects;

/**
 * Group class
 */
public class Group {
    /**
     * Property groupId.
     */
    int groupId;
    /**
     * Property shortName.
     */
    String shortName;
    /**
     * Property fullName.
     */
    String fullName;
    /**
     * Property description.
     */
    String description;

    /**
     * Group class constructor with params
     * @param shortName short name of group
     * @param fullName full name of group
     * @param description description of the student group
     */
    public Group(String shortName, String fullName, String description) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.description = description;
    }

    /**
     * Group class constructor without params
     */
    public Group() {
    }

    public int getGroupId() {
        return groupId;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAverageMarks(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                Objects.equals(shortName, group.shortName) &&
                Objects.equals(fullName, group.fullName) &&
                Objects.equals(description, group.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, shortName, fullName, description);
    }
}
