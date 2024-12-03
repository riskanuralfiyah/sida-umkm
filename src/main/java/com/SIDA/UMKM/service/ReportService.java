package com.SIDA.UMKM.service;

import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    byte[] generateReport(String reportName, Map<String, Object> parameters) throws JRException, SQLException;
}

