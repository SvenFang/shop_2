package com.cqgk.clerk.delegate;

import de.greenrobot.event.EventBus;

/**
 * 
 * @author duke
 *
 */
public class EventDelegate {

    public void registerEventBus(Object subscriber) {
        try {
            EventBus.getDefault().register(subscriber);
        } catch (Exception e) {
        }
    }

    public void unregisterEventBus(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }
}
