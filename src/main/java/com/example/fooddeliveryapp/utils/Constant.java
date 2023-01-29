package com.example.fooddeliveryapp.utils;

import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String ANYTHING = "Anything";
    public static final String DEFAULT = "Default";
    public static final String STATUS_NEW = "New";
    public static final String STATUS_IN_PROGRESS = "In progress";
    public static final String STATUS_CLOSE = "Close";
    public static final String STATUS_CANCELED = "Canceled";

    public static final String MSG_CHECKOUT_SUCCESSFULLY = "Checkout successfully";

    public static final String MSG_EMPTY_FIELD = "One or more fields are not being filled." +
            "\n Please fill in all the fields and try again.";
    public static enum ADDRESS_TYPE {Home, Work};
}
