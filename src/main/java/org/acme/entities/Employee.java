package org.acme.entities;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Employee extends PanacheEntity{
    public String name;
    public String surname;
    public float salary;
	
	public Employee(){}
    
    
    public static Employee findByName(String name){
        return find("name", name).firstResult();
    }
    
    public static void remove(String name){
        delete("name", name);
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


	public Employee(String name, String surname, float salary) {
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}
}