package com.carvendy.guava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.google.common.collect.UnmodifiableIterator;

public class TestGuava {

	private void create() {
		// 原来创建 复杂的List和Map
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

		List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();

		// guava
		Map<String, Map<String, String>> mapG = Maps.newHashMap();
		List<List<Map<String, String>>> listG = Lists.newArrayList();
		Set<Map<String, String>> setG = Sets.newHashSet();
	}

	private static void valueCannotChange() {
		Set<Integer> data = new HashSet<Integer>();
		data.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80));
		System.out.println("data1:" + data);
		Set<Integer> fixedData = Collections.unmodifiableSet(data); // fixedData
																	// - [50,
																	// 70, 80,
																	// 20, 40,
																	// 10, 60,
																	// 30]
		data.add(90); // fixedData - [50, 70, 80, 20, 40, 10, 90, 60, 30]
		// 可以改变
		System.out.println("data2:" + data);

		// 如何创建不可变的集合：
		/*
		 * ImmutableSet<Integer> numbers = ImmutableSet.of(10, 20, 30, 40, 50);
		 * //使用copyOf方法 ImmutableSet<Integer> another =
		 * ImmutableSet.copyOf(numbers); //使用Builder方法 ImmutableSet<Integer>
		 * numbers2 = ImmutableSet.<Integer>builder().addAll(numbers) .add(60)
		 * .add(70).add(80).build();
		 */

		ImmutableSet<Integer> numbers = ImmutableSet.of(10, 20, 30, 40, 50);
		System.out.println("numbers0:" + data);
		data.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80));
		System.out.println("numbers1:" + data);
		data.add(90);
		System.out.println("numbers2:" + data);

		// 构造测试数据，不用一个一个put或add赋值
		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
		ImmutableMap<String, String> map = ImmutableMap.of("key1", "value1",
				"key2", "value2");
	}

	private static void newStruct() {
		// 一种key可以重复的map，子类有ListMultimap和SetMultimap，对应的通过key分别得到list和set
		System.out.println("//---------test-MutilMap");
		Multimap<String, String> customersByType = ArrayListMultimap.create();
		customersByType.put("abc", "xx");
		customersByType.put("abc", "xyz");
		customersByType.put("abc", "p1");
		customersByType.put("abc", "p2");

		customersByType.put("abcd", "p3");
		customersByType.put("abcde", "p4");

		for (String s : customersByType.values()) {
			System.out.println(s);
		}

		Collection<String> collection = customersByType.get("abc");
		Iterator<String> iterator = collection.iterator();
		System.out.println(".................");
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}

		// 不是集合，可以增加重复的元素，并且可以统计出重复元素的个数，例子如下：
		Multiset<Integer> multiSet = HashMultiset.create();
		multiSet.add(10);
		multiSet.add(30);
		multiSet.add(30);
		multiSet.add(40);
		System.out.println("//--------test-MutilSet:");
		System.out.println(multiSet.count(30)); // 2
		System.out.println(multiSet.size()); // 4

		// -----两个key的map
		Table<Integer, Integer, String> table = HashBasedTable.create();
		table.put(1, 1, "[1,1]");
		table.put(1, 3, "[1,3]");
		table.put(1, 4, "[1,4]");

		table.put(2, 2, "[2,2]");
		table.put(2, 3, "[2,3]");
		table.put(2, 1, "[2,1]");

		table.put(3, 1, "[3,1]");
		table.put(3, 2, "[3,2]");
		table.put(3, 3, "[3,3]");

		System.out.println("//-------test-table");
		// row is key
		System.out.println("row0-size:" + table.row(0));
		Map<Integer, String> rowMap = table.row(1);
		System.out.println("row1-size:" + rowMap.size());
		for (String cellStr : rowMap.values()) {
			System.out.println(cellStr);// 无序
		}

		// 是一个一一映射，可以通过key得到value，也可以通过value得到key；
		BiMap<Integer, String> biMap = HashBiMap.create();

		biMap.put(1, "hello");
		biMap.put(2, "helloa");
		biMap.put(3, "world");
		biMap.put(4, "worldb");
		biMap.put(5, "my");
		biMap.put(6, "myc");

		int value = biMap.inverse().get("my");
		System.out.println("my --" + value);

		// 有的时候，你的map的key并不是一种类型，他们是很多类型，你想通过映射他们得到这种类型
		System.out.println("//------test-ClassToInstanceMap");
		ClassToInstanceMap<Serializable> classToInstanceMap = MutableClassToInstanceMap
				.create();
		classToInstanceMap.put(String.class, "abc");
		classToInstanceMap.put(Integer.class, 1);

		// System.out.println(classToInstanceMap.get(String.class));
		// System.out.println(classToInstanceMap.get(Integer.class));
		// --------------------
		/*
		 * RangeSet<Integer> rangeSet = TreeRangeSet.create();
		 * rangeSet.add(Range.closed(1, 10));
		 * System.out.println("rangeSet:"+rangeSet);
		 * rangeSet.add(Range.closedOpen(11, 15));
		 * System.out.println("rangeSet:"+rangeSet); rangeSet.add(Range.open(15,
		 * 20)); System.out.println("rangeSet:"+rangeSet);
		 * rangeSet.add(Range.openClosed(0, 0));
		 * System.out.println("rangeSet:"+rangeSet);
		 * rangeSet.remove(Range.open(5, 10));
		 * System.out.println("rangeSet:"+rangeSet);
		 */
	}

	// 筛选
	public static void testPredict() {
		List<Person> personList = Lists.newArrayList(new Person(1, 1, "a",
				"46546", 1, 20), new Person(2, 1, "abm", "46546", 0, 30),
				new Person(3, 1, "abcm", "46546", 0, 25), new Person(4, 1,
						"aef", "46546", 1, 50), new Person(5, 1, "adem",
						"46546", 0, 27),
				new Person(6, 1, "acc", "46546", 1, 29), new Person(7, 1,
						"addm", "46546", 0, 33));

		ImmutableMultiset<Person> women = ImmutableMultiset.copyOf(Collections2
				.filter(personList, new Predicate<Person>() {
					@Override
					public boolean apply(Person input) {
						return input.getSex() == 0;
					}
				}));

		UnmodifiableIterator<Person> iterator = women.iterator();
		while (iterator.hasNext()) {
			Person next = iterator.next();
			System.out.println(next.getName());
		}

		/*
		 * 举个例子来说，假设你有一个Map，key是物品，value是对应的价格，单位是欧元。
		 * 那么，你有个需求是将里面的价格都转换为美元，传统的做法是遍历整个Map，然后更新每个value值， 将价格转换为美元价格，好麻烦...
		 */

		HashMap<String, Double> eurPriceMap = Maps.newHashMap();
		eurPriceMap.put("a", 1.0);
		eurPriceMap.put("b", 1.2);
		eurPriceMap.put("c", 1.3);
		eurPriceMap.put("d", 2.4);

		/*
		 * Functions functions = new Functions(){ double eurToUsd = 1.4888;
		 * 
		 * @SuppressWarnings("unused") public Double apply(final Double from) {
		 * return from * eurToUsd; } };
		 */

		Maps.transformValues(eurPriceMap, new Function<Double, Double>() {
			double eurToUsd = 1.4888;

			@SuppressWarnings("unused")
			public Double apply(final Double from) {
				return from * eurToUsd;
			}

		});

	}

	// 截取一列，变成新的集合
	public static void testTransform() {
		List<Person> personList = Lists.newArrayList(new Person(1, 1, "a",
				"46546", 1, 20), new Person(2, 1, "ab", "46546", 0, 30),
				new Person(3, 1, "abc", "46546", 0, 25), new Person(4, 1,
						"aef", "46546", 1, 50), new Person(5, 1, "ade",
						"46546", 0, 27),
				new Person(6, 1, "acc", "46546", 1, 29), new Person(7, 1,
						"add", "46546", 0, 33));

		ImmutableMultiset<String> copyOf = ImmutableMultiset.copyOf(Lists
				.transform(personList, new Function<Person, String>() {
					@Override
					public String apply(Person input) {
						return input.getName();
					}
				}));
	}

	public static void testOrder() {
		ArrayList<Integer> numbers = Lists.newArrayList(30, 20, 60, 80, 10);
		System.out.println(numbers);
		// 10,20,30,60,80
		System.out.println(Ordering.natural().sortedCopy(numbers));

		System.out.println(Ordering.natural().reverse().sortedCopy(numbers)); // 80,60,30,20,10

		Ordering.natural().min(numbers); // 10

		Ordering.natural().max(numbers); // 80

		numbers = Lists.newArrayList(30, 20, 60, 80, null, 10);

		Ordering.natural().nullsLast().sortedCopy(numbers); // 10,
															// 20,30,60,80,null

		Ordering.natural().nullsFirst().sortedCopy(numbers); // null,10,20,30,60,80

	}

	public static void testCharMatcher() {

		CharMatcher.is('a');
		CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));

		String strNum = CharMatcher.DIGIT
				.retainFrom("some text 89983 and more");
		System.out.println("get-num:" + strNum);
		System.out.println(CharMatcher.DIGIT.retainFrom(" 89983 and 1234"));
		String strText = CharMatcher.DIGIT
				.removeFrom("some text 89983 and more");
		System.out.println("text:" + strText);

		// System.out.println(CharMatcher.DIGIT.retainFrom("some text 89983 and more"));
		System.out.println();
	}

	public static void testJoinerAndSplitter() {
		String[] subdirs = { "usr", "local", "lib" };
		String dir = Joiner.on("/").join(subdirs);
		System.out.println(dir);

		ArrayList<String> strArr1 = Lists.newArrayList("test1", "test2",
				"test3", null, "test4", null, null);
		Joiner.on(';').skipNulls().join(strArr1);

		/*
		 * Output:-> "test1;test2;test3;test4"
		 */
		Joiner.on(';').useForNull("_").join(strArr1);

		/*
		 * Output:-> "test1;test2;test3;_;test4;_;_"
		 */

		// ---
		// Iterable split = Splitter.on(",").split("1,2,3,4,5");
		// 可以使用str.spilt

		// 但
		System.out.println("--------spilt----");
		String testString = "foo , what,,, more ,";
		for (String str : testString.split(",")) {
			System.out.println(str);
		}

		System.out.println("--------Splitter----");
		Iterable<String> split = Splitter.on(",").omitEmptyStrings().// 空白结果除去
				trimResults().// 结果去空格
				split(testString);
		for (String s : split) {
			System.out.println(s);
		}
		System.out.println("--------------");

		// System.out.println("xyz");
		System.out.println();

		String tmpValue = "a_b_c_1_2_3";
		String[] valArr = tmpValue.split("_");

		// 求字符串数组的子串，并最后拼接起来
		String tmpVal = "";
		for (int i = 1; i < valArr.length; i++) {
			tmpVal = tmpVal.equalsIgnoreCase("") ? valArr[i] : tmpVal + "_"
					+ valArr[i];
		}
		System.out.println(tmpVal);
		System.out.println("———————");

		// 上面这么一段与下面这句等价
		System.out.println(Joiner.on("_").join(
				Lists.newArrayList(valArr).subList(1, valArr.length)));
	}

	public void testSet() {
		/*
		 * for (Object o : usdPriceMap.values()) { System.out.println(o); }
		 */

		HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
		HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

		SetView<Integer> union = Sets.union(setA, setB);
		System.out.println("union:");
		for (Integer integer : union) {
			System.out.println(integer);
		}

		SetView<Integer> difference = Sets.difference(setA, setB);
		System.out.println("difference:");
		for (Integer integer : difference) {
			System.out.println(integer);
		}

		SetView<Integer> intersection = Sets.intersection(setA, setB);
		System.out.println("intersection:");
		for (Integer integer : intersection) {
			System.out.println(integer);
		}
	}

	public static void main(String[] args) {
		// valueCannotChange();
		// newStruct();

		testPredict();
		// testOrder();

		// testCharMatcher();
		// testJoinerAndSplitter();

	}
}
