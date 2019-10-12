package com.sut.sa.g13.Controller;

import com.sut.sa.g13.Entity.*;
import com.sut.sa.g13.Repository.*;
//import com.okta.springbootvue.Repository.RegisterRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
// import java.util.Date;
// import java.sql.Timestamp;
// import java.util.Optional;
import java.util.stream.Collectors;

// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.text.ParseException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {
    @Autowired
	public CustomerRepository customerRepository;
    @Autowired
	public GenderRepository genderRepository;
    @Autowired
	public ProvinceRepository provinceRepository;
    @Autowired
    public CategoryRepository categoryRepository;
    
    public CustomerController(CustomerRepository customerRepository,
                               GenderRepository genderRepository,
                               ProvinceRepository provinceRepository,
                               CategoryRepository categoryRepository){

                                   this.customerRepository = customerRepository;
                                   this.genderRepository = genderRepository;
                                   this.provinceRepository = provinceRepository;
                                   this.categoryRepository = categoryRepository;
                               }
    //Gender
    // @GetMapping("/gender")
    // public Collection<Gender>Gender() {
    //     return genderRepository.findAll().stream().collect(Collectors.toList());
    // }
    // @GetMapping("/gender/{genderId}")
    // public Optional<Gender>Gender(@PathVariable Long genderId){
    //     Optional<Gender>gender = genderRepository.findByGenderId(genderId);
    //     return gender;
    // }
    
    //Province
    // @GetMapping("/province")
    // public Collection<Province>Province() {
    //     return provinceRepository.findAll().stream().collect(Collectors.toList());
    // }
    // @GetMapping("/province/{provinceId}")
    // public Optional<Province>Province(@PathVariable Long provinceId){
    //     Optional<Province>province = provinceRepository.findByProvinceId(provinceId);
    //     return province;
    // }
    
    //Category
    @GetMapping("/category")
    public Collection<Category>category() {
        return categoryRepository.findAll().stream().collect(Collectors.toList());
    }
    // @GetMapping("/category/{categoryId}")
    // public Optional<Category>Category(@PathVariable Long category){
    //     Optional<Category>category = categoryRepository.findByCategoryId(categoryId);
    //     return category;
    // }
    
    //Customer
    @GetMapping("/customer")
    public Collection<Customer>customer() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping("/customer/{id}")
    public Optional<Customer> Customers(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }
    // @GetMapping("/register/{registerId}")
    // public Optional<Register>Register(@PathVariable Long registerId){
    //     Optional<Register>register = registerRepository.findByRegisterId(registerId);
    //     return register;
    // }

    @PostMapping("/customer/{customername}/{genderid}/{address}/{provinceid}/{telephone}/{categoryid}")
            public Customer newCustomer(Customer newCustomer,
                                    @PathVariable String customername, //edit
                                    @PathVariable Long genderid,
                                    @PathVariable String address,
                                    @PathVariable Long provinceid,
                                    @PathVariable String telephone,
                                    @PathVariable Long categoryid //edit
	                                ){
    
    Gender gender = genderRepository.findByGenderid(genderid);
    Province province = provinceRepository.findByProvinceid(provinceid);
    Category category = categoryRepository.findByCategoryid(categoryid); //edit

    newCustomer.setCustomername(customername); //edit
    newCustomer.setGenderid(gender);
    newCustomer.setAddress(address);
    newCustomer.setProvinceid(province);
    newCustomer.setTelephone(telephone);
    newCustomer.setCategoryid(category); //edit
	    return customerRepository.save(newCustomer);
	    
    }
                                                                                    

}
