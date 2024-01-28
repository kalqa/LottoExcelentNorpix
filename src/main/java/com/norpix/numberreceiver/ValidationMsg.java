package com.norpix.numberreceiver;

public enum ValidationMsg {
    ERROR_NOT_ENOUGH_NUMBERS_OR_OUT_OF_RANGE_FROM_1_TO_99(false),
    ERROR_TOO_MUCH_NUMBERS(false),
    VALIDATION_PASSED(true);
    ValidationMsg(boolean result) {
    }
}
