package com.fakespring.service.impl;

import com.fakespring.annotation.InjectProperty;
import com.fakespring.annotation.Singleton;
import com.fakespring.service.Recommendator;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
@Singleton
@Deprecated
public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String alcohol;

    public RecommendatorImpl(){
    System.out.println("recomendator was created");
    }
    @Override
    public void recommend() {
    System.out.println("drink " + alcohol);
    }
}
