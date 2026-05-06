package com.task.service;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.Tesseract;

@Service
public class PdfService {

    public String extractText(MultipartFile file) {

        File tempFile = null;
        PDDocument document = null;

        try {

            // 1. Create temp file
            tempFile = File.createTempFile("medical-report", ".pdf");
            file.transferTo(tempFile);

            // 2. Load PDF
            document = Loader.loadPDF(tempFile);

            // 3. Try normal text extraction first
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            System.out.println("NORMAL PDF TEXT:");
            System.out.println(text);

            // If normal text is found → return it
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }

            // 4. OCR fallback (scanned PDF)
            PDFRenderer renderer = new PDFRenderer(document);

            Tesseract tesseract = new Tesseract();

            // ⚠ IMPORTANT FIX: must point to parent folder, NOT tessdata directly
            tesseract.setDatapath("C:/Program Files/Tesseract-OCR");

            // Optional: language
            tesseract.setLanguage("eng");

            StringBuilder extractedText = new StringBuilder();

            for (int i = 0; i < document.getNumberOfPages(); i++) {

                BufferedImage image = renderer.renderImageWithDPI(i, 300);

                String ocrText = tesseract.doOCR(image);

                extractedText.append(ocrText).append("\n");
            }

            System.out.println("OCR TEXT:");
            System.out.println(extractedText);

            return extractedText.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return "Unable to analyze report";

        } finally {

            try {
                if (document != null) document.close();
                if (tempFile != null && tempFile.exists()) tempFile.delete();
            } catch (Exception ignored) {}

        }
    }
}