package packie;

public class EmployeeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8366831248544686527L;
	
	public EmployeeException() {
		super("Must be over 16 to work\n");
			
	}
}
