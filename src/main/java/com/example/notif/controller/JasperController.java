package com.example.notif.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notif.sevice.Jasper;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/jasper")
public class JasperController {
    Jasper jasper;

    @Autowired
    public JasperController(Jasper jasper) {
        this.jasper = jasper;
    }

    @GetMapping("/nama")
    public ResponseEntity<ByteArrayResource> cetakTemplateSuratPuas() throws JRException {
        ByteArrayResource resource = jasper.cetakTemplate();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + "template_nama.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/nama/pdf")
    public ResponseEntity<ByteArrayResource> cetakTemplateSuratPuasPdf() throws JRException, IOException {
        ByteArrayResource resource = jasper.cetakTemplateBio();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + "template_nama.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/product")
    public ResponseEntity<ByteArrayResource> cetakProductImg() throws JRException, IOException {
        ByteArrayResource resource = jasper.cetakProductWithImage();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + "product.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
