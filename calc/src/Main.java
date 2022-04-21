

public class Main {
    public static String calc(String input) {

        String[] inputCalcArr = input.trim().split(" ");

        if(inputCalcArr.length < 2){
            throw new RuntimeException("строка не является математической операцией, введите в формате - а + b");
        }else if (inputCalcArr.length >= 4) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        String inputCalcStr = String.join(" ", inputCalcArr);

        Check checkedNumArabic = new Check();
        Check checkedNumRoman = new Check();
        Check checkedSing = new Check();

        String sing = checkedSing.sing(inputCalcStr);

        boolean isRomanCalc = checkedNumRoman.checkedRomanNum(inputCalcStr);

        if (isRomanCalc) {

            int firstRomanOperand = RomanNum.romanToArabic(inputCalcArr[0]);
            int secondRomanOperand = RomanNum.romanToArabic(inputCalcArr[2]);

            if(firstRomanOperand >= 11 || secondRomanOperand >= 11){

                throw new RuntimeException("Калькулятор может принимать на вход числа от I до X включительно");

            }

            int resultRomanCalc = RomanNum.romanCalc(firstRomanOperand, secondRomanOperand, sing);

            return RomanNum.arabicToRoman(resultRomanCalc);

        }

        boolean isArabicCalc = checkedNumArabic.checkedArabicNum(inputCalcStr);

        if (isArabicCalc) {

            Check calcArabic = new Check();

            return calcArabic.arabicCalc(isArabicCalc, inputCalcArr[0], inputCalcArr[2], sing);


        }

        throw new RuntimeException("используются одновременно разные системы счисления");
    }

    static class Check {
        String[] arabicNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romanNum = {"I", "V", "X", "L", "D", "M", "i", "v", "x", "l", "d", "m"};


        boolean checkedRomanNum(String str) {

            for (String key : romanNum) {
                if (str.contains(key)) {
                    for (String elem : arabicNum) {
                        if (str.contains(elem)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        boolean checkedArabicNum(String str) {

            for (String key : arabicNum) {
                if (str.contains(key)) {
                    for (String elem : romanNum) {
                        if (str.contains(elem)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        String sing(String str) {
            String[] sings = {"+", "-", "*", "/"};
            String sing = "";

            for (String key : sings) {
                if (str.contains(key)) {
                    sing = key;
                }
            }
            return sing;
        }

        String arabicCalc(boolean flag, String firstOperand, String secondOperand, String mathAction) {

            int res;


            if (flag) {
                try {
                    Integer.parseInt(firstOperand);
                    Integer.parseInt(secondOperand);

                } catch (Exception e) {
                    throw new RuntimeException("Калькулятор умеет работать только с целыми числами");
                }


                int a = Integer.parseInt(firstOperand);
                int b = Integer.parseInt(secondOperand);

                if (a <= 0 || a >= 11 || b <= 0 || b >= 11) {
                    throw new RuntimeException("Калькулятор принимает на вход числа от 1 до 10 включительно");
                }

                switch (mathAction) {
                    case "+":
                        res = a + b;
                        break;
                    case "-":
                        res = a - b;
                        break;
                    case "/":
                        try {
                            res = a / b;
                            break;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    case "*":
                        res = a * b;
                        break;
                    default:
                        throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - используйте один из операторов (+, -, /, *)");
                }
            } else {
                throw new RuntimeException("используются одновременно разные системы счисления");
            }
            return String.valueOf(res);
        }
    }
}
