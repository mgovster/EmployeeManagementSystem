package packie;

public class EmployeeIDException extends Exception {


	private static final long serialVersionUID = -8784657250600794671L;
	public EmployeeIDException() {
		super("Employee ID is already in use");
		
	}
}
