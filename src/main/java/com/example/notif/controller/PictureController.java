package com.example.notif.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.notif.dto.PictureReqDto;
import com.example.notif.model.Picture;
import com.example.notif.sevice.PictureService;

@RestController
@RequestMapping("/picture")
public class PictureController {
    PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(value = "/{picId}")
    public ResponseEntity<Picture> getPicture(@PathVariable("picId") String id) {
        return ResponseEntity.ok(pictureService.getPicture(id));
    }

    @GetMapping(value = "/p/{picId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getOnlyPicture(@PathVariable("picId") String id) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bite = decoder.decode(pictureService.getPicture(id).getImage());

        return ResponseEntity.ok(bite);
    }

    @PostMapping
    public ResponseEntity<Picture> postPicture(@RequestPart("req") PictureReqDto req,
            @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(pictureService.addPicture(req, file));
    }
}
