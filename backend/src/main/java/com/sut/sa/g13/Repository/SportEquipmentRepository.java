package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.SportEquipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface SportEquipmentRepository extends JpaRepository<SportEquipment, Long>{
           SportEquipment findBysportequipmentid(Long sportequipmentid);                                                  
	
	
	
}