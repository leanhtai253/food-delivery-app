package com.example.fooddeliveryapp.utils;

import org.springframework.stereotype.Component;

@Component
public class AddressUtils {

    private String MSG_UNABLE_TO_ADD_ADDRESS = "Unable to add address";
    private String MSG_NO_ADDRESS_FOUND = "No address found with id: ";


    public String setAddressTypeString(int val) {
        if (val == 0) {
            return Constant.ADDRESS_TYPE.Home.toString();
        } else {
            return Constant.ADDRESS_TYPE.Work.toString();
        }
    }

    public int setAddressTypeInt(String val) {
        if (val.equals(Constant.ADDRESS_TYPE.Home.toString())) {
            return 0;
        } else return 1;
    }

    public String MSG_UNABLE_TO_ADD_ADDRESS() {
        return MSG_UNABLE_TO_ADD_ADDRESS;
    }
    public String MSG_NO_ADDRESS_FOUND_ID(int id) {
        return MSG_NO_ADDRESS_FOUND + id;
    }
}
