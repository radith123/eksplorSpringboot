package com.example.notif.sevice;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.example.notif.model.Nama;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class Jasper {
    @Value("${jasper.puas.path}")
    private String jasperPuasPath;

    @Value("${jasper.nama.path}")
    private String jasperNamaPath;

    @Value("${jasper.bio.path}")
    private String jasperBioPath;

    public ByteArrayResource cetakTemplate() throws JRException {
        // Compile Jasper Report
        JasperReport jasperReport = loadTemplate(jasperNamaPath);

        // Buat Parameter
        Nama nama = Nama.builder()
                .namaAwal("Radith")
                .namaAkhir("Fadillah")
                .keterangan("Halo")
                .id("01").build();
        Map<String, Object> parameters = parameters(nama);

        // Buat empty DataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Data"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return new ByteArrayResource(pdfBytes);
    }

    public JasperReport loadTemplate(String templatePath) throws JRException {
        InputStream inputStream = getClass().getResourceAsStream(templatePath);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        return JasperCompileManager.compileReport(jasperDesign);
    }

    public Map<String, Object> parameters(Nama nama) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("namaAwal", nama.getNamaAwal());
        parameters.put("namaAkhir", nama.getNamaAkhir());
        parameters.put("keterangan", nama.getKeterangan());
        parameters.put("id", nama.getId());
        return parameters;
    }

    public ByteArrayResource cetakTemplateAsPDf() throws JRException {
        // Compile Jasper Report
        JasperReport jasperReport = loadTemplate(jasperPuasPath);

        // Buat Parameter
        Nama nama = Nama.builder()
                .namaAwal("Radith")
                .namaAkhir("Fadillah")
                .keterangan("Halo")
                .id("01").build();
        Map<String, Object> parameters = parameters(nama);

        // Buat empty DataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Data"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        return new ByteArrayResource(pdfBytes);
    }

    public ByteArrayResource cetakTemplateBio() throws JRException {
        // Compile Jasper Report
        JasperReport jasperReport = loadTemplate(jasperBioPath);

        // Buat Parameter
        Date lahir = new Date(21, 3, 16);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nama", "Radith");
        parameters.put("alamat", "Desa");
        parameters.put("tempatLahir", "Bandung");
        parameters.put("tanggalLahir", lahir);
        parameters.put("umur", 17);
        parameters.put("harga", 2.9);
        parameters.put("golDarah", 'O');

        // Buat empty DataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Data"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return new ByteArrayResource(pdfBytes);
    }

}
