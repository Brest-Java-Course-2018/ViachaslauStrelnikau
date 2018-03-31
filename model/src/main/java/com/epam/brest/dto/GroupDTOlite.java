package com.epam.brest.dto;

import java.util.Objects;

public class GroupDTOlite {
    /**
     * Property groupId.
     */
    private int groupId;
    /**
     * Property fullName.
     */
    private String fullName;

    public GroupDTOlite(int groupId, String fullName) {
        this.groupId = groupId;
        this.fullName = fullName;
    }

    public GroupDTOlite() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "GroupDTOlite{"
                + "groupId=" + groupId
                + ", fullName='" + fullName + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTOlite that = (GroupDTOlite) o;
        return groupId == that.groupId
                && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, fullName);
    }
}
