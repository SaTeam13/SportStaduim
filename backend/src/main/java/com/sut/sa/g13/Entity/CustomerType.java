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
@Table(name="CUSTOMERTYPE")
public class CustomerType {

    @Id
    @SequenceGenerator(name="customertype_seq",sequenceName="customertype_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customertype_seq")  
    @Column(name = "CUSTOMERTYPE_ID", unique = true, nullable = true)
    private @NonNull Long customertypeid;
    private @NonNull String customertypename;
}
