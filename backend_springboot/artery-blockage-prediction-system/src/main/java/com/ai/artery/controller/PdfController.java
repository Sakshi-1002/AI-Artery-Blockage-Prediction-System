package com.ai.artery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.artery.pdf.PdfReportService;

@RestController
public class PdfController {

    @Autowired
    private PdfReportService pdfReportService;

    @GetMapping("/download-report")
    public ResponseEntity<byte[]> downloadReport(
            @RequestParam String riskLevel,
            @RequestParam double confidence)
            throws Exception {

        byte[] pdf =
                pdfReportService.generateReport(
                        riskLevel,
                        confidence);

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename(
                                "AI_Artery_Report.pdf")
                        .build());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdf);
    }
}