package com.management.reports;

import com.management.models.Transaction;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    public void generateReport(List<Transaction> transactions) {
        try {
            // Muat file .jasper dari folder resources
            InputStream reportStream = getClass().getClassLoader().getResourceAsStream("Blank_A4.jasper");
            
            if (reportStream == null) {
                throw new JRException("File Blank_A4.jasper tidak ditemukan di folder resources.");
            }

            // Data source untuk laporan
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);

            // Parameter laporan (jika ada)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Financial Report");

            // Isi laporan dengan data dan parameter
            JasperPrint print = JasperFillManager.fillReport(reportStream, parameters, dataSource);

            // Tampilkan laporan menggunakan viewer
            JasperViewer.viewReport(print, false);

        } catch (JRException e) {
            // Tangani error jika file atau proses laporan gagal
            System.err.println("Error saat menghasilkan laporan: " + e.getMessage());
        }
    }
}
