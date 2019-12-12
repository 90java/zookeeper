package com.nojava.one;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("----------------");

        System.out.println(watchedEvent.getPath());
        System.out.println(watchedEvent.getState());
        System.out.println(watchedEvent.getType());

        System.out.println("----------------");
    }
}
