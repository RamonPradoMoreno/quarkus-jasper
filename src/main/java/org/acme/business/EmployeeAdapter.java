package org.acme.business;

import org.acme.dto.EmployeeDto;
import org.acme.entities.Employee;

/**
 * EmployeeAdapter
 */
public class EmployeeAdapter {

    public static EmployeeDto adapt(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setName(entity.getName());
        dto.setSalary(entity.getSalary());
        dto.setSurname(entity.getSurname());
        return dto;
        
    }
}