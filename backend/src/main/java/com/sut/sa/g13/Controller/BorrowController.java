package com.sut.sa.g13.Controller;

import com.sut.sa.g13.Entity.*;
import com.sut.sa.g13.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
//import java.sql.Timestamp;
//import java.util.Optional;
import java.util.stream.Collectors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.text.ParseException;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BorrowController {
    @Autowired
	public BorrowRepository borrowRepository;
    @Autowired
	public EmployeeRepository employeeRepository;
    @Autowired
	public SportEquipmentRepository sportEquipmentRepository;
    @Autowired
	public TypeEquipmentRepository typeEquipmentRepository;

    public BorrowController(BorrowRepository borrowRepository,
                        EmployeeRepository employeeRepository,
                        SportEquipmentRepository sportEquipmentRepository,
                        TypeEquipmentRepository typeEquipmentRepository) {

        this.borrowRepository = borrowRepository;
        this.employeeRepository = employeeRepository;
        this.sportEquipmentRepository = sportEquipmentRepository;
        this.typeEquipmentRepository = typeEquipmentRepository;
    }
 
    @GetMapping(path = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Borrow> getborrow() {
        return borrowRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/borrow/{id}")
        public Optional<Borrow> borrow(@PathVariable Long id) {
            Optional<Borrow> borrow = borrowRepository.findById(id);
        return borrow;
        }

    @DeleteMapping("/borrow/{id}")
	    public ResponseEntity<String> deleteborrow(@PathVariable("id") long id) {
		System.out.println("Delete borrow with ID = " + id + "...");

		borrowRepository.deleteById(id);

		return new ResponseEntity<>("borrow has been deleted!", HttpStatus.OK);
	    }
   

    @PostMapping(path ="/Borrow/{nameborrow}/{numberequipment}/{dateyyyymmdd}/{empselect}/{eqmselect}/{typeeqmselect}")
            public Borrow newBorrow(Borrow newBorrow,@PathVariable String nameborrow,
                                    @PathVariable Integer numberequipment,
                                    @PathVariable String dateyyyymmdd,
                                    @PathVariable Long empselect,
                                    @PathVariable Long eqmselect,
                                    @PathVariable Long typeeqmselect
	                                )throws ParseException {
	
                                       
	    
	    Employee employee = employeeRepository.findByEmployeeid(empselect); 
	    TypeEquipment typeEquipment = typeEquipmentRepository.findByTypeequipmentid(typeeqmselect);
        SportEquipment sportEquipment = sportEquipmentRepository.findBysportequipmentid(eqmselect);
        
         DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
	 	  Date b = newDate.parse(dateyyyymmdd); 
	
	    Borrow borrow = new Borrow(); 
        borrow.setNameborrow(nameborrow);
        borrow.setNumberequipment(numberequipment);
        borrow.setBorrowdate(b);
        borrow.setEmployeename(employee); 
        borrow.setEquipment(sportEquipment);
        borrow.setTypeequipmentid(typeEquipment);
	    return borrowRepository.save(borrow);
	    
    }
}

