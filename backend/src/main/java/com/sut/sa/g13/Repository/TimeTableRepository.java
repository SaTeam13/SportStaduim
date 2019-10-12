package com.sut.sa.g13.Repository;
import com.sut.sa.g13.Entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    TimeTable findByTimetableid(Long timetableid );
}