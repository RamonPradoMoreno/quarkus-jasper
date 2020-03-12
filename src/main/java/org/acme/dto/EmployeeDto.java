package org.acme.dto;

/**
 * EmployeeDto
 */
public class EmployeeDto {
    public String name;
    public String surname;
    public float salary;

    public EmployeeDto() {
	}
	public EmployeeDto(String name, String surname, float salary) {
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
    
}