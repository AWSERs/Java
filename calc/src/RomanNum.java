import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

enum RomanNum {

    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D( 500), CM( 900), M(1000);

    private int value;
    RomanNum(int value ) {

        this.value = value;
    }

    public int getValue(){

        return value;
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;


        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();


        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNum symbol = romanNumerals.get(i);

            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " невозможно преобразовать в римскую цифру");
        }
        return result;
    }

    public static List<RomanNum> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNum e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    public static int romanCalc( int a, int b, String sing){

        switch (sing){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
                throw new RuntimeException("формат математической операции не удовлетворяет заданию - используйте один из операторов (+, -, /, *)");
        }

    }

    public static String arabicToRoman(int number) {
        if ((number < 1)) {
            throw new IllegalArgumentException(" результат римского калькулятора не может быть меньше I");
        }

        List <RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNum currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
