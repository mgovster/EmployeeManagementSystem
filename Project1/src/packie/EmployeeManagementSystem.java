package packie;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class EmployeeManagementSystem{
	private File daFile;
	private ArrayList<Employee> allEmps;
	
	public EmployeeManagementSystem(File f){
		daFile = f;
		allEmps = new ArrayList<Employee>();
	}
	public void setEmployees(ArrayList<Employee> emp) {
		allEmps = emp;
	}
	
	public void addEmployee() throws EmployeeException, EmployeeIDException{
		Scanner in = new Scanner(System.in);
		Employee myEmp = new Employee();
		try { 
			System.out.println("Enter Employee's first name: "); 
			myEmp.firstName = in.nextLine();
			System.out.println("Enter Employee's last name: "); 
			myEmp.lastName = in.nextLine();
			System.out.println("Enter Employee's Department: "); 
			myEmp.department = in.nextLine();
			System.out.println("Enter Employee's age: "); 
			myEmp.age = in.nextInt();
			if(myEmp.age < 16) { throw new EmployeeException(); }
			System.out.println("Enter Employee's ID number: ");
			myEmp.ID = in.nextInt();
			if ((validID(myEmp.ID))!=-1) { throw new EmployeeIDException(); }
			
			
			
			
			//myEmp.department = in.nextLine();
			System.out.println("Enter Employee's salary: "); 
			myEmp.salary = in.nextDouble();
			
			
			
		} catch(InputMismatchException e) {
			System.out.println("Error in input. Exiting");
			e.printStackTrace();
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		} finally {
			
		}
		//-----------^input
		System.out.println("Adding employee Mr/Mrs/Ms: " + myEmp.lastName);
		allEmps.add(myEmp);
		System.out.println("Added");
				
	}
	
	public int validID(int myID) {
		int s = allEmps.size();
		for(int i=0; i < s; i++) {
			if(myID == allEmps.get(i).getID()) { return i; }
		}
		return -1;
	}
	public boolean removeEmployee() {
		System.out.print("Enter employee ID you wish to remove: ");
		Scanner in = new Scanner(System.in);
		int remID = in.nextInt();
		int pos;
		if((pos=validID(remID)) != -1) {
			Employee cc = allEmps.get(pos);
			allEmps.remove(cc);
			return true;
		}
		return false;
	}
	
	public void updateEmployee() throws EmployeeException{
		//enter employee ID to find
		int myID;
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter Employee ID to update: ");
			
			myID = in.nextInt();
			int choice=-1;
			boolean facts = false;
			Scanner innie;
			for(int i=0; i< allEmps.size();i++) {
				if(allEmps.get(i).getID() == myID) {
					System.out.println("Employee Found: " + allEmps.get(i).printEmployee());
					System.out.println("Select Option to change: ");
					System.out.println("1: Change first name");
					System.out.println("2: Change last name");
					System.out.println("3: Change Department");
					System.out.println("4: Change Salary");
					System.out.println("5: Change Age");
					System.out.println("6: Update Classification Level");
					choice = in.nextInt();
					switch(choice) {
					case 1:
						innie = new Scanner(System.in);
						System.out.println("Enter new First Name");
						allEmps.get(i).firstName = (innie.nextLine());
						break;
					case 2:
						innie = new Scanner(System.in);
						System.out.println("Enter new Last Name");
						allEmps.get(i).lastName = innie.nextLine();
						break;
					case 3:
						innie = new Scanner(System.in);
						System.out.println("Enter new Department");
						allEmps.get(i).department = innie.nextLine();
						break;
					case 4:
						innie = new Scanner(System.in);
						System.out.println("Enter new Salary");
						allEmps.get(i).salary = innie.nextInt();
						break;
					case 5:
						innie = new Scanner(System.in);
						System.out.println("Enter new Age");
						allEmps.get(i).age = innie.nextInt();
						if(allEmps.get(i).age < 16) { throw new EmployeeException(); }
						break;
					case 6:
						innie = new Scanner(System.in);
						System.out.println("Enter Updated Security Level: ");
						allEmps.get(i).classLevel = innie.nextInt();
						break;
						
					}
					
					System.out.println("\nInformation Updated");
					System.out.println(allEmps.get(i).printEmployee());
					i=allEmps.size();
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void readFromFile(){
		System.out.println("<--Reading From File-->");
		//where we will read from file
		ArrayList<Employee> emps = new ArrayList<Employee>();
		
		ObjectInputStream reader = null;
		try{ reader = new ObjectInputStream(new FileInputStream(daFile));
			try {
				emps = (ArrayList<Employee>) reader.readObject();
			} catch (EOFException e) {
				emps = new ArrayList<Employee>();
		}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		allEmps = emps;
		
	}
	
	public void printAllEmps(){
		System.out.println("<--Printing all Employees-->");
		int cc=allEmps.size();
		for(int i=0; i< cc;i++) {
			System.out.println(allEmps.get(i).printEmployee());
		}
		System.out.println("<--End Printing all Employees-->");
	}
	
	public static void writeObjectsToFile(EmployeeManagementSystem ems) {
		System.out.println("<--Writing Objects to file-->");
		ObjectOutputStream writer = null;
		ArrayList<Employee> emps = null;
		try {
			writer = new ObjectOutputStream(new FileOutputStream(ems.daFile));
			emps = (ArrayList<Employee>)ems.allEmps;
			if(emps != null)
				writer.writeObject(emps);
			
			writer.flush();
			writer.close();
			System.out.println("Success: Wrote/overwrote objects to objectFile");
		}catch(NullPointerException e) {
			;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws EmployeeException, EmployeeIDException {
		// TODO Auto-generated method stub
		File file = new File("res/employees.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("File Created");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("<== IOException: Could not create file 'objectFile' ==>");
			}
		} 
		EmployeeManagementSystem empie = new EmployeeManagementSystem(file);
		//-----------------------------------------------
		//at first run (here) we read the objects from the file (put it into the global arraylist)
				
		empie.readFromFile();
		
		//------------------------------------------------
		//add while loop to be interactive with the file until user exits
		
		Scanner in = new Scanner(System.in);
		int option=0;
		helpMe();
		try {
			while(option >= 0) {
				
				System.out.println("\nEnter your Operation: ");
				
				option = in.nextInt();
				switch(option) {
				case 0:
					System.out.println("<--Saving your work-->");
					//save employee here
					writeObjectsToFile(empie);
					option = -1;
					System.out.println("Thank you. Goodbye");
					break;
				case 1:
					System.out.println("<--All Current Employees: ");
					empie.printAllEmps();
					break;
				case 2: 
					System.out.println("<--Updating Employee-->");
					empie.updateEmployee();
					break;
				case 3:
					helpMe();
					break;
				case 4: 
					System.out.println("<--Removing Employee-->");
					empie.removeEmployee();
					break;
				case 5: 
					System.out.println("<--Adding an Employee-->");
					empie.addEmployee();
					break;
				}
				
				
					
			}
		}catch(InputMismatchException e) {
			e.printStackTrace();
		}finally {
			;
		}
		
		
	}
	public static void helpMe() {
		System.out.println("[help]");
		System.out.println("0: Save and Quit");
		System.out.println("1: Print all Employees");
		System.out.println("2: Update an Employee");
		System.out.println("3: Help with commands");
		System.out.println("4: Remove an Employee");
		System.out.println("5: Add an Employee");
	}
}
