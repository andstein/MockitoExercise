package org.wurst.exercises.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class WurstBudeTest {

	private WurstBude wurstBude;
	private Wurst wurst1, wurst2, wurst3;
	private Calendar calendar;

	@Before
	public void setUp() throws Exception {
		wurstBude = new WurstBude();
		wurst1 = mock(Wurst.class);
		when(wurst1.isRaw()).thenReturn(true);
		wurst2 = mock(Wurst.class);
		when(wurst2.isRaw()).thenReturn(true);
		wurst3 = mock(Wurst.class);
		when(wurst3.isRaw()).thenReturn(true);
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
	}

	@Test
	public void testGetWurstCount() {
		// Test that .getWurstCount() works as expected when adding wuerste.
	}

	@Test
	public void testAddWurst() {
		// Test that .addWurst() throws exception when trying to add a Wurst
		// that is already grilled.
		when(wurst1.isRaw()).thenReturn(false);
	}

	@Test
	public void testGetTotalWurstWeight() {
		// Test that .getTotalWurstWeight() returns calculates the correct
		// amount of wurstware and calls Wurst's .getWeight()
	}

	@Test
	public void testGrillAll() {
		// Test that .grillAll() grills every Wurst that is still raw but does
		// not grill a Wurst that has already been grilled.
	}

	@Test
	public void testRemoveWurstNotBestBefore() {
		// Test that .removeWurstNotBestAfter() only keeps the wuerste in the
		// bude that have their "best before" date *after* the date specified.
	}
}
