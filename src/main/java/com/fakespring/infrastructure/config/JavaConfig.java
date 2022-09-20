package com.fakespring.infrastructure.config;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class JavaConfig implements Config {

  @Getter private final Reflections scanner;
  private final Map<Class, Class> ifcToImplClass;

  public JavaConfig(String packageToScan, Map<Class, Class> ifcToImplClass) {
    scanner = new Reflections(packageToScan);
    this.ifcToImplClass = ifcToImplClass;
  }

  @Override
  public <T> Class<? extends T> getImplClass(Class<T> ifc) {
    return ifcToImplClass.computeIfAbsent(
        ifc,
        aClass -> {
          Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
          if (classes.size() != 1) {
            throw new IllegalStateException(
                ifc + " has 0 or more than 1 impl please update your config");
          }
          return classes.iterator().next();
        });
  }
}
