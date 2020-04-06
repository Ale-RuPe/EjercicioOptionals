package optionals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class OptionalsTest {

	@Test
	public void comparadorStringTestVerdadero() {
		Properties p = new Properties();
		p.setProperty("a", "5");
		p.setProperty("b", "true");
		p.setProperty("c", "-3");
		
		EjercicioOptionals test = new EjercicioOptionals();
		
		assertEquals(5, test.readDuration(p,"a"));
		assertEquals(0, test.readDuration(p,"b"));
		assertEquals(-3, test.readDuration(p,"c"));
		assertEquals(0, test.readDuration(p,"d"));
	}

}
