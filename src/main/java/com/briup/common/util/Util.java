package com.briup.common.util;

/*
 * 根据协同过滤计算相似度，进行推荐
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Util {
	// 获取要推荐的歌单编号
	public static Collection<String> getValue(Collection<String> list) {
		Map<String, String> map = getMap();
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (String s : list) {
			String string = map.get(s);
			if (string == null) {
				continue;
			}
			String[] split = string.split(":");
			for (int i = 0; i < split.length; i++) {
				String[] split2 = split[i].split(",");
				int value = 0;
				if (m.get(split2[0]) != null) {
					value = Integer.parseInt(split2[1]);
					Integer oldvalue = m.get(split2[0]);
					value += oldvalue;
					m.put(split2[0], value);
				} else {
					value = Integer.parseInt(split2[1]);
					m.put(split2[0], value);
				}
			}
		}
		Collection<String> sort = sort(m);
		// 已经存在的歌单删除
		for (String s : list) {
			sort.remove(s);
		}
		return sort;
	}
	// 排序，根据共现次数计算出的结果按最高的排前面
	private static Collection<String> sort(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		List<String> list2 = new ArrayList<String>();
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		for (Entry<String, Integer> l : list) {
			list2.add(l.getKey());
		}
		return list2;
	}
	// 将文件的数据整理成key--value
	private static Map<String, String> getMap() {
		String path = "F:/MavenWorkspace/Music/src/main/resources/a.txt";
		BufferedReader br = null;
		Boolean flag = true;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Integer> newMap = new HashMap<String, Integer>();
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			try {
				while (flag) {
					String readLine = br.readLine();
					if (readLine == null) {
						flag = false;
					} else {
						String[] str = readLine.split(",");
						if (map.get(str[0]) != null) {
							String value = map.get(str[0]);
							value = value + "," + str[1];
							map.put(str[0], value);
						} else {
							map.put(str[0], str[1]);
						}
					}
				}
				for (String s : map.keySet()) {
					String[] str = map.get(s).split(",");
					for (int i = 0; i < str.length; i++) {
						for (int j = 0; j < str.length; j++) {
							String string = str[i] + "," + str[j];
							if (newMap.get(string) != null) {
								newMap.put(string, newMap.get(string) + 1);
							} else {
								newMap.put(string, 1);
							}
						}
					}
				}
				// 将map清空
				map.clear();
				// 将新的数据添加到map中
				for (String s : newMap.keySet()) {
					String[] str = s.split(",");
					String key = str[0];
					String value = str[1] + "," + newMap.get(s);
					if (map.get(key) != null) {
						value = value + ":" + map.get(key);
						;
						map.put(key, value);
					} else {
						map.put(key, value);
					}
				}
				return map;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
