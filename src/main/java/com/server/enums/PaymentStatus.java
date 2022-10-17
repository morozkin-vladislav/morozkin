package com.server.enums;

/**
 * Статусы платежа:
 * 1. Новый
 * 2. Ошибка обработки
 * 3. Обработка успешна завершена
 */
public enum PaymentStatus {
    NEW,
    ERROR,
    COMPLETED
}
