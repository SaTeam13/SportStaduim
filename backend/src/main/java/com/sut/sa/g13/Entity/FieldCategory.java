package com.sut.sa.g13.Entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor

@Table(name="FIELDCATEGORY")
public class FieldCategory {
    @Id
    @SequenceGenerator(name="FieldCategory_seq",sequenceName="FieldCategory_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FieldCategory_seq")  
    @Column(name = "FIELDCATEGORY_ID", unique = true, nullable = true)
    private @NonNull Long fieldid;
    private @NonNull String field;
}
