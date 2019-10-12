package com.sut.sa.g13.Entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="SPORTEQUIPMENT")
public class SportEquipment {
	@Id
	@SequenceGenerator(name="sportequipment_seq",sequenceName="sportequipment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sportequipment_seq")
	@Column(name="SPORTEQUIPMENT_ID",unique = true, nullable = true)
   
    private @NonNull Long sportequipmentid;
    private @NonNull String equipment;
    private @NonNull Integer number;
    
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE")
    private Employee employeeid;

    @ManyToOne
    @JoinColumn(name = "TYPEEQUIMENT")
    private TypeEquipment typeequipmentid;

    @ManyToOne
    @JoinColumn(name = "STATUS")
    private Status typestatus;





}