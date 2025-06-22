package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "book_image")
@Data
@NoArgsConstructor
public class BookImage {

    @Id
    @Column(name = "book_image_uid")
    private String bookImageUid;
    /**
     * Store URLs of the images with ' ; ' as Delimiter.
     */
    private String urls;
}
