package com.sut.sa.g13.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.ParseException;

import com.sut.sa.g13.Entity.Customer;
import com.sut.sa.g13.Entity.Employee;
import com.sut.sa.g13.Entity.CustomerType;
import com.sut.sa.g13.Entity.TimeRange;
import com.sut.sa.g13.Entity.PaymentMember;
import com.sut.sa.g13.Repository.CustomerRepository;
import com.sut.sa.g13.Repository.EmployeeRepository;
import com.sut.sa.g13.Repository.CustomerTypeRepository;
import com.sut.sa.g13.Repository.PaymentMemberRepository;
import com.sut.sa.g13.Repository.TimeRangeRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentMemberController {
    @Autowired
    private PaymentMemberRepository paymentmemberRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerTypeRepository customertypeRepository;
    @Autowired
    private TimeRangeRepository timerangeRepository;

    public PaymentMemberController (PaymentMemberRepository paymentmemberRepository,
    CustomerRepository customerRepository,EmployeeRepository employeeRepository, CustomerTypeRepository customertypeRepository, 
    TimeRangeRepository timerangeRepository ){
        this.paymentmemberRepository = paymentmemberRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.customertypeRepository = customertypeRepository;
        this.timerangeRepository =  timerangeRepository;
    }
    @GetMapping("/paymentmember")
    public Collection <PaymentMember>  PaymentMembers() {
        return paymentmemberRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/paymentmember/{id}")
    public Optional<PaymentMember> PaymentMembers(@PathVariable Long id) {
        Optional<PaymentMember> payment = paymentmemberRepository.findById(id);
        return payment;
    }
    @GetMapping("/customertype")
    public Collection<CustomerType> Customertypes() {
        return customertypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/customertype/{id}")
    public Optional<CustomerType> Customertypes(@PathVariable Long id) {
        Optional<CustomerType> customerType = customertypeRepository.findById(id);
        return customerType;
    }
    @GetMapping("/timerange")
    public Collection<TimeRange> Timerange() {
        return timerangeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/timerange/{id}")
    public Optional<TimeRange> Timerange(@PathVariable Long timeid) {
        Optional<TimeRange> timeRange = timerangeRepository.findById(timeid);
        return timeRange;
    }
    @DeleteMapping("/paymentmember/{id}")
	public ResponseEntity<String> deletePaymentMembers(@PathVariable("id") long id) {
		System.out.println("Delete Payment with ID = " + id + "...");
        
        paymentmemberRepository.deleteById(id);

		return new ResponseEntity<>("Payment has been deleted!", HttpStatus.OK);
	}

    @PostMapping("/paymentmember/{customer_id}/{employee_id}/{customertype_id}/{timerange_id}")
    public PaymentMember newPaymentMember(PaymentMember newPaymentMember,
    @PathVariable Long customer_id, 
    @PathVariable Long employee_id, 
    @PathVariable Long customertype_id,
    @PathVariable Long timerange_id)
    throws ParseException   {
        
    Customer customer = customerRepository.findByCustomerid(customer_id); //edit
    Employee employee = employeeRepository.findByEmployeeid(employee_id); //edit
    CustomerType customertype  = customertypeRepository.findByCustomertypeid(customertype_id); //edit
    TimeRange timerange = timerangeRepository.findByTimerangeid(timerange_id);

    newPaymentMember.setCustomerid(customer); //edit
    newPaymentMember.setEmployeeid(employee); //edit
    newPaymentMember.setCustomertypeid(customertype); //edit
    newPaymentMember.setTimerangeid(timerange);
    newPaymentMember.setPayDate(new Date());

    return paymentmemberRepository.save(newPaymentMember);
    
    }
}