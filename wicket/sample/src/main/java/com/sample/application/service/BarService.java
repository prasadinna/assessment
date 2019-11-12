package com.sample.application.service;

import org.springframework.stereotype.Service;

@Service
public class BarService implements IBarService {

  @Override
  public String createMessage() {
    return "Hello, Spring Boot + Wicket!";
  }

}