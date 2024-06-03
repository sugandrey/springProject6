package com.sugandrey.FirstRestApp.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public enum ExceptionMessagesEnum {

    SENSOR_NOT_FOUND_EXCEPTION("Сенсор с таким названием не найден"),
    SENSOR_DUPLICATE_EXCEPTION("Сенсор с таким именем уже зарегистрирован"),
    EMPTY_SENSOR_EXCEPTION("Имя сенсора не может быть пустым"),
    TOO_LONG_OR_SHORT_SENSOR_NAME("Имя сенсора не может быть меньше 3 и больше 30 символов"),
    NEED_REGISTER_SENSOR("Сенсор с таким именем не зарегистрирован. Зарегистрируйте этот сенсор"),
    NON_NULL_TEMPERATURE("Поле температура не может быть пустым"),
    OUT_OF_BOUND_TEMPERATURE_RANGE("Поле температура не может быть ниже -100С или выше 100С"),
    EMPTY_RAINING_FIELD("Поле дождь не может быть пустым");

    private String message;
    ExceptionMessagesEnum(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
