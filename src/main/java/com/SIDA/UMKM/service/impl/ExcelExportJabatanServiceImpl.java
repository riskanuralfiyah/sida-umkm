package com.SIDA.UMKM.service.impl;

import com.SIDA.UMKM.entities.Jabatan;
import com.SIDA.UMKM.service.ExcelExportJabatanService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelExportJabatanServiceImpl implements ExcelExportJabatanService {

    @Override
    public ByteArrayInputStream exportJabatanToExcel(List<Jabatan> jabatans) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Daftar Jabatan");

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"No", "ID", "Nama Jabatan", "Deskripsi", "Foto"};

            // Create Header Style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Create Data Rows
            int rowIndex = 1;
            for (int i = 0; i < jabatans.size(); i++) {
                Jabatan jabatan = jabatans.get(i);
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(i + 1); // Nomor
                row.createCell(1).setCellValue(jabatan.getId()); // ID
                row.createCell(2).setCellValue(jabatan.getNamaJabatan()); // Nama Jabatan

                // Clean up Deskripsi by removing leading HTML tags (like <p>)
                String deskripsi = jabatan.getDeskripsi();
                if (deskripsi != null) {
                    deskripsi = deskripsi.replaceAll("<[^>]+>", "").trim();
                } else {
                    deskripsi = "";
                }
                row.createCell(3).setCellValue(deskripsi); // Deskripsi

                // Foto (Hanya teks path karena Excel tidak dapat menampilkan gambar secara langsung)
                String foto = jabatan.getPictJabatan() != null ? jabatan.getPictJabatan() : "Tidak ada foto";
                row.createCell(4).setCellValue(foto); // Foto
            }

            // Autosize columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Convert to ByteArrayInputStream
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
