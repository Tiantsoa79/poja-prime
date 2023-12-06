package com.poja.prime.endpoint.rest.controller;

import com.poja.prime.repository.PrimeRepository;
import com.poja.prime.repository.model.PrimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@RestController
public class PrimeController {

  private static final int NUM_PRIMES_TO_KEEP = 10;

  @Autowired
  private PrimeRepository primeRepository;
  @GetMapping("/new-prime")
  public String getNewPrime() {
    BigInteger prime = BigInteger.probablePrime(1000, new Random());
    saveGeneratedPrime(prime);
    return prime.toString();
  }


  @GetMapping("/generated-primes")
  public List<String> getGeneratedPrimes() {
    List<PrimeEntity> primeEntities = primeRepository.findTopNPrimes(NUM_PRIMES_TO_KEEP);
    return primeEntities.stream()
      .map(PrimeEntity::getPrimeNumber)
      .toList();
  }

  private void saveGeneratedPrime(BigInteger prime) {
    try {
      PrimeEntity primeEntity = new PrimeEntity();
      primeEntity.setPrimeNumber(prime.toString());
      primeRepository.save(primeEntity);

      List<PrimeEntity> allPrimes = primeRepository.findAll();
      if (allPrimes.size() > NUM_PRIMES_TO_KEEP) {
        PrimeEntity oldestPrime = allPrimes.get(0);
        primeRepository.delete(oldestPrime);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
