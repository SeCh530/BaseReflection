package com.company;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PerfomanceProxy implements Calculator {
    Calculator calculator;

    public PerfomanceProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calc(int number) {
        Method method = null;
        try {
            method = Calculator.class.getMethod("calc", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Annotation annotation = method.getAnnotation(Metric.class);
        if (annotation != null) {
            long startTime = System.nanoTime();
            int result = calculator.calc(number);
            System.out.println("Затрачено секунд:"+(System.nanoTime() - startTime) / 1_000_000_000.0);
            return result;
        } else {
            return calculator.calc(number);
        }
    }
}
