package com.fakespring.infrastructure.factory;

import com.fakespring.infrastructure.ApplicationContext;
import com.fakespring.infrastructure.bpp.ObjectConfigurator;
import com.fakespring.infrastructure.bpp.ProxyConfigurator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class ObjectFactory {

  private final ApplicationContext context;
  private final List<ObjectConfigurator> configurators = new ArrayList<>();
  private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

  @SneakyThrows
  public ObjectFactory(ApplicationContext context) {
    this.context = context;
    for (Class<? extends ObjectConfigurator> aClass :
        context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
      configurators.add(aClass.getDeclaredConstructor().newInstance());
    }
    for (Class<? extends ProxyConfigurator> aClass :
        context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
      proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
    }
  }

  public <T> T createObject(Class<T> implClass) {
    T t = create(implClass);

    configure(t);

    invokeInit(implClass, t);

    t = wrapWithProxyIfNeeded(implClass, t);

    return t;
  }

  private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
    for (ProxyConfigurator proxyConfigurator: proxyConfigurators) {
      t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
    }
    return t;
  }


  @SneakyThrows
  private <T> void invokeInit(Class<T> implClass, T t) {
    for (Method method : implClass.getMethods()) {
      if (method.isAnnotationPresent(PostConstruct.class)) {
        method.invoke(t);
      }
    }
  }

  private <T> void configure(T t) {
    configurators.forEach(config -> config.configure(t, context));
  }

  @SneakyThrows
  private <T> T create(Class<T> type) {
    return type.getDeclaredConstructor().newInstance();
  }
}
