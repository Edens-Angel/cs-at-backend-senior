package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "feeds")
public class Feed {

    public Feed(String title, String description, Date publishDate, String image) {
        this.title = title;
        this.description = description;
        this.publishDate = publishDate;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String description;

    @Column(name = "publish_date")
    private Date publishDate;

    private String image;

}
