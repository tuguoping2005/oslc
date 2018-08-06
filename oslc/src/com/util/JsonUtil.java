package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.common.Const;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 处理json数据相关的工具类
 * 
 * @author tuguoping
 * 
 */
public class JsonUtil {
	public static ArrayList<String> allocationList = Lists.newArrayList();
	public static ArrayList<String> assetLeaseList = Lists.newArrayList();
	public static HashMap<String,List<String>> map= Maps.newHashMap();

	public static Map<String,List<String>> resolveJson(String srcJson,
			String[] elements) {
		if(StringUtils.isBlank(srcJson) || Arrays.asList(elements).isEmpty()){
			return null;
		};
		// 将字符串转换成jsonObject对象
		JsonObject jo = new JsonParser().parse(srcJson).getAsJsonObject();
		JsonObject jsonObject = new JsonObject();
		for (int i = 0; i < elements.length; i++) {
			String key = elements[i];
			if (!key.equalsIgnoreCase(Const.CONTRACT_ALLOCATION_NODE_NAME)) {
				JsonElement value = jo.get(Const.FIELD_PRIFIX + key);
				jsonObject.add(Const.FIELD_PRIFIX + key, value);
			}

			// 获取Allocation json字符串
			if (Arrays.toString(elements).contains(
					Const.CONTRACT_ALLOCATION_NODE_NAME)) {
				if (Const.CONTRACT_ALLOCATION_NODE_NAME
						.equalsIgnoreCase(elements[i])) {
					String allocationKey = Const.FIELD_PRIFIX + elements[i];
					JsonElement e = jo.get(allocationKey);
					if (e != null && e.isJsonArray()) {
						JsonArray jsonArray = e.getAsJsonArray();
						Iterator<JsonElement> it = jsonArray.iterator();
						while (it.hasNext()) {
							JsonElement element = it.next();
							String s = element.toString();
							allocationList.add(s);
						}
						map.put("allocations",allocationList);
					}
				}
			}
		}
		assetLeaseList.add(jsonObject.toString());
		map.put("assetLeaseTemplate",assetLeaseList);
		return map;
	}
}
