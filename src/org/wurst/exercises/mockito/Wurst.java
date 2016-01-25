package org.wurst.exercises.mockito;

import java.util.Date;

/** Class to fill {@link WurstBude} with. */
public class Wurst {

	private final int weight;
	private final Date bestBefore;

	private boolean isRaw = true;

	/** Creates a new Wurst with specified date and "best before" date. */
	Wurst(int weight, Date bestBefore) {
		this.weight = weight;
		this.bestBefore = bestBefore;
	}

	/**
	 * Grills this Wurst. Raises a {@link IllegalStateException} if the Wurst
	 * was already grilled before.
	 */
	void grill() {
		if (!isRaw) {
			throw new IllegalStateException("Wurst already grilled!");
		}
		isRaw = false;
	}

	/** Returns the weight of this Wurst (in grams). */
	public int getWeight() {
		return weight;
	}

	/** Returns the date before which this Wurst should be eaten. */
	public Date getBestBefore() {
		return new Date(bestBefore.getTime());
	}

	/** Returns whether this Wurst is not yet grilled. */
	public boolean isRaw() {
		return isRaw;
	}
}
