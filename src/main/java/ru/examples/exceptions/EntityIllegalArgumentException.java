package ru.examples.exceptions;


/**
 * Исключение выбрасывается при вызове метода с некорректными параметрами
 */
public class EntityIllegalArgumentException extends BaseException {

    public EntityIllegalArgumentException(String message) {
        super(message);
    }

}
