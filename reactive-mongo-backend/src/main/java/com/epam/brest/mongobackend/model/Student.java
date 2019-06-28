package com.epam.brest.mongobackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Student class
 */
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /**
     * Property studentId.
     */
    private String studentId;
    /**
     * Property studentName.
     */
    @Size(min = 2, max = 50)
    private String studentName;
    /**
     * Property studentBirth students date of birth.
     */
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Minsk")
    private Date studentBirth;
    /**
     * Property studentAvgMarks.
     */
    @DecimalMin("1")
    @DecimalMax("10")
    private double studentAvgMarks;


}
