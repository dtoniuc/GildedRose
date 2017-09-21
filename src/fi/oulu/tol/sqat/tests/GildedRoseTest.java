package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	
	// SellIn tests
	
	@Test
	public void testUpdateEndOfDay_SellInArbitrary_RegularItemSellInPositivive_SellInDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 10, 15) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(9, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_SellInBoundary_RegularItemSellInOne_SellInDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 1, 15) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_SellInBoundary_RegularItemSellInZero_SellInDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 0, 15) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(-1, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_SellInBoundary_RegularItemSellInMinusOne_SellInDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", -1, 15) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(-2, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_SellInArbitrary_RegularItemSellInNegative_SellInDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", -1, 15) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(-2, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_SellIn_SpecialSulfuras_SellInDoesNotChange() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 5, 80) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(5, item.getSellIn());
	}
	
	// Quality regular tests
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_RegularItemQualityPositiveSellInPositive_QualityDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 15, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(9, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_RegularItemQualityPositiveSellInZero_QualityDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(9, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_RegularItemQualityPositiveSellInNegative_QualityDecreaseByTwo() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", -10, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(8, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_RegularItemQualityOneSellInPositive_QualityDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 10, 1) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_RegularItemQualityOneSellInNegative_QualityDecreaseStopAtZero() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", -10, 1) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_RegularItemQualityZeroSellInPositive_QualityDoesNotDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", 10, 0) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_RegularItemQualityZeroSellInNegative_QualityDoesNotDecrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Regular Item", -10, 0) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	// Quality Aged Brie tests
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_AgedBrieQualityRegularSellInPositive_QualityIncrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 15, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(11, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_AgedBrieQualityRegularSellInNegative_QualityIncrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", -15, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(11, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_AgedBrieQualityZero_QualityIncrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 15, 0) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(1, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_AgedBrieQualityFourtyNine_QualityIncrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 15, 49) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_AgedBrieQualityFifty_QualityStopsAtFifty() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 15, 50) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(50, item.getQuality());
	}
	
	// Quality Passes tests
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_PassesQualityRegularSellInPositive_QualityIncrease() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(11, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_PassesBrieQualityRegularSellInZero_QualityZero() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
		
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_PassesBrieQualityRegularSellInNegative_QualityZero() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", -15, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(0, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInEleven_QualityIncreases() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(21, item.getQuality());
	}
		
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInTen_QualityIncreasesByTwo() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(22, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInNine_QualityIncreasesByTwo() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(22, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInSix_QualityIncreasesByTwo() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(22, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInFive_QualityIncreasesByThree() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(23, item.getQuality());
	}
		
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInFour_QualityIncreasesByThree() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(23, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityBoundary_PassesBrieQualityRegularSellInOne_QualityIncreasesByThree() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(23, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_PassesQualityFifty_QualityStopsAtFifty() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 50) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_PassesQualityFortyNine_QualityIncreases() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 49) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(50, item.getQuality());
	}
	
	// Quality Sulfuras tests
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_SulfurasSellInPositive_QualityNotChanged() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 20, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(10, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityArbitrary_SulfurasSellInNegative_QualityNotChanged() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", -20, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item item = items.get(0);
		assertEquals(10, item.getQuality());
	}
	
}
