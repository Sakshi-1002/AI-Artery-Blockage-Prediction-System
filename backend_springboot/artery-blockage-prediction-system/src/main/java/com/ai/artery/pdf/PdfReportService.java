package com.ai.artery.pdf;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfReportService {

    public byte[] generateReport(
            String riskLevel,
            double confidence)
            throws Exception {

        Document document =
                new Document();

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        PdfWriter.getInstance(
                document,
                out);

        document.open();

        Font titleFont =
                new Font(
                        Font.HELVETICA,
                        20,
                        Font.BOLD);

        document.add(
                new Paragraph(
                        "AI-Based Artery Blockage Prediction Report",
                        titleFont));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "Risk Level : "
                                + riskLevel));

        document.add(
                new Paragraph(
                        "Confidence Score : "
                                + confidence));

        document.add(
                new Paragraph(" "));

        document.add(
                new Paragraph(
                        "This report was generated using a Machine Learning model."));

        document.add(
                new Paragraph(
                        "It should not be considered a substitute for professional medical advice."));

        document.close();

        return out.toByteArray();
    }
}