package com.task.Model;

import java.util.List;

public class DiseaseResult 
{
	    private String diseaseName;
	    private String riskLevel;
	    private String description;
	    private List<String> recommendations;

	    public String getDiseaseName() {
	        return diseaseName;
	    }

	    public void setDiseaseName(String diseaseName) {
	        this.diseaseName = diseaseName;
	    }

	    public String getRiskLevel() {
	        return riskLevel;
	    }

	    public void setRiskLevel(String riskLevel) {
	        this.riskLevel = riskLevel;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public List<String> getRecommendations() {
	        return recommendations;
	    }

	    public void setRecommendations(List<String> recommendations) {
	        this.recommendations = recommendations;
	    }
}
