
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;

public class HR4_2_5ProgramWithMultipleProblemsTest {

	public static final String VERSION = "2024-01-04 13:16";

	private ByteArrayOutputStream systemOut = new ByteArrayOutputStream();

	private InputStream originalSystemIn = System.in;
	private PrintStream originalSystemOut = System.out;

	@BeforeEach
	public void setupSystemOutputStreams() {
		System.setOut(new PrintStream(systemOut));
	}

	@AfterEach
	public void restoreSystemStreams() {
		System.setIn(originalSystemIn);
		System.setOut(originalSystemOut);
	}

	private void setSystemIn(String... userInputLines) {
		System.setIn(createUserInputStream(userInputLines));
	}

	private InputStream createUserInputStream(String... userInputLines) {
		var input = Arrays.stream(userInputLines).collect(Collectors.joining("\n")) + "\n";
		return new ByteArrayInputStream(input.getBytes());
	}

	private String systemOut() {
		return systemOut.toString().replaceAll("\\R", "\n").trim();
	}

	private void executeTest(String expected, String... userInputLines) {
		setSystemIn(userInputLines);
		try {
			HR4_2_5ProgramWithMultipleProblems.main(null);
			assertEquals(expected, systemOut());
		} catch (Exception e) {
			fail(systemOut(), e);
		}
	}

	@Test
	void eachInputOnItsOwnLine() {
		executeTest("""
				Skriv en rad med text:\s
				Skriv ett heltal:\s
				Data[s=Första, i=22]
				Skriv en rad med text:\s  
				Skriv ett heltal:\s
				Data[s=Tredje, i=4444]""", "Första", "22", "Tredje", "4444");
	}

	@Test
	void eachInputOnItsOwnLineAnotherTime() {
		executeTest("""
				Skriv en rad med text:\s
				Skriv ett heltal:\s
				Data[s=Första, i=22]
				Skriv en rad med text:\s  
				Skriv ett heltal:\s
				Data[s=Tredje, i=4444]""", "Första", "22", "Tredje", "4444");
	}

}
