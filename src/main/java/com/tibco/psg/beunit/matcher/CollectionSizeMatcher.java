package com.tibco.psg.beunit.matcher;

import java.util.Collection;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class CollectionSizeMatcher<T> extends
		FeatureMatcher<Iterable<? extends T>, Integer> {
	private final Matcher<? super T> itemMatcher;
	
	public CollectionSizeMatcher(Matcher<? super T> itemMatcher, Matcher<? super Integer> sizeMatcher) {
		super(sizeMatcher, "a collection of size", "collection size");
		this.itemMatcher = itemMatcher;
	}

	@SuppressWarnings("rawtypes")
	protected Integer featureValueOf(Iterable<? extends T> actual) {
		if (null == itemMatcher && actual instanceof Collection) {
			return ((Collection) actual).size();
		}
		int count = 0;
		for (T t : actual) {
			if (null == itemMatcher || itemMatcher.matches(t)) {
				count++;
			}
		}
        return count;
	}
	
	/**
     * Creates a matcher for {@link Iterable}s that only matches when a single pass over the
     * examined {@link Iterable} yields expected number items that are matched by the specified
     * <code>itemMatcher</code>.
     * For example:
     * <pre>assertThat(Arrays.asList("bar", "baz"), hasSize(startsWithString("ba"), equalTo(2)))</pre>
     * 
     * @param itemMatcher
     *     the matcher to apply to every item provided by the examined {@link Iterable}
	 * @param expectedSize expected number of matching items
	 */
	public static <T> Matcher<Iterable<? extends T>> hasSize(final Matcher<T> itemMatcher, Matcher<? super Integer> sizeMatcher) {
		return new CollectionSizeMatcher<T>(itemMatcher, sizeMatcher);
	}
}