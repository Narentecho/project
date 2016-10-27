package com.restraunt.satisfaction.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTests {

	@Mock
	private DemoController controller;

	@Before
	public void setup() {
		controller = new DemoController();
	}

	@Test
	public void getSatisfactoryOrder() throws Exception {
		List<Long> response = controller.getSatisfactoryOrder();
		Assert.assertNotNull(response);
		Assert.assertFalse(response.size() == 0);
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void populateItemsInListTest() {
		Map<Long, Long> mockMap = Mockito.mock(Map.class);
		List<Long> itemsList = controller.populateItemsInList(mockMap);
		Assert.assertNotNull(itemsList);
	}

	@Test
	public void returnTotalSatisfactionValueTest() {
		List<Long> list = new ArrayList<Long>();
		list.add((long) 5);
		list.add((long) 6);
		int response = controller.returnTotalSatisfactionValue(list);
		Assert.assertEquals(11, response);
	}

	@SuppressWarnings({})
	@Test
	public void populateItemsInListwithNullTest() {
		List<Long> itemsList = controller.populateItemsInList(null);
		Assert.assertNull(itemsList);
	}

	@Test
	public void readFileReturnMapTest() {
		Map<Long, Long> map = controller.readFileReturnMap("c:\\data.txt");
		Assert.assertNotNull(map);
		Assert.assertEquals(100,map.size());
	}

	@Test
	public void readFileReturnMapWIthNullPathTest() {
		Map<Long, Long> map = controller.readFileReturnMap(null);
		Assert.assertNull(map);
	}

	@Test
	public void sortByValueMapSizeTest() {
		final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(4, 6);
		map.put(3, 6);
		map.put(5, 6);
		map.put(8, 7);
		map.put(23, 8);
		map.put(1, 9);
		Map<Integer, Integer> responseMap = controller.sortByValue(map);
		Assert.assertEquals(6, responseMap.size());
	}
}