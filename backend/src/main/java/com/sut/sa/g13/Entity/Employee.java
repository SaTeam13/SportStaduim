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
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long employeeid;
    private @NonNull String employeename;
    private @NonNull String idcardnumber;
    private @NonNull String address;
    private @NonNull String telephone;
    private @NonNull String banknum;
    private @NonNull String username;
    private @NonNull String password;

    @ManyToOne
    @JoinColumn(name = "GENDER")
    private Gender genderid;

    @ManyToOne
    @JoinColumn(name = "PROVINCE")
    private Province provinceid;

    @ManyToOne
    @JoinColumn(name = "POSITION")
    private Position positionid;

    @ManyToOne
    @JoinColumn(name = "BANKEMP")
    private Bank bankid;

}

