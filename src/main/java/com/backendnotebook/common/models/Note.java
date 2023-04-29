package com.backendnotebook.common.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String title;
    private Date createdat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo createdby;
    private Date updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo updatedby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Notebook notebook;
}
