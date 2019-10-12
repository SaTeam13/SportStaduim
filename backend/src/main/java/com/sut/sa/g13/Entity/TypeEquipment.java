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
@NoArgsConstructor
@Table(name="TYPEEQUIPMENT")
public class TypeEquipment {
	@Id
	@SequenceGenerator(name="typeequipment_seq",sequenceName="typeequipment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typeequipment_seq")
	@Column(name="TYPEEQUIMENT_ID",unique = true, nullable = true)
	private @NonNull Long typeequipmentid;
	private @NonNull String typename;

}