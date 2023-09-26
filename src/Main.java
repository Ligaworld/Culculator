import java.util.Scanner;

class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифры арабские или римские, (но не смешивааь!!!):");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expresion) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expresion.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Только два аргумента могут участвовать в операции");
        oper = detectOperacion(expresion);
        if (oper == null) throw new Exception("Ты б еще катангенсы ввел)))):");
        // Если два числа римские (V+X)
        if (Roman.isRoman (operands[0]) && Roman.isRoman(operands[1])) {
            //конверт Римских в Арабские
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
//Оба числа Арабские (1+1)
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
//если числа разные (1+V)
        else {
            throw new Exception("Нужен одинаковый формат (1+1) или (V+I)");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Калькулятор может только от 1-10))), пока слабоват");
        }
        int arabian = calc (num1, num2, oper);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("В Риме нет отрицательных чисел)))");
            }
            //конвертируем полученный результат из арабского опять в Римское число, ох
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //Возвращаем результат
        return result;
    }

    static String detectOperacion (String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc (int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}