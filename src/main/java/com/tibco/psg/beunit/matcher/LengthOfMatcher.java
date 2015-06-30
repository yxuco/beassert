package com.tibco.psg.beunit.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class LengthOfMatcher<T> extends
		TypeSafeDiagnosingMatcher<Iterable<? extends T>> {
	private final Matcher<? super T> itemMatcher;
	private final int expectedSize;
	
	public LengthOfMatcher(Matcher<? super T> itemMatcher, int expectedSize) {
		this.itemMatcher = itemMatcher;
		this.expectedSize = expectedSize;
	}

	public void describeTo(Description description) {
		description.appendText("Length is ").appendValue(expectedSize)
			.appendText(" for items ").appendDescriptionOf(itemMatcher);
	}

	@Override
	protected boolean matchesSafely(Iterable<? extends T> collection, Description description) {
		int count = 0;
		for (T t : collection) {
			if (itemMatcher.matches(t)) {
				count++;
			}
		}
		if (count != expectedSize) {
			description.appendText(" actual size is ").appendValue(count);
			return false;
		}
        return true;
	}

	/**
     * Creates a matcher for {@link Iterable}s that only matches when a single pass over the
     * examined {@link Iterable} yields expected number items that are matched by the specified
     * <code>itemMatcher</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("bar", "baz"), lengthIs(startsWithString("ba"), 2))</pre>
     * 
     * @param itemMatcher
     *     the matcher to apply to every item provided by the examined {@link Iterable}
	 * @param expectedSize expected number of matching items
	 */
	public static <T> Matcher<Iterable<? extends T>> lengthIs(final Matcher<T> itemMatcher, int expectedSize) {
		return new LengthOfMatcher<T>(itemMatcher, expectedSize);
	}
}
