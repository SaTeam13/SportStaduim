package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long>{
    Category findByCategoryid(Long Categoryid);
}