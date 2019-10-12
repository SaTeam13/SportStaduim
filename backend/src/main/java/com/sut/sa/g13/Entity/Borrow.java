package com.sut.sa.g13.Entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Data
@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name="BORROW")
public class Borrow {
    @Id
	@SequenceGenerator(name="borrow_seq",sequenceName="borrow_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="borrow_seq")
	@Column(name="BORROW_ID",unique = true, nullable = true)
    
    private @NonNull Long borrowid;
    private @NonNull String nameborrow;
    private @NonNull Integer numberequipment;
    @Temporal(TemporalType.DATE)
    private @NonNull Date borrowdate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEEE_ID" , insertable = true)
    private Employee employeename;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SportEquipment.class)
    @JoinColumn(name = "EQUIMENT_ID" , insertable = true)
    private SportEquipment equipment;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeEquipment.class)
    @JoinColumn(name = "TYPEEQUIPMENT_ID" , insertable = true)
    private  TypeEquipment typeequipmentid;

	
   
   

	
}