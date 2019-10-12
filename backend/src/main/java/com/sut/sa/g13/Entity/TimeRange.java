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
@Table(name="TIMERANGE")
public class TimeRange {

    @Id
    @SequenceGenerator(name="TimeRange_seq",sequenceName="TimeRange_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TimeRange_seq")  
    @Column(name = "TimeRange_ID", unique = true, nullable = true)
    
    private @NonNull Long timerangeid;
    private @NonNull String timerangename; 
}
