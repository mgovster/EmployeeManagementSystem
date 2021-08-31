package packie;

import java.io.Serializable;

public class ClassifiedPerson implements Serializable{
	/**
	 * 
	 */
	
	protected int classLevel;
	public ClassifiedPerson(int n) {
		classLevel = n;
	}
	public int getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(int newLevel) {
		classLevel = newLevel;
	}
	public String getLevelStatus() {
		switch(classLevel) {
		case 0: case 1: 
			return "Beginner";
		case 2: case 3:
			return "Intermediate";
		case 4: case 5:
			return "Advanced";
		case 6: case 7:
			return "Master";
		}
		return "Not Applicable";
	}
}
