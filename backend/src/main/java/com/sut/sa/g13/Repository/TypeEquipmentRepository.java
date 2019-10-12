package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface TypeEquipmentRepository extends JpaRepository<TypeEquipment, Long>{
    TypeEquipment findByTypeequipmentid(Long typeequipmentid);

   
}
