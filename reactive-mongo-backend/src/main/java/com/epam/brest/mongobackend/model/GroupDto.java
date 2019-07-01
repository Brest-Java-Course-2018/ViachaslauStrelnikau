package com.epam.brest.mongobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    /**
     * Property groupId.
     */
    @Id
    private String groupId;
    /**
     * Property shortName.
     */
    @Size(min = 2, max = 50)
    private String shortName;
    /**
     * Property fullName.
     */
    @Size(min = 2, max = 255)
    private String fullName;
    /**
     * Property description.
     */
    @Size(min = 2, max = 255)
    private String description;
}
