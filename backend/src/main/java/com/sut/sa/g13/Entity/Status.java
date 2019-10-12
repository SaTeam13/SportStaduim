package com.sut.sa.g13.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="STATUS")
public class Status {
    @Id    
    @SequenceGenerator(name="status_SEQ",sequenceName="status_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="status_SEQ")  
    @Column(name="STATUS_ID",unique = true, nullable = true)
    private @NonNull Long statusid;
    private @NonNull String typestatus;

  
}