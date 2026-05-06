package com.task.MedicalAnalysisAI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.task.Model.DiseaseResult;
import com.task.Model.MedicalResult;

@Service
public class MedicalReportAnalysisAI {

    public MedicalResult analyzeMedicalReport(String text) {

        text = text.toLowerCase();

        MedicalResult result = new MedicalResult();
        List<DiseaseResult> diseases = new ArrayList<>();

        int healthScore = 100;
        int highRiskCount = 0;
        int moderateRiskCount = 0;

        // =========================
        // GLUCOSE
        // =========================
        double glucose = extractValue(text, "glucose|sugar");
        if (glucose != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Diabetes Risk");

            if (glucose > 180) {
                d.setRiskLevel("High");
                healthScore -= 25;
                highRiskCount++;
            } else if (glucose >= 140) {
                d.setRiskLevel("Moderate");
                healthScore -= 15;
                moderateRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("Glucose: " + glucose + " mg/dL");

            d.setRecommendations(List.of(
                "Reduce sugar and refined carbs",
                "Eat whole grains and fiber-rich foods",
                "Exercise daily (30 mins walk)",
                "Monitor glucose regularly",
                "Drink 2-3L water daily"
            ));

            diseases.add(d);
        }

        // =========================
        // CHOLESTEROL
        // =========================
        double cholesterol = extractValue(text, "cholesterol");
        if (cholesterol != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Cholesterol");

            if (cholesterol > 240) {
                d.setRiskLevel("High");
                healthScore -= 25;
                highRiskCount++;
            } else if (cholesterol >= 200) {
                d.setRiskLevel("Moderate");
                healthScore -= 15;
                moderateRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("Cholesterol: " + cholesterol + " mg/dL");

            d.setRecommendations(List.of(
                "Avoid fried foods",
                "Eat oats, nuts, fruits",
                "Do cardio exercise",
                "Reduce fast food",
                "Use healthy oils"
            ));

            diseases.add(d);
        }

        // =========================
        // HEMOGLOBIN
        // =========================
        double hb = extractValue(text, "hemoglobin|haemoglobin");
        if (hb != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Hemoglobin");

            if (hb < 10) {
                d.setRiskLevel("High");
                healthScore -= 20;
                highRiskCount++;
            } else if (hb < 13) {
                d.setRiskLevel("Moderate");
                healthScore -= 10;
                moderateRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("Hemoglobin: " + hb + " g/dL");

            d.setRecommendations(List.of(
                "Eat iron-rich foods (spinach, beetroot)",
                "Include dates and jaggery",
                "Take Vitamin B12",
                "Avoid tea/coffee after meals"
            ));

            diseases.add(d);
        }

        // =========================
        // TSH
        // =========================
        double tsh = extractValue(text, "tsh|thyroid");
        if (tsh != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Thyroid");

            if (tsh > 4.5 || tsh < 0.4) {
                d.setRiskLevel("High");
                healthScore -= 20;
                highRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("TSH: " + tsh);

            d.setRecommendations(List.of(
                "Use iodized salt",
                "Manage stress",
                "Regular testing",
                "Sleep properly"
            ));

            diseases.add(d);
        }

        // =========================
        // CREATININE
        // =========================
        double creatinine = extractValue(text, "creatinine");
        if (creatinine != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Kidney Function");

            if (creatinine > 1.5) {
                d.setRiskLevel("High");
                healthScore -= 20;
                highRiskCount++;
            } else if (creatinine > 1.2) {
                d.setRiskLevel("Moderate");
                healthScore -= 10;
                moderateRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("Creatinine: " + creatinine + " mg/dL");

            d.setRecommendations(List.of(
                "Drink more water",
                "Reduce salt intake",
                "Avoid excess protein",
                "Regular checkups"
            ));

            diseases.add(d);
        }

        // =========================
        // WBC
        // =========================
        double wbc = extractValue(text, "wbc|white blood cell");
        if (wbc != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("WBC Count");

            if (wbc > 11000 || wbc < 4000) {
                d.setRiskLevel("High");
                healthScore -= 20;
                highRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("WBC: " + wbc);

            d.setRecommendations(List.of(
                "Maintain hygiene",
                "Eat immunity foods",
                "Rest properly",
                "Stay hydrated"
            ));

            diseases.add(d);
        }

        // =========================
        // PLATELETS
        // =========================
        double platelets = extractValue(text, "platelet");
        if (platelets != -1) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("Platelets");

            if (platelets < 150000) {
                d.setRiskLevel("High");
                healthScore -= 20;
                highRiskCount++;
            } else if (platelets < 200000) {
                d.setRiskLevel("Moderate");
                healthScore -= 10;
                moderateRiskCount++;
            } else {
                d.setRiskLevel("Normal");
            }

            d.setDescription("Platelets: " + platelets);

            d.setRecommendations(List.of(
                "Eat fruits like kiwi and papaya",
                "Avoid injuries",
                "Stay hydrated",
                "Monitor regularly"
            ));

            diseases.add(d);
        }

        // DEFAULT
        if (diseases.isEmpty()) {
            DiseaseResult d = new DiseaseResult();
            d.setDiseaseName("General Health");
            d.setRiskLevel("Normal");
            d.setDescription("No abnormal values detected.");
            d.setRecommendations(List.of(
                "Maintain healthy diet",
                "Exercise daily",
                "Regular checkups"
            ));
            diseases.add(d);
        }

        if (healthScore < 0) healthScore = 0;

        result.setHealthScore(healthScore);

        // STATUS
        if (healthScore >= 85) result.setOverallStatus("Excellent");
        else if (healthScore >= 70) result.setOverallStatus("Good");
        else if (healthScore >= 50) result.setOverallStatus("Moderate Risk");
        else result.setOverallStatus("Critical");

        // OVERALL RISK
        String overallRiskLevel;

        if (healthScore < 50 || highRiskCount >= 2) overallRiskLevel = "Critical";
        else if (healthScore < 70 || highRiskCount == 1) overallRiskLevel = "High";
        else if (moderateRiskCount >= 2) overallRiskLevel = "Moderate";
        else overallRiskLevel = "Low";

        result.setOverallRiskLevel(overallRiskLevel);

        // GENERAL RECOMMENDATIONS
        if (overallRiskLevel.equals("Critical") || overallRiskLevel.equals("High")) {
            result.setGeneralRecommendations(List.of(
                "Consult doctor immediately",
                "Strict diet control",
                "Avoid junk food",
                "Regular monitoring",
                "Proper sleep"
            ));
        } else if (overallRiskLevel.equals("Moderate")) {
            result.setGeneralRecommendations(List.of(
                "Healthy lifestyle",
                "Balanced diet",
                "Exercise daily",
                "Reduce sugar & salt",
                "Routine checkups"
            ));
        } else {
            result.setGeneralRecommendations(List.of(
                "Maintain current lifestyle",
                "Stay active",
                "Eat balanced diet",
                "Drink water",
                "Annual checkups"
            ));
        }

        // DIET PLAN
        result.setDietPlan(List.of(
            "Breakfast: Oats / Fruits",
            "Lunch: Brown rice + vegetables + dal",
            "Dinner: Light meal + salad",
            "Snacks: Nuts & fruits",
            "Avoid: Sugar, fried, junk food"
        ));

        result.setSummary("AI-based medical analysis completed successfully.");
        result.setDiseases(diseases);

        return result;
    }

    private double extractValue(String text, String keywordPattern) {
        Pattern pattern = Pattern.compile("(" + keywordPattern + ")\\s*[:=]?\\s*(\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) return Double.parseDouble(matcher.group(2));
        return -1;
    }
}