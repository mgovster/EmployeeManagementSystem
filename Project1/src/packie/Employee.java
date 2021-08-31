package packie;

import java.io.Serializable;

public class Employee extends ClassifiedPerson implements Serializable{
	/**
	 * 
	 */
	
	protected int age, ID, classLevel;
	protected String firstName, lastName, department;
	protected double salary;
	public Employee() {
		super(0);
		age=16; firstName=null; lastName=null; salary=0; department = null; ID=0;
	}
	public Employee(String fname, String lname, int age, double sal, String department, int empID) {
		super(0);
		firstName = fname;
		lastName = lname;
		this.age = age;
		salary = sal;
		ID = empID;
		this.department = department;
		
	}
	public String printEmployee() {
		Employee emp;
		return (firstName + " " + lastName + " EmployeeID: " + ID + "  Age: " + 
				age + "  Salary: " + salary + "  Department: " + department + " Classification Level: " + getLevelStatus());
	}
	public int getID() {
		return ID;
	}
	public int getClassLevel() {
		return classLevel;
	}
		
}
