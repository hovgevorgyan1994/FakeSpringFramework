package com.fakespring.infrastructure.config;

import org.reflections.Reflections;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public interface Config {
  <T> Class<? extends T> getImplClass(Class<T> ifc);

  Reflections getScanner();
}
