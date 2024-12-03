//package com.SIDA.UMKM.service.impl;
//
//import com.SIDA.UMKM.entities.Jabatan;
//import com.SIDA.UMKM.service.PdfExportJabatanService;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class PdfExportJabatanServiceImpl implements PdfExportJabatanService {
//
//    @Override
//    public ByteArrayInputStream exportJabatanToPdf(List<Jabatan> jabatans) {
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            // Menambahkan judul
//            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
//            Paragraph para = new Paragraph("Daftar Jabatan", font);
//            para.setAlignment(Element.ALIGN_CENTER);
//            document.add(para);
//            document.add(Chunk.NEWLINE);
//
//            // Membuat tabel
//            PdfPTable table = new PdfPTable(4); // Tambahkan kolom untuk foto
//            table.setWidthPercentage(100);
//            table.setWidths(new int[]{1, 3, 3, 3}); // Sesuaikan lebar kolom
//
//            // Menambahkan header tabel
//            PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("ID", font));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Nama Jabatan", font));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Deskripsi", font));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//            
//            hcell = new PdfPCell(new Phrase("Foto", font)); // Header untuk kolom foto
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            // Menambahkan data ke tabel
//            for (Jabatan jabatan : jabatans) {
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(jabatan.getId().toString()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(jabatan.getNamaJabatan()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(jabatan.getDeskripsi()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                // Menambahkan foto ke tabel
//                if (jabatan.getPictJabatan() != null) {
//                    try {
//                        // Gunakan path relatif jika gambar disimpan di dalam resources/static
//                        String path = "src/main/resources/static/assets/images/jabatan/" + jabatan.getPictJabatan();
//                        Image img = Image.getInstance(path);
//                        img.scaleToFit(50, 50); // Mengatur ukuran gambar
//                        cell = new PdfPCell(img, true);
//                        cell.setPaddingLeft(5);
//                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        table.addCell(cell);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                        cell = new PdfPCell(new Phrase("Foto tidak ditemukan"));
//                        cell.setPaddingLeft(5);
//                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        table.addCell(cell);
//                    }
//                } else {
//                    cell = new PdfPCell(new Phrase("Tidak ada foto"));
//                    cell.setPaddingLeft(5);
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(cell);
//                }
//            }
//
//            document.add(table);
//            document.close();
//
//        } catch (DocumentException ex) {
//            ex.printStackTrace();
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }
//}


package com.SIDA.UMKM.service.impl;

import com.SIDA.UMKM.entities.Jabatan;
import com.SIDA.UMKM.service.PdfExportJabatanService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfExportJabatanServiceImpl implements PdfExportJabatanService {

    @Override
    public ByteArrayInputStream exportJabatanToPdf(List<Jabatan> jabatans) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Menambahkan judul
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph title = new Paragraph("DAFTAR JABATAN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Membuat tabel
            PdfPTable table = new PdfPTable(5); // Tambahkan kolom untuk nomor urut dan foto
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 2, 4, 4, 3}); // Sesuaikan lebar kolom

            // Menambahkan header tabel dengan format bold dan kapital
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("NO", headerFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("ID", headerFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("NAMA JABATAN", headerFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("DESKRIPSI", headerFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("FOTO", headerFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // Menambahkan data ke tabel
            int index = 1; // Nomor urut
            for (Jabatan jabatan : jabatans) {
                PdfPCell cell;

                // Nomor urut
                cell = new PdfPCell(new Phrase(String.valueOf(index++)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // ID Jabatan
                cell = new PdfPCell(new Phrase(jabatan.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // Nama Jabatan
                cell = new PdfPCell(new Phrase(jabatan.getNamaJabatan()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                // Deskripsi
                cell = new PdfPCell(new Phrase(jabatan.getDeskripsi().replaceAll("<p>", "").replaceAll("</p>", "")));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                // Foto
                if (jabatan.getPictJabatan() != null) {
                    try {
                        // Gunakan path relatif jika gambar disimpan di dalam resources/static
                        String path = "src/main/resources/static/assets/images/jabatan/" + jabatan.getPictJabatan();
                        Image img = Image.getInstance(path);
                        img.scaleToFit(100, 100); // Mengatur ukuran gambar agar tidak terlalu besar
                        cell = new PdfPCell(img, true);
                        cell.setPadding(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        cell = new PdfPCell(new Phrase("Foto tidak ditemukan"));
                        cell.setPadding(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                } else {
                    cell = new PdfPCell(new Phrase("Tidak ada foto"));
                    cell.setPadding(5);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

