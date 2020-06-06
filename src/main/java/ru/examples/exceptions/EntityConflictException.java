package ru.examples.exceptions;

/**
 * Исключение выбрасывается в случае конфликтов с существующими данными
 */
public class EntityConflictException extends BaseException {
    public EntityConflictException(String message) {
        super(message);
    }
}
