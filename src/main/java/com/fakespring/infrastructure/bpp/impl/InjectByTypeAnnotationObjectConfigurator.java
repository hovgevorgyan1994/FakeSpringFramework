package com.fakespring.infrastructure.bpp.impl;

import com.fakespring.annotation.InjectByType;
import com.fakespring.infrastructure.ApplicationContext;
import com.fakespring.infrastructure.bpp.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {


  @Override
  @SneakyThrows
  public void configure(Object t, ApplicationContext context) {
    for (Field field : t.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(InjectByType.class)) {
        field.setAccessible(true);
        Object object = context.getObject(field.getType());
        field.set(t, object);
      }
    }
  }
}
