package test;

import static org.junit.Assert.*;

import org.junit.Test;

import static fizz.FizzBuzz.change;

public class FizzBuzzTest {

	@Test
	public void testModulo3() {
		assertEquals("Fizz", change(3));

	}
	@Test
	public void testModulo3And5() {
		assertEquals("FizzBuzz", change(15));

	}
	@Test
	public void testModulo5() {
		assertEquals("Buzz", change(5));

	}
	@Test
	public void testModulo7() {
		assertEquals("Tazz", change(7));

	}
	@Test
	public void testModulo3And5And7() {
		assertEquals("FizzBuzzTazz", change(105));

	}
	@Test
	public void testModulo3And7() {
		assertEquals("FizzTazz", change(21));

	}
	public void testModulo5And7() {
		assertEquals("FizzBuzz", change(35));

	}

}
