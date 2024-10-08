package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import java.util.Date;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}
