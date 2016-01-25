package org.wurst.exercises.mockito;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** A place to stock and prepare wuerste. */
public class WurstBude {

	private final Set<Wurst> wuerste;

	WurstBude() {
		wuerste = new HashSet<>();
	}

	/** Adds a Wurst. */
	void addWurst(Wurst wurst) {
		if (!wurst.isRaw()) {
			throw new IllegalArgumentException("Only raw wuerste can be added");
		}
		wuerste.add(wurst);
	}

	/** Returns the number of wuerste. */
	int getWurstCount() {
		return wuerste.size();
	}

	/** Returns the total weight of wuerste. */
	int getTotalWurstWeight() {
		int total = 0;
		for (Wurst wurst : wuerste) {
			total += wurst.getWeight();
		}
		return total;
	}

	/** Grills all wuerste that are not still raw. */
	void grillAll() {
		for (Wurst wurst : wuerste) {
			if (wurst.isRaw()) {
				wurst.grill();
			}
		}
	}

	/**
	 * Removes the wuerste that do not have their {@link Wurst#getBestBefore()}
	 * after the specified date.
	 *
	 * <p>Note that a wurst that has its {@code bestBefore} date on the same day
	 * as the date specified will also be removed.
	 */
	void removeWurstNotBestAfter(Date date) {
		Set<Wurst> notBestAfter = new HashSet<>();
		for (Wurst wurst : wuerste) {
			if (wurst.getBestBefore().compareTo(date) <= 0) {
				notBestAfter.add(wurst);
			}
		}
		wuerste.removeAll(notBestAfter);
	}
}
