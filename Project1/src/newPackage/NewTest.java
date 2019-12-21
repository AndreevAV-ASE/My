package newPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NewTest {

	@Test
	void test() {
		try {
			NewClass.Solve(1, 2, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
