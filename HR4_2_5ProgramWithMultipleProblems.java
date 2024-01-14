
import java.util.Scanner;

public class HR4_2_5ProgramWithMultipleProblems {

	public static Data readData(Scanner input) {
		

		System.out.println("Skriv en rad med text: ");
		String s = input.nextLine();
		System.out.println("Skriv ett heltal: ");
		int i = Integer.parseInt(input.nextLine());

		
		return new Data(s, i);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(readData(input).toString());
		System.out.println(readData(input).toString());
		input.close();
	}

	/*
	 * En record är en klass som bara innehåller data som inte kan förändras. De är
	 * praktiska om man, som här, vill överföra ett par värden tillsammans eftersom
	 * man inte behöver skriva lika mycket kod som en vanlig klass. Parametrarna
	 * bestämmer vilka datafält klassen har.
	 * 
	 * Records ingår INTE i det som tagits upp på PROG1.
	 */
	private static record Data(String s, int i) {
		public String toString() {
			return "Data[s=" + s + ", i=" + i + "]";
		}
	}

}
