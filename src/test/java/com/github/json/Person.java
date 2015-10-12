package com.github.json;

public class Person {

	private String NAME;
    private String LOCATION;
    private Exam EXAM;
    
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public Exam getEXAM() {
		return EXAM;
	}
	public void setEXAM(Exam eXAM) {
		EXAM = eXAM;
	}
	@Override
	public String toString() {
		return "Person [NAME=" + NAME + ", LOCATION=" + LOCATION + ", EXAM=" + EXAM + "]";
	}

    
}
