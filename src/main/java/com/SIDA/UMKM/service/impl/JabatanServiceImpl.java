//package com.SIDA.UMKM.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.SIDA.UMKM.entities.Jabatan;
//import com.SIDA.UMKM.repository.JabatanRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class JabatanServiceImpl {
//
//    @Autowired
//    private JabatanRepository jabatanRepository;
//
//    public Optional<Jabatan> findById(Long id) {
//        return jabatanRepository.findById(id);
//    }
//
//    public Jabatan saveJabatan(Jabatan jabatan) {
//        return jabatanRepository.save(jabatan);
//    }
//
//    public void deleteJabatan(Long id) {
//        jabatanRepository.deleteById(id);
//    }
//
//    public List<Jabatan> findAllJabatan() {
//        return jabatanRepository.findAll();
//    }
//}

package com.SIDA.UMKM.service.impl;

import com.SIDA.UMKM.entities.Jabatan;
import com.SIDA.UMKM.repository.JabatanRepository;
import com.SIDA.UMKM.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JabatanServiceImpl implements JabatanService {

    @Autowired
    private JabatanRepository jabatanRepository;

    @Override
    public List<Jabatan> findAllJabatan() {
        return jabatanRepository.findAll();
    }

    @Override
    public Jabatan saveJabatan(Jabatan jabatan) {
        return jabatanRepository.save(jabatan);
    }

    @Override
    public Optional<Jabatan> updateJabatan(Long id) {
        return jabatanRepository.findById(id);
    }

    @Override
    public void deleteJabatan(Long id) {
        jabatanRepository.deleteById(id);
    }
    
    @Override
    public Optional<Jabatan> findById(Long id) {
        return jabatanRepository.findById(id);
    }
}

