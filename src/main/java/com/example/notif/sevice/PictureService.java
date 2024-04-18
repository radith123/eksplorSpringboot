package com.example.notif.sevice;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.notif.dto.PictureReqDto;
import com.example.notif.model.Picture;
import com.example.notif.repositories.PictureRepo;

@Service
public class PictureService {
    PictureRepo pictureRepo;

    @Autowired
    public PictureService(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    public Picture addPicture(PictureReqDto req, MultipartFile multipartFile) throws IOException {
        Picture picture = Picture.builder()
                .id(req.getId())
                .filename(req.getFilename())
                .image(Base64.getEncoder().encodeToString(multipartFile.getBytes()))
                .build();
        pictureRepo.save(picture);
        return picture;
    }

    public Picture getPicture(String id) {
        Picture picture = pictureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " Not Found"));
        return picture;
    }
}
