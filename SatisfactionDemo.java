package com.restraunt.satisfaction.demo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SatisfactionDemo {

		//Path to txt file.
		private final String path = "c:\\data.txt";
		//Total time for customer[In this case first element of txt file].
		Long totalTime = null;
		

		/**
		 * @return
		 * @throws Exception
		 * Controller method which returns List of menu items that gives best possible satisfaction to customer in provided time.
		 */
		public static void main(String[] args) {
			SatisfactionDemo demo = new SatisfactionDemo();
			demo.populateItemsInList(demo.readFileReturnMap(demo.path));
		}
		
		public int returnTotalSatisfactionValue(List<Long> satisfactoryItemsList) {
			int finalValue = 0;
			for (Long value : satisfactoryItemsList) {
				finalValue += value;
			}
			return finalValue;
		}

		/**
		 * @param satisfactoryItemsList
		 * @param finalMap
		 * @param iterativeTime
		 * @return Method adds menu items in list with adding iterativeTime upto
		 *         finalTime
		 */
		public List<Long> populateItemsInList(Map<Long, Long> finalMap) {
			if (finalMap == null) {
				return null;
			}
			List<Long> satisfactoryItemsList = new ArrayList<>();
			// IterativeTime keeps adding time after adding every element in the order satisfaction list.
			int iterativeTime = 0;
			for (Map.Entry<Long, Long> entry : finalMap.entrySet()) {
				iterativeTime = (int) (iterativeTime + entry.getValue());
				if (iterativeTime <= totalTime){
					//&& (iterativeTime + entry.getValue()) <= totalTime) {
					satisfactoryItemsList.add(entry.getKey());
				} else {
					break;
				}
			}
			System.out.println("Size of final result list :"+ satisfactoryItemsList.size());
			System.out.println("Total Satisfaction Value = "+ returnTotalSatisfactionValue(satisfactoryItemsList));
			return satisfactoryItemsList;
		}

		/**
		 * @param path
		 * @return Method reads a text file from filesystem path and returns a
		 *         treemap.
		 */
		public Map<Long, Long> readFileReturnMap(String path) {
			if (null == path) {
				return null;
			}
			BufferedReader br = null;
			// Using TreeMap since its the best way to have a sorted key in a map.
			Map<Long, Long> map = new HashMap<Long, Long>();
			try {
				String sCurrentLine = null;
				br = new BufferedReader(new FileReader(new File(path)));
				int i = 0;
				while ((sCurrentLine = br.readLine()) != null) {
					// Splitting on basis of space.
					String[] arr = sCurrentLine.split(" ");
					if (i == 0) {
						// setting total time for allotted to customer.
						totalTime = Long.parseLong(arr[0]);
					} else {
						map.put(Long.parseLong(arr[0]), Long.parseLong(arr[1]));
					}
					i++;
				}
				System.out.println("Printing TotalTime = " + totalTime);
				if (null != map) {
					return sortByValue(map);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return null;
		}

		public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
			Comparator<K> valueComparator = new Comparator<K>() {
				public int compare(K k1, K k2) {
					int compare = map.get(k1).compareTo(map.get(k2));
					if (compare == 0)
						return 1;
					else
						return compare;
				}};
			Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
			sortedByValues.putAll(map);
			return sortedByValues;
		}
}
