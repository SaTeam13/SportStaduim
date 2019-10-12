package com.sut.sa.g13.Repository;
import com.sut.sa.g13.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationRepository extends JpaRepository<Reservation, Long> {
}