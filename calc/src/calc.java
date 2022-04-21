import java.util.Scanner;

public class calc {

    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);
        String calcStr = str.nextLine();
        String resultCalc = Main.calc(calcStr);
        System.out.println(resultCalc);

    }
}
