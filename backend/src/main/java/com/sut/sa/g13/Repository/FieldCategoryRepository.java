package com.sut.sa.g13.Repository;
import com.sut.sa.g13.Entity.FieldCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface FieldCategoryRepository extends JpaRepository<FieldCategory, Long> {
    FieldCategory findByFieldid(Long fieldid );
}