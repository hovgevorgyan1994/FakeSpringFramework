package com.fakespring.infrastructure.bpp.impl;

import com.fakespring.infrastructure.bpp.ProxyConfigurator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Hovhannes Gevorgyan on 02.09.2022
 */
public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

  @Override
  public Object replaceWithProxyIfNeeded(Object t, Class<?> implClass) {
    if (implClass.isAnnotationPresent(Deprecated.class)) {
      return Proxy.newProxyInstance(
          implClass.getClassLoader(),
          implClass.getInterfaces(),
          (proxy, method, args) -> getInvocationHandlerLogic(implClass.getName(), method, t, args));

    } else {
      return t;
    }
  }

  private Object getInvocationHandlerLogic(String implClass, Method method, Object t, Object[] args)
      throws IllegalAccessException, InvocationTargetException {
    System.out.println("DEPRECATED CLASS: " + implClass);
    return method.invoke(t, args);
  }
}
