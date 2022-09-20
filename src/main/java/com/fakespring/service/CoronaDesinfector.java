package com.fakespring.service;

import com.fakespring.annotation.InjectByType;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class CoronaDesinfector {

  @InjectByType private Announcer announcer;
  @InjectByType private Policeman policeman;

  public void start() {
    announcer.announce("Get out");
    policeman.makePeopleLeaveRoom();
    disinfect();
    announcer.announce("Get in");
  }

  private void disinfect() {
    System.out.println("Starting disinfection");
  }
}
