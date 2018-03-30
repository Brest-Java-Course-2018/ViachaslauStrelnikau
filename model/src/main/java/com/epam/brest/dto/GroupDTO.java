package com.epam.brest.dto;

import java.util.Objects;

/**
 * GroupDTO class
 */
public class GroupDTO {
    /**
     * Property groupId.
     */
    private int groupId;
    /**
     * Property shortName.
     */
    private String shortName;
    /**
     * Property fullName.
     */
    private String fullName;
    /**
     * Property group average marks .
     */
    private double groupAvgMarks;

    /**
     * Group class constructor with params
     *
     * @param groupId       id of group
     * @param shortName     short name of group
     * @param fullName      full name of group
     * @param groupAvgMarks average marks of dtudents ib the group
     */
    public GroupDTO(final int groupId, final String shortName, final String fullName, final double groupAvgMarks) {
        this.groupId = groupId;
        this.shortName = shortName;
        this.fullName = fullName;
        this.groupAvgMarks = groupAvgMarks;
    }

    /**
     * Group class constructor with params
     */
    public GroupDTO() {
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

    public double getGroupAvgMarks() {
        return groupAvgMarks;
    }

    public void setGroupId(final int groupId) {
        this.groupId = groupId;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setGroupAvgMarks(final double groupAvgMarks) {
        this.groupAvgMarks = groupAvgMarks;
    }

    @Override
    public String toString() {
        return "GroupDTO{"
                + "groupId=" + groupId
                + ", shortName='" + shortName + '\''
                + ", fullName='" + fullName + '\''
                + ", groupAvgMarks=" + groupAvgMarks
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return groupId == groupDTO.groupId
                && Double.compare(groupDTO.groupAvgMarks, groupAvgMarks) == 0
                && Objects.equals(shortName, groupDTO.shortName)
                && Objects.equals(fullName, groupDTO.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, shortName, fullName, groupAvgMarks);
    }
}
