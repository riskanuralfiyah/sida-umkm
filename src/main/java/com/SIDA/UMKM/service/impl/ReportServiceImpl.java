package com.SIDA.UMKM.service.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SIDA.UMKM.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private DataSource dataSource;

    @Override
    public byte[] generateReport(String reportName, Map<String, Object> parameters) throws JRException, SQLException {
        // Load template .jasper file
        InputStream reportStream = getClass().getResourceAsStream("/reports/" + reportName + ".jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

        // Fill report with data (using JDBC)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
