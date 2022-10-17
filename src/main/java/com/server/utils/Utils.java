package com.server.utils;

/**
 * Вспомогательный класс.
 *
 * @author Morozkin V. A. on 14.10.2022
 */
public class Utils {

    /**
     * 100%
     */
    private static final int ONE_HUNDRED_PERCENT = 100;

    /**
     * Внимание! Для сумм не используют Integer!
     * Метод призван посчитать сумму,
     * которая является кол-вом % от изначальной суммы.
     *
     * @param amount  Сумма (например платеж).
     * @param percent какой % нужно взять от изначальной суммы.
     * @return возвращает сумму, которую будет использовать дальше.
     */
    public static Integer countPercent(Integer amount, Integer percent) {
        return (amount / ONE_HUNDRED_PERCENT) * percent;
    }
}
