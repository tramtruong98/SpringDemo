package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CRUDRepository<T> extends JpaRepository<T, Long> {
	List<T> findAll();
	Optional<T> findById(Long id);
    void deleteById(Long id) ;
}
