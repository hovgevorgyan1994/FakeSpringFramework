package com.fakespring;

import com.fakespring.infrastructure.Application;
import com.fakespring.infrastructure.ApplicationContext;
import com.fakespring.service.CoronaDesinfector;
import com.fakespring.service.Policeman;
import com.fakespring.service.impl.PolicemanImpl;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class Main {

  public static void main(String[] args) {
    ApplicationContext context =
        Application.run("com.fakespring", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
    context.getObject(CoronaDesinfector.class).start();
  }
}
