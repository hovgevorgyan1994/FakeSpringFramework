package com.fakespring.infrastructure.bpp;

import com.fakespring.infrastructure.ApplicationContext;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public interface ObjectConfigurator {

    void configure(Object t, ApplicationContext context);

}
