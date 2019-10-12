package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    Customer findByCustomerid(Long customerid); //edit
  
    
}