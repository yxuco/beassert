package com.tibco.psg.beunit;

import static com.tibco.psg.beunit.MatcherAssert.*;

import java.util.Calendar;

import org.junit.Test;

public class MatchAssertTest {

	@Test
	public void testAllOf() {
		assertThat("AllOf", "myValue", allOf(startsWithString("my"), containsString("Val")));
	}

	@Test
	public void testAnyOf() {
		assertThat("AnyOf", "myValue", anyOf(startsWithString("foo"), containsString("Val")));
	}

	@Test
	public void testEveryItem() {
		assertThat("EveryItem", asList("bar", "baz"), everyItem(startsWithString("ba")));
	}

	@Test
	public void testHasItem() {
		assertThat("HasItem", asList("foo", "bar"), hasItem(startsWithString("ba")));
	}

	@Test
	public void testEqualTo() {
		assertThat("EqualTo", "foo", equalTo("foo"));
	}

	@Test
	public void testNot() {
		assertThat("Not", "foo", not(equalTo("bar")));
	}

	@Test
	public void testSameInstance() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj1p = obj1;
		assertThat("SameInstance", obj1p, sameInstance(obj1));
		assertThat("Not SameInstance", obj2, not(sameInstance(obj1)));
	}
	
	@Test
	public void testNullValue() {
		assertThat("NullValue", null, nullValue());
	}

	@Test
	public void testStartsWithString() {
		assertThat("StartsWithString", "myValue", startsWithString("my"));
	}

	@Test
	public void testEndsWithString() {
		assertThat("EndsWithString", "myValue", endsWithString("lue"));
	}

	@Test
	public void testContainsString() {
		assertThat("ContainsString", "myValue", containsString("Val"));
	}

	@Test
	public void testMatchesRegex() {
		assertThat("MatchesRegex", "myValue", matchesRegex("^my.*lue$"));
	}

	@Test
	public void testInstanceOf() {
		assertThat("InstanceOf", Calendar.getInstance(), instanceOf("DateTime"));
	}

	@Test
	public void testLengthIs() {
		assertThat("LengthIs", asList("foo", "bar"), lengthIs(notNullValue(), 2));
	}

}
