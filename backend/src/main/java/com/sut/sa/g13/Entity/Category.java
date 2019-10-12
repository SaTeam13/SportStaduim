package com.sut.sa.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category {

    @Id
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq")
    @GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="CATEGORY_ID",unique = true, nullable = true)

    private @NonNull Long categoryid;
    private @NonNull String type; //edit
    
}