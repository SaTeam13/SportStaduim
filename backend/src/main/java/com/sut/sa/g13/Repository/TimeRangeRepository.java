package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TimeRangeRepository extends JpaRepository<TimeRange, Long> {
	TimeRange findByTimerangeid(long timerangeid);
}