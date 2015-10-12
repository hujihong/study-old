package com.github.json;

public class Exam {

	private String SUBJECT;
    private double GRADE;
    
	public String getSUBJECT() {
		return SUBJECT;
	}

	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}

	public double getGRADE() {
		return GRADE;
	}

	public void setGRADE(double gRADE) {
		GRADE = gRADE;
	}

	@Override
	public String toString() {
		return "Exam [SUBJECT=" + SUBJECT + ", GRADE=" + GRADE + "]";
	}
    
}
