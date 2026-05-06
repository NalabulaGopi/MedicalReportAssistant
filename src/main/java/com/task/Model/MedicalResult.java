package com.task.Model;

import java.util.List;

public class MedicalResult 
{
	    private int healthScore;
	    private String overallStatus;
	    private String summary;
	    private List<DiseaseResult> diseases;
	    private String overallRiskLevel;
	    private List<String> generalRecommendations;
	    private List<String> dietPlan;
	    public int getHealthScore() {
	        return healthScore;
	    }

	    public void setHealthScore(int healthScore) {
	        this.healthScore = healthScore;
	    }

	    public String getOverallStatus() {
	        return overallStatus;
	    }

	    public void setOverallStatus(String overallStatus) {
	        this.overallStatus = overallStatus;
	    }

	    public String getSummary() {
	        return summary;
	    }

	    public void setSummary(String summary) {
	        this.summary = summary;
	    }

	    public List<DiseaseResult> getDiseases() {
	        return diseases;
	    }

	    public void setDiseases(List<DiseaseResult> diseases) {
	        this.diseases = diseases;
	    }
	    public String getOverallRiskLevel() {
	        return overallRiskLevel;
	    }

		public void setOverallRiskLevel(String overallRiskLevel) {
			this.overallRiskLevel=overallRiskLevel;
		}  
		
		public List<String> getgeneralRecommendations()
		{
		   return  generalRecommendations;
		}
		public void setGeneralRecommendations(List<String> generalRecommendations)
		{
			this.generalRecommendations=generalRecommendations;
		}
		
		public List<String> getDietPlan()
		{
			return dietPlan;
		}
		
		public void setDietPlan(List<String>dietPlan)
		{
			this.dietPlan=dietPlan;
		}
		
}
