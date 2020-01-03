/*Napisz program, który sprawdzi czy podana data jest prawidłowa w kalendarzu gregoriańskim.
        Program ma wczytać ze standardowego wejścia trzy liczby całkowite d (dzień), m (miesiąc) i r (rok), a
        następnie sprawdzić czy podane liczby reprezentują prawidłową datę w kalendarzu gregoriańskim
        (funkcja poprawnaData()) i wypisać na standardowym wyjściu odpowiedni komunikat. Kalendarz
        gregoriański obowiązuje w Polsce od 15 października 1582 roku.
        Zastosuj w swoim programie tablicę 2-wymiarową 2×13 zainicjalizowaną liczbą dni w poszczególnych
        miesiącach osobno w latach zwykłych i przestępnych.*/

package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author Jarosław Kaczmarek
 */
public class Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();

        Optional.of(checkDate(day, month, year))
                .filter(Boolean::valueOf)
                .ifPresentOrElse(
                        isCorrectDate -> printSuccessInfo(),
                        Task4::printFailedInfo
                );
    }

    private static void printSuccessInfo() {
        System.out.println("date is correct");
    }

    private static void printFailedInfo() {
        System.out.println("date is invalid");
    }

    private static boolean checkDate(int day, int month, int year) {
        return isDateInGregorianTime(day, month, year) && isValidDate(day, month, year);
    }

    private static boolean isDateInGregorianTime(int day, int month, int year) {
        String input = year + "." + month + "." + day;
        String begin = "1582.10.15";
        try {
            Date inputDate = new SimpleDateFormat("yyyy.MM.dd").parse(input);
            Date beginDate = new SimpleDateFormat("yyyy.MM.dd").parse(begin);
            return inputDate.after(beginDate);
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isValidDate(int day, int month, int year) {
        int[][] daysInMonth = {
                {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
        };
        try {
            return daysInMonth[isIntercalary(year) ? 0 : 1][month] > 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean isIntercalary(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

}
