package TestTaquin;

import static org.junit.Assert.*;

import org.junit.Test;
import taquin.Taquin;

public class TestTaquin {

	@Test
	public void testResolvable() { //a tester sans la condition resolvable dans le constructeur de Taquin
		Taquin t = new Taquin(3);
		assertTrue(t.estResolvable());
	}
	@Test
	public void test1() { //a tester sans melanger dans le constructeur de Taquin
		Taquin t = new Taquin(3);
		assertTrue(t.estResolu());
	}
}
