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
		assertEquals(0, wurstBude.getWurstCount());
		wurstBude.addWurst(wurst1);
		assertEquals(1, wurstBude.getWurstCount());
	}

	@Test
	public void testAddWurst() {
		// Test that .addWurst() throws exception when trying to add a Wurst
		// that is already grilled.
		when(wurst1.isRaw()).thenReturn(false);
		try {
			wurstBude.addWurst(wurst1);
			fail("expected IllegalArgumentException when adding not raw wurst");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testGetTotalWurstWeight() {
		// Test that .getTotalWurstWeight() returns calculates the correct
		// amount of wurstware and calls Wurst's .getWeight()
		when(wurst1.getWeight()).thenReturn(1);
		when(wurst2.getWeight()).thenReturn(2);
		wurstBude.addWurst(wurst1);
		wurstBude.addWurst(wurst2);
		int totalWeight = wurstBude.getTotalWurstWeight();
		assertEquals(3, totalWeight);
		verify(wurst1).getWeight();
		verify(wurst2).getWeight();
	}

	@Test
	public void testGrillAll() {
		// Test that .grillAll() grills every Wurst that is still raw but does
		// not grill a Wurst that has already been grilled.
		wurstBude.addWurst(wurst1);
		wurstBude.addWurst(wurst2);
		wurstBude.addWurst(wurst3);
		when(wurst1.isRaw()).thenReturn(false);
		wurstBude.grillAll();
		verify(wurst1, never()).grill();
		verify(wurst3).grill();
	}

	@Test
	public void testRemoveWurstNotBestBefore() {
		// Test that .removeWurstNotBestAfter() only keeps the wuerste in the
		// bude that have their "best before" date *after* the date specified.
		calendar.add(Calendar.DATE, -1);
		Date oneMonthMinusOneDay = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date oneMonth = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date oneMonthPlusOneDay = calendar.getTime();
		when(wurst1.getBestBefore()).thenReturn(oneMonthMinusOneDay);
		when(wurst2.getBestBefore()).thenReturn(oneMonth);
		when(wurst3.getBestBefore()).thenReturn(oneMonthPlusOneDay);
		wurstBude.addWurst(wurst1);
		wurstBude.addWurst(wurst2);
		wurstBude.addWurst(wurst3);
		assertEquals(3, wurstBude.getWurstCount());
		wurstBude.removeWurstNotBestAfter(oneMonth);
		assertEquals(1, wurstBude.getWurstCount());
	}
}
