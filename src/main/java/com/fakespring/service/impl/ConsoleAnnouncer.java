package com.fakespring.service.impl;

import com.fakespring.annotation.InjectByType;
import com.fakespring.service.Announcer;
import com.fakespring.service.Recommendator;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;

    @Override
    public void announce(String msg) {
    System.out.println(msg);
    recommendator.recommend();
    }
}
