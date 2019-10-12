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
@Table(name="BANK")
public class Bank {
	@Id
	@SequenceGenerator(name="bank_seq",sequenceName="bank_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bank_seq")
	@Column(name="BANK_ID",unique = true, nullable = true)
	private @NonNull Long bankid;
	private @NonNull String bankname;

}
