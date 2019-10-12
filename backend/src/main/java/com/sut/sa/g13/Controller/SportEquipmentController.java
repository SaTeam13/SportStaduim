package com.sut.sa.g13.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.sa.g13.Entity.*;
import com.sut.sa.g13.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SportEquipmentController {
    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TypeEquipmentRepository typeEquipmentRepository;

        public SportEquipmentController(SportEquipmentRepository sportEquipmentRepository,
        EmployeeRepository employeeRepository,
        TypeEquipmentRepository typeEquipmentRepository,
        StatusRepository statusRepository  ) {
    this.sportEquipmentRepository  = sportEquipmentRepository;        
    this.employeeRepository = employeeRepository;
    this.typeEquipmentRepository = typeEquipmentRepository;
    this.statusRepository = statusRepository;

}

        @GetMapping("/sportEquipment")
        public Collection<SportEquipment> sportEquipment(){
            return sportEquipmentRepository.findAll().stream().collect(Collectors.toList());
        }

        @GetMapping("/sportEquipment/{id}")
        public Optional<SportEquipment> sportEquipment(@PathVariable Long id) {
            Optional<SportEquipment> sportEquipment = sportEquipmentRepository.findById(id);
            return sportEquipment;
        }

     

        @GetMapping("/status")
        public Collection<Status> status(){
            return statusRepository.findAll().stream().collect(Collectors.toList());
        }

        @GetMapping("/typeEquipment")
        public Collection<TypeEquipment> typeEquipment(){
            return typeEquipmentRepository.findAll().stream().collect(Collectors.toList());
        }
        @DeleteMapping("/sportEquipment/{id}")
        public ResponseEntity<String> deleteSportEquipment(@PathVariable("id") long id) {
            System.out.println("Delete SportEquipment with ID = " + id + "...");
    
            sportEquipmentRepository.deleteById(id);
    
            return new ResponseEntity<>("SportEquipment has been deleted!", HttpStatus.OK);
        }
    


        @PostMapping("/sportEquipment/{employeename}/{typeequipmentid}/{equipment}/{typestatus}/{number}")
        public SportEquipment newsportEquipment(SportEquipment newsportEquipment,
                @PathVariable Long employeename, //edit
                @PathVariable Long typeequipmentid,
                @PathVariable Long typestatus, //edit
                @PathVariable String equipment,
                @PathVariable Integer number
                ) {
                    
                    Employee employee = employeeRepository.findByEmployeeid(employeename); //edit
                    TypeEquipment typeEquipment = typeEquipmentRepository.findByTypeequipmentid(typeequipmentid);
                    Status status = statusRepository.findBystatusid(typestatus);//edit

                    newsportEquipment.setEmployeeid(employee); //edit
                    newsportEquipment.setTypeequipmentid(typeEquipment);
                    newsportEquipment.setEquipment(equipment);
                    newsportEquipment.setTypestatus(status);
                    newsportEquipment.setNumber(number);
                    return sportEquipmentRepository.save(newsportEquipment);

            }

}

