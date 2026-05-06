package com.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.task.MedicalAnalysisAI.MedicalReportAnalysisAI;
import com.task.Model.MedicalResult;
import com.task.Model.UserDetails;
import com.task.service.PdfService;
import com.task.service.UserService;



@RestController
@RequestMapping("/medical")
@CrossOrigin(origins = "*")
public class MedicalAnalysisController 
{
   @Autowired
    private MedicalReportAnalysisAI serviceai;
    
   @Autowired
    private PdfService pdfservice;
   
   @PostMapping("/upload")
   public MedicalResult uploadPDF(@RequestParam("file") MultipartFile file) {

       try {

           String extractedText =
           pdfservice.extractText(file);

           System.out.println("RAW TEXT:");
           System.out.println(extractedText);

           // 🔥 CLEAN TEXT (IMPORTANT FIX)
           extractedText =
           extractedText
           .replaceAll("\n", " ")
           .replaceAll("\r", " ")
           .replaceAll("\\s+", " ")
           .toLowerCase();

           System.out.println("CLEAN TEXT:");
           System.out.println(extractedText);

           return serviceai.analyzeMedicalReport(extractedText);

       } catch (Exception e) {

           e.printStackTrace();

           return new MedicalResult();
       }
   }
  
}
