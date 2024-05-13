package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class base {

	int precio;
	@BeforeEach
	void setUp() throws Exception {
		
		precio = 20;
	}

	@Test
	void test() {
		assertEquals(20,precio,"mal");
		
		
	}

}
