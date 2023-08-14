package com.jpa.demo.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.demo.model.JPAModel;


@Repository
public interface JPARepository extends JpaRepository<JPAModel, String> {

}
