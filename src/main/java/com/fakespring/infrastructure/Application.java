package com.fakespring.infrastructure;

import com.fakespring.infrastructure.config.Config;
import com.fakespring.infrastructure.config.JavaConfig;
import com.fakespring.infrastructure.factory.ObjectFactory;
import java.util.Map;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class Application {

  public static ApplicationContext run(String packageToScan, Map<Class, Class> ifcToImplClass) {
    Config javaConfig = new JavaConfig(packageToScan, ifcToImplClass);
    ApplicationContext context = new ApplicationContext(javaConfig);
    ObjectFactory objectFactory = new ObjectFactory(context);
    context.setObjectFactory(objectFactory);
    return context;
  }
}
