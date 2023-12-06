package com.poja.prime.endpoint.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.poja.prime.conf.FacadeIT;
public class PrimeControllerIT extends FacadeIT {
  @Autowired PrimeController primeController;

  @Test
  void newPrime() {
    assertNotNull(primeController.getNewPrime());
  }

  @Test
  void generatedPrime() {
    assertEquals(10, primeController.getGeneratedPrimes().size());
  }


}