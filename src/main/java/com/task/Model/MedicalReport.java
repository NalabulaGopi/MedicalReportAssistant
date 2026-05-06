package com.task.Model;


public class MedicalReport 
{
	private int age;
	private String sex;
	private int patientID;
    private double sugar;
    
    private double cholesterol;
    
    private double hemoglobin;
    
    private double bpsystolic;
    
    private double bpdiastolic;
    
    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public double getBpSystolic() {
        return bpsystolic;
    }

    public void setBpSystolic(double bpSystolic) {
        this.bpsystolic = bpSystolic;
    }

    public double getBpDiastolic() {
        return bpdiastolic;
    }

    public void setBpDiastolic(double bpDiastolic) {
        this.bpdiastolic = bpDiastolic;
    }
}
