package com.example.notif.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notif.model.Picture;

@Repository
public interface PictureRepo extends JpaRepository<Picture, String> {

}
