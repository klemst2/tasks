/*Napisz program, który do n-elementowej tablicy wpisze losowe wartości z przedziału [a, b) a potem
        wyliczy podstawowe statystyki na wylosowanych danych.
        Program ma wczytać ze standardowego wejścia liczbę naturalną n (zakładamy, że n > 0), dwie liczby
        rzeczywiste a i b (zakładamy że a < b), utworzyć n-elementową tablicę typu double[], wypełnić tą
        tablicę losowymi wartościami z przedziału [a, b), używając funkcji Math.random(), a następnie
        wyliczyć i wypisać na standardowym wyjściu następujące statystyki:
        • wartość minimalną (funkcja minimum()),
        • wartość maksymalną (funkcja maksimum()),
        • średnią arytmetyczną (funkcja średnia() oraz suma()).
        Zastosuj w swoim programie pętle for-each, która przechodzi po całej tablicy.*/

package com.company;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.DoubleStream;

/**
 * @author Jarosław Kaczmarek
 */
public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        double[] values = getValues(n, a, b);

        System.out.println(String.format("Max value: %f", getMin(values)));
        System.out.println(String.format("Min value: %f", getMax(values)));
        System.out.println(String.format("Avg value: %f", getAvg(values)));
        System.out.println(String.format("Sum value: %f", getSum(values)));
    }

    private static double[] getValues(int n, double a, double b) {
        return new Random()
                .doubles(n, a, b)
                .toArray();
    }

    private static double getMin(double[] values) {
        return DoubleStream.of(values)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("Not found minimum value"));
    }

    private static double getMax(double[] values) {
        return DoubleStream.of(values)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Not found maximum value"));
    }

    private static double getAvg(double[] values) {
        return DoubleStream.of(values)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Not found maximum value"));
    }

    private static double getSum(double[] values) {
        return DoubleStream.of(values)
                .sum();
    }

}
