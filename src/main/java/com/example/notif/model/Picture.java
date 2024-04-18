package com.example.notif.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "picture")
@Entity
@Builder
public class Picture {
    @Id
    @Column(name = "id")
    String id;

    @Column(name = "nama_file")
    String filename;

    @Column(name = "picture", columnDefinition = "MEDIUMBLOB")
    String image;
}
