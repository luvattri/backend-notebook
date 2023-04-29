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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date schduledat;
    private Long notificationTiming;
    private Date createdat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo createdby;
    private Date updatedat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo updatedby;
}
