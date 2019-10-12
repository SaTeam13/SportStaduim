package com.sut.sa.g13.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENTMEMBER")
public class PaymentMember {
    @Id
    @SequenceGenerator(name="PaymentMember_seq",sequenceName="PaymentMember_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="PaymentMember_seq")
    @Column(name = "PAYMENTMEMBER_ID", unique = true, nullable = true)
    private @NonNull Long paymentid;
    
    @Temporal(TemporalType.DATE)
    @Column(name="PAY_DATE")
    private @NonNull Date payDate;
   
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customerid; //edit

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerType.class)
    @JoinColumn(name = "CUSTOMER_TYPE", insertable = true)
    private CustomerType customertypeid; //edit
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TimeRange.class)
    @JoinColumn(name = "TIME_RANGE", insertable = true)
    private TimeRange timerangeid;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employeeid; //edit

   

}

