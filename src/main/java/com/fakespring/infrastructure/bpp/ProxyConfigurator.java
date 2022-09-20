package com.fakespring.infrastructure.bpp;

/**
 * @author Hovhannes Gevorgyan on 02.09.2022
 */
public interface ProxyConfigurator {

  Object replaceWithProxyIfNeeded(Object t, Class<?> implClass);

}
