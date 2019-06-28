package com.epam.brest.mongobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Group class
 */
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Group {
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

    private List<Student> students;
}
