package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.CustomerType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
	CustomerType findByCustomertypeid(Long customertypeid);
}