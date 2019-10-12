package com.sut.sa.g13.Repository;
import com.sut.sa.g13.Entity.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BorrowRepository extends JpaRepository<Borrow, Long> {
    //Borrow findByborrowId (Long borrowId);
}
