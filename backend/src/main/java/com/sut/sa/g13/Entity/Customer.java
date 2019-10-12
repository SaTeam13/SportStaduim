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
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq")
    @GeneratedValue(generator = "customer_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="CUSTOMER_ID",unique = true, nullable = true)
 
    private @NonNull Long customerid;
    private @NonNull String customername; //edit
    private @NonNull String address;
    private @NonNull String telephone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name="GENDER_ID", insertable = true)
    private Gender genderid;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name="PROVINCE_ID", insertable = true)
    private Province provinceid;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name="CATEGORY_ID", insertable = true)
    private Category categoryid; 
}