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
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="RESERVATION")
public class Reservation {

    @Id
    @SequenceGenerator(name="Reservation_seq",sequenceName="Reservation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Reservation_seq")
    @Column(name = "RESERVATION_ID", unique = true, nullable = true)
    private @NonNull Long reservationid;
    @Temporal(TemporalType.DATE)
    private @NonNull Date reservedate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FieldCategory.class)
    @JoinColumn(name = "FIELDCATEGORY_ID", insertable = true)
    private FieldCategory fieldcategory;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TimeTable.class)
    @JoinColumn(name = "TIMETABLE_ID", insertable = true)
    private TimeTable timetable;

}