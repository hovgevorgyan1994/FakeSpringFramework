package com.fakespring.service.impl;

import com.fakespring.annotation.InjectByType;
import com.fakespring.service.Policeman;
import com.fakespring.service.Recommendator;
import javax.annotation.PostConstruct;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class PolicemanImpl implements Policeman {

  @InjectByType
  private Recommendator recommendator;

  @PostConstruct
  public void init() {
    System.out.println(recommendator.getClass());
  }

  @Override
  public void makePeopleLeaveRoom() {
    System.out.println("bix bax get out");
  }
}
