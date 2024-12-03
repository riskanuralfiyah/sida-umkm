package com.SIDA.UMKM.service;

import com.SIDA.UMKM.entities.Jabatan;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PdfExportJabatanService {
    ByteArrayInputStream exportJabatanToPdf(List<Jabatan> jabatans);
}
