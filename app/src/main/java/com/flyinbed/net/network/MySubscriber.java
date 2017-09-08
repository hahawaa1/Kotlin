package com.flyinbed.net.network;

import rx.Observer;
import rx.Producer;
import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * 作者：Administrator on 2017/9/5 10:03
 * 邮箱：zhanghuaiha@gmail.com
 */

public abstract class MySubscriber<T> implements Observer<T>, Subscription {

    // represents requested not set yet
    private static final Long NOT_SET = Long.MIN_VALUE;

    private final SubscriptionList subscriptions;
    private MySubscriber<?> subscriber;
    /* protected by `this` */
    private Producer producer;
    /* protected by `this` */
    private long requested = NOT_SET; // default to not set

    protected MySubscriber() {
        this(null, false);
    }

    protected MySubscriber(MySubscriber<?> subscriber) {
        this(subscriber, true);
    }


    protected MySubscriber(MySubscriber<?> subscriber, boolean shareSubscriptions) {
        this.subscriber = subscriber;
        this.subscriptions = shareSubscriptions && subscriber != null ? subscriber.subscriptions : new SubscriptionList();
    }


    public final void add(Subscription s) {
        subscriptions.add(s);
    }

    @Override
    public  void unsubscribe() {
        subscriptions.unsubscribe();
    }


    @Override
    public  boolean isUnsubscribed() {
        return subscriptions.isUnsubscribed();
    }


    public void onStart() {
        // do nothing by default
    }


    protected final void request(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + n);
        }

        // if producer is set then we will request from it
        // otherwise we increase the requested count by n
        Producer producerToRequestFrom = null;
        synchronized (this) {
            if (producer != null) {
                producerToRequestFrom = producer;
            } else {
                addToRequested(n);
                return;
            }
        }
        // after releasing lock (we should not make requests holding a lock)
        producerToRequestFrom.request(n);
    }

    private void addToRequested(long n) {
        if (requested == NOT_SET) {
            requested = n;
        } else {
            final long total = requested + n;
            // check if overflow occurred
            if (total < 0) {
                requested = Long.MAX_VALUE;
            } else {
                requested = total;
            }
        }
    }


    public void setProducer(Producer p) {
        long toRequest;
        boolean passToSubscriber = false;
        synchronized (this) {
            toRequest = requested;
            producer = p;
            if (subscriber != null) {
                // middle operator ... we pass through unless a request has been made
                if (toRequest == NOT_SET) {
                    // we pass through to the next producer as nothing has been requested
                    passToSubscriber = true;
                }
            }
        }
        // do after releasing lock
        if (passToSubscriber) {
            subscriber.setProducer(producer);
        } else {
            // we execute the request with whatever has been requested (or Long.MAX_VALUE)
            if (toRequest == NOT_SET) {
                producer.request(Long.MAX_VALUE);
            } else {
                producer.request(toRequest);
            }
        }
    }
}

