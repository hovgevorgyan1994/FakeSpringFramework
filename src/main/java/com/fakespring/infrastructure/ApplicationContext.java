package com.fakespring.infrastructure;

import com.fakespring.infrastructure.config.Config;
import com.fakespring.infrastructure.factory.ObjectFactory;
import com.fakespring.annotation.Singleton;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class ApplicationContext {

  @Setter
  private ObjectFactory objectFactory;
  private final Map<Class, Object> cache = new ConcurrentHashMap<>();
  @Getter
  private final Config config;

  public ApplicationContext(Config config) {
    this.config = config;
  }

  public <T> T getObject(Class<T> type) {
    if (cache.containsKey(type)) {
      return (T) cache.get(type);
    }

    Class<? extends T> implClass = type;
    if (type.isInterface()) {
      implClass = config.getImplClass(type);
    }

    T t = objectFactory.createObject(implClass);

    if (implClass.isAnnotationPresent(Singleton.class)) {
      cache.put(type, t);
    }
    return t;
  }
}
