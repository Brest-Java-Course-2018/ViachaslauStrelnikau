package com.epam.brest.mongobackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTOlite {
    /**
     * Property groupId.
     */
    private String groupId;
    /**
     * Property fullName.
     */
    private String fullName;
}
