
import java.util.Scanner;

public class HR4_2_4StaticScannerDemo {

    private static void multiplyByTwo(Scanner input) {
        System.out.print("Skriv ett heltal: ");
        int i = input.nextInt();
        System.out.printf("%d*2=%d%n", i, i * 2);
    }

    private static void multiplyByThree(Scanner input) {
        System.out.print("Skriv ett heltal: ");
        int i = input.nextInt();
        System.out.printf("%d*3=%d%n", i, i * 3);
    }

    private static void multiplyNumbers(Scanner input) {
        multiplyByTwo(input);
        multiplyByThree(input);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        multiplyNumbers(input);
        input.close();
    }
}
