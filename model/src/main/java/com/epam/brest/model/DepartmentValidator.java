package com.epam.brest.model;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class DepartmentValidator {
    public boolean supports(Class clazz) {
        return Department.class.equals(clazz);
    }
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "departmentName", "departmentName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "description", "description.empty");

        Department p = (Department) obj;
        if (p.getDepartmentName().length() < 2) {
            e.rejectValue("departmentName", "To short name");
        } else if (p.getDepartmentName().length()  > 254) {
            e.rejectValue("departmentName", "To long name");
        }
        if (p.getDepartmentName().length() < 2) {
            e.rejectValue("description", "To short description");
        } else if (p.getDepartmentName().length()  > 254) {
            e.rejectValue("description", "To long description");
        }

    }
}
