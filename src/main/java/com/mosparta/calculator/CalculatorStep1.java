package com.mosparta.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

// step1
public class CalculatorStep1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("첫 번째 숫자 입력 (혹은 종료하려면 exit): ");
            String fstInput = scanner.next();
            if (fstInput.equals("exit"))
                return;
            int num1 = Integer.parseInt(fstInput);
            System.out.println();

            System.out.print("두 번째 숫자 입력: ");
            int num2 = scanner.nextInt();
            System.out.println();

            System.out.print("연산자 입력 (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            System.out.println();

            switch (operator) {
                case '+':
                    System.out.println("Result: " + (num1 + num2));
                    break;
                case '-':
                    System.out.println("Result: " + (num1 - num2));
                    break;
                case '*':
                    System.out.println("Result: " + (num1 * num2));
                    break;
                case '/':
                    if (num2 != 0) {
                        System.out.println("Result: " + (num1 / num2));
                    } else {
                        System.out.println("Error: 0으로 나눌 수 없습니다");
                    }
                    break;
                default:
                    System.out.println("Error: 알 수 없는 기호입니다");
            }
        }
    }
}
