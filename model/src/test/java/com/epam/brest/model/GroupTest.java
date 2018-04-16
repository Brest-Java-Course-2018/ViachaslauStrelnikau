package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Test Group class.
 */
public class GroupTest {

    private static int GROUPID =1;
    private static String SHORTNAME = "test";
    private static String FULLNAME = "test group";
    private static String DESCRIPTION = "description";

    /**
     * Method grouptest tests all methods of Group class.
     */
    @Test
    public void grouptest()
    {
        Group group = new Group();
        group.setGroupId(GROUPID);
        group.setShortName(SHORTNAME);
        group.setFullName(FULLNAME);
        group.setDescription(DESCRIPTION);

        Assert.assertEquals(group.getGroupId(),GROUPID);
        Assert.assertEquals(group.getShortName(),SHORTNAME);
        Assert.assertEquals(group.getFullName(),FULLNAME);
        Assert.assertEquals(group.getDescription(),DESCRIPTION);
        String toString= "Group{"
                + "groupId=" + GROUPID
                + ", shortName='" + SHORTNAME + '\''
                + ", fullName='" + FULLNAME + '\''
                + ", description=" + DESCRIPTION
                + '}';
        Assert.assertEquals(group.toString(),toString);

        Group group1 = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        group1.setGroupId(GROUPID);

        Assert.assertEquals(group,group1);
        Assert.assertEquals(group1.hashCode(), Objects.hash(GROUPID,SHORTNAME,FULLNAME,DESCRIPTION));
    }

}