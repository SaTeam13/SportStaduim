package com.sut.sa.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Optional;
import com.sut.sa.g13.Entity.Customer;
import com.sut.sa.g13.Entity.Employee;
import com.sut.sa.g13.Entity.TimeTable;
import com.sut.sa.g13.Entity.FieldCategory;
import com.sut.sa.g13.Entity.Reservation;
import com.sut.sa.g13.Repository.CustomerRepository;
import com.sut.sa.g13.Repository.EmployeeRepository;
import com.sut.sa.g13.Repository.TimeTableRepository;
import com.sut.sa.g13.Repository.ReservationRepository;
import com.sut.sa.g13.Repository.FieldCategoryRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private FieldCategoryRepository fieldcategoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TimeTableRepository timetableRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public ReservationController(ReservationRepository reservationRepository,
    CustomerRepository customerRepository,
    FieldCategoryRepository fieldcategoryRepository,
    TimeTableRepository timetableRepository,
    EmployeeRepository employeeRepository) {
this.reservationRepository = reservationRepository;
this.customerRepository = customerRepository;
this.fieldcategoryRepository = fieldcategoryRepository;
this.timetableRepository = timetableRepository;
this.employeeRepository = employeeRepository;
}

    @GetMapping("/reservation")
    public Collection<Reservation> Reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/reservation/{id}")
    public Optional<Reservation> Reservations(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation;
    }

    @GetMapping("/fieldcate")
    public Collection<FieldCategory> FieldCategories() {
        return fieldcategoryRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/timetable")
    public Collection<TimeTable> TimeTables() {
        return timetableRepository.findAll().stream().collect(Collectors.toList());
    }
    @DeleteMapping("/reservation/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable("id") long id) {
		System.out.println("Delete Reservation with ID = " + id + "...");

		reservationRepository.deleteById(id);

		return new ResponseEntity<>("Reservation has been deleted!", HttpStatus.OK);
	}

    @PostMapping("/reservation/{customer_id}/{fieldcate_id}/{timetable_id}/{employee_id}/{date}")
    public Reservation newReservation(Reservation newReservation,
    @PathVariable long customer_id,
    @PathVariable long fieldcate_id,
    @PathVariable long timetable_id,
    @PathVariable long employee_id,
    @PathVariable String date) 
    throws ParseException  {
   
    Customer customer = customerRepository.findByCustomerid(customer_id);
    FieldCategory fieldcategory = fieldcategoryRepository.findByFieldid(fieldcate_id);
    TimeTable timetable = timetableRepository.findByTimetableid(timetable_id);
    Employee employee = employeeRepository.findByEmployeeid(employee_id);

        DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        Date d = newDate.parse(date); 
        
        newReservation.setCustomer(customer);
        newReservation.setFieldcategory(fieldcategory);
        newReservation.setTimetable(timetable);
        newReservation.setEmployee(employee);
        newReservation.setReservedate(d);

    return reservationRepository.save(newReservation);
    }
}