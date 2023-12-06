package com.poja.prime.repository;

import com.poja.prime.repository.model.PrimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrimeRepository extends JpaRepository<PrimeEntity, Long> {

  @Query(value = "SELECT * FROM prime_entity ORDER BY id DESC LIMIT ?1", nativeQuery = true)
  List<PrimeEntity> findTopNPrimes(int n);
}