package com.fakespring.infrastructure.bpp.impl;

import static java.util.stream.Collectors.toMap;

import com.fakespring.annotation.InjectProperty;
import com.fakespring.infrastructure.ApplicationContext;
import com.fakespring.infrastructure.bpp.ObjectConfigurator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.SneakyThrows;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {
  private final Map<String, String> propertiesMap;

  @SneakyThrows
  public InjectPropertyAnnotationObjectConfigurator() {
    String path =
        Objects.requireNonNull(
                ClassLoader.getSystemClassLoader().getResource("application.properties"))
            .getPath();
    path = path.replace("%20", " ");
    try (Stream<String> lines = new BufferedReader(new FileReader(path)).lines()) {
      this.propertiesMap =
          lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }
  }

  @Override
  @SneakyThrows
  public void configure(Object t, ApplicationContext context) {
    Class<?> implClass = t.getClass();
    for (Field field : implClass.getDeclaredFields()) {
      InjectProperty annotation = field.getAnnotation(InjectProperty.class);
      if (annotation != null) {
        String value =
            annotation.value().isEmpty()
                ? propertiesMap.get(field.getName())
                : propertiesMap.get(annotation.value());
        field.setAccessible(true);
        field.set(t, value);
      }
    }
  }
}
