package com.backendnotebook.common.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Date createdat;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn
    private List<Note> notes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo createdby;
    private Date updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo updatedby;

}
