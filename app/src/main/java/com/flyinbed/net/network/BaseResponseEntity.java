package com.flyinbed.net.network;

import java.io.Serializable;

/**
 *
 */
public class BaseResponseEntity<T> implements Serializable {

        private int count;
        private int start;
        private int total;
        private String title;
        public T subjects;
}
