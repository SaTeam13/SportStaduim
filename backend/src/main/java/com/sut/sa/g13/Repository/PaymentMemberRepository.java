package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.PaymentMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PaymentMemberRepository extends JpaRepository<PaymentMember, Long> {
    
}