package com.kuteinykov.programm.utils;

public class ValidationUtil {

    /**
     * Утилитный класс. Для жизни и работы которого не требуется экземпляр данного класса,
     * поскольку он не имеет состояния.
     * Использует только статические методы и переменные.
     *
     */

    private ValidationUtil() {
    }

    /**
     * Метод который преобразовывает строку в число. Выбрасывает NumberFormatException при неудачи, который необходимо обработать.
     */
    static public int checkNumber(String number) {
        return new Integer(number);
    }

    static public boolean checkInput(String number, String digits){

        if(number.isEmpty()) return false;

        for (int i = 0; i < number.length(); i++){
            if (!digits.contains(number.substring(i,(i+1))))
                return false;
        }
        return true;
    }

    static public int countDigits(String number, String digits){

        if(number.isEmpty()) return 0;

        String digitsPhonNumber = "";

        for (int i = 0; i < number.length(); i++){
            if (digits.contains(number.substring(i,(i+1))))
                digitsPhonNumber += number.substring(i,(i+1));
        }
        return Long.toString(Long.valueOf(digitsPhonNumber)).length();
    }
}