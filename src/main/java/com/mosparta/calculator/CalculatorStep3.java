package com.mosparta.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

enum OperatorType {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;
    private static final Map<Character, OperatorType> KEY_TO_OPERATORTYPE;

    // 객체 로딩될 때 딱 한번 모든 enum 데이터를 symbol 을 키로 hashmap 으로 저장.
    static {
        OperatorType[] values = OperatorType.values();
        KEY_TO_OPERATORTYPE = new HashMap<>(values.length);
        for (OperatorType operatorType : values) {
            KEY_TO_OPERATORTYPE.put(operatorType.symbol, operatorType);
        }
    }

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static OperatorType fromChar(char operator) {
        return KEY_TO_OPERATORTYPE.get(operator);
    }
}

class ArithmeticCalculator<T extends Number> {
    private List<Double> history = new ArrayList<>();

    public double calculate(T num1, T num2, OperatorType operator) {
        double result;

        double num1val = num1.doubleValue();
        double num2val = num2.doubleValue();
        switch (operator) {
            case ADD:
                result = num1val + num2val;
                break;
            case SUBTRACT:
                result = num1val - num2val;
                break;
            case MULTIPLY:
                result = num1val * num2val;
                break;
            case DIVIDE:
                if (num2val != 0) {
                    result = num1val / num2val;
                    break;
                } else {
                    throw new RuntimeException("0으로 나눌 수 없습니다");
                }
            default:
                throw new RuntimeException("알 수 없는 기호입니다");
        }

        history.add(result);
        return result;
    }

    public List<Double> getHistory() {
        return history;
    }

    public void setHistory(List<Double> history) {
        this.history = history;
    }

    public void deleteOldHistory() {
        if (!history.isEmpty())
            history.remove(0);
    }

    public void printHistoryBiggerThan(double axis) {
        history.stream()
                .filter(value -> value > axis)
                .forEach(System.out::println);
    }
}

public class CalculatorStep3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArithmeticCalculator<Integer> calculator = new ArithmeticCalculator<Integer>();

        while (true) {
            try {
                System.out.print(
                        "첫 번째 숫자 입력 " +
                                "- 종료하려면 `exit`" +
                                "- history 전체 출력하려면 `all-history`" +
                                "- history 예전 데이터 삭제하려면 `pop`" +
                                "- history 중 입력값보다 큰 값을 검색하려면 `bigger`" +
                                ": "
                );
                String fstInput = scanner.next();

                if (fstInput.equals("exit"))
                    return;

                if (fstInput.equals("all-history")) {
                    System.out.println(calculator.getHistory());
                    continue;
                }

                if (fstInput.equals("pop")) {
                    calculator.deleteOldHistory();
                    System.out.println("예전 데이터 삭제 완료");
                    continue;
                }

                if (fstInput.equals("bigger")) {
                    double axis = scanner.nextDouble();
                    calculator.printHistoryBiggerThan(axis);
                    continue;
                }

                int num1 = Integer.parseInt(fstInput);
                System.out.println();

                System.out.print("두 번째 숫자 입력: ");
                int num2 = scanner.nextInt();
                System.out.println();

                System.out.print("연산자 입력 (+, -, *, /): ");
                char operator = scanner.next().charAt(0);
                System.out.println();

                double result = calculator.calculate(num1, num2, OperatorType.fromChar(operator));
                System.out.println("Result: " + result);
            } catch (NumberFormatException | InputMismatchException e) { // 숫자가 아닌걸 입력했을 때
                System.out.println("Error: 숫자를 입력해야 합니다");
                scanner.nextLine(); // 버퍼 비우기
            } catch (RuntimeException e) { // 계산 중 오류가 발생했을 때
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("Error: 알 수 없는 오류가 발생했습니다" + e.getMessage() + e.getClass());
                scanner.nextLine(); // 버퍼 비우기
            }
        }
    }
}
