package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.common.Const;
import com.common.ResponseCode;
import com.common.TririgaResponse;
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
	
	
	public static void main(String[] args) {
		System.out.println(StringUtils.EMPTY);
		System.out.println(StringUtils.SPACE);
		System.out.println(StringUtils.isNotBlank("50"));
		System.out.println(StringUtils.EMPTY=="");
		
		
		/*String srcJson = "{\"student\":[{\"name\":\"\",\"age\":23},{\"name\":\"leilei02\",\"age\":23}]}";
		JsonObject jsonObject = new JsonParser().parse(srcJson).getAsJsonObject();
		JsonElement je = jsonObject.get("student");
		System.out.println(je.isJsonArray());
		
		if(je.isJsonArray()){
			System.out.println("===============");
			Iterator<JsonElement> it = je.getAsJsonArray().iterator();
			while (it.hasNext()) {
				JsonElement element = it.next();
				JsonElement nameJson = element.getAsJsonObject().get("name");
				JsonElement ageJson = element.getAsJsonObject().get("age");
				System.out.println("--"+nameJson.toString()+"---"+"   "+ageJson.toString()); 
			}
		}
		*/
	}
	
	
	public static Map<String,List<String>> resolveJson(String srcJson,
			String[] elements) {
		HashMap<String,List<String>> map= Maps.newHashMap();
		if(StringUtils.isBlank(srcJson) || Arrays.asList(elements).isEmpty()){
			return null;
		};
		// 将字符串转换成jsonObject对象
		JsonObject jo = new JsonParser().parse(srcJson).getAsJsonObject();
		JsonObject jsonObject = new JsonObject();
		
		//分别创建allocationList，assetLeaseList保存json数据，
		ArrayList<String> allocationList = Lists.newArrayList();
		ArrayList<String> assetLeaseList = Lists.newArrayList();
		//存储每个摊销记录的百分比的值
		ArrayList<String> persentageList = Lists.newArrayList();
		
		for (int i = 0; i < elements.length; i++) {
			String key = elements[i];
			if (!key.equalsIgnoreCase(Const.CONTRACT_ALLOCATION_NODE_NAME)) {
				key = Const.FIELD_PRIFIX + key;
				JsonElement value = jo.get(key);
				if(value != null){
					jsonObject.add(key, value);
				}
			}else{
				// 获取Allocation json字符串
				if (Const.CONTRACT_ALLOCATION_NODE_NAME.equalsIgnoreCase(elements[i])) {
					String allocationKey = Const.FIELD_PRIFIX + elements[i];
					JsonElement e = jo.get(allocationKey);
					if (e != null && e.isJsonArray()) {
						JsonArray jsonArray = e.getAsJsonArray();
						Iterator<JsonElement> it = jsonArray.iterator();
						while (it.hasNext()) {
							JsonElement element = it.next();
							//获取摊销百分比数据
							JsonElement percentageJson = element.getAsJsonObject().get("spi:cstAllocationPercentageNU");
							if(percentageJson != null){
								String s = percentageJson.getAsString();
								if(StringUtils.isNotBlank(s) && StringUtils.isNumeric(s)){
									persentageList.add(s);							
								}
							}
							
							allocationList.add(element.toString());
						}
						map.put("allocations",allocationList);
						map.put("percentages",persentageList);
					}
				}
			}
		}
		assetLeaseList.add(jsonObject.toString());
		map.put("assetLeaseTemplate",assetLeaseList);
		return map;
	}
}
