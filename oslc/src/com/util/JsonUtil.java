package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.common.Const;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * ����json������صĹ�����
 * 
 * @author tuguoping
 * 
 */
public class JsonUtil {
	public static ArrayList<String> list = new ArrayList<>();

	public static ArrayList<String> resolveJson(String srcJson,
			String[] elements) {
		// ���ַ���ת����jsonObject����
		JsonObject jo = new JsonParser().parse(srcJson).getAsJsonObject();
		JsonObject jsonObject = new JsonObject();
		for (int i = 0; i < elements.length; i++) {
			String key = elements[i];
			if (!key.equalsIgnoreCase(Const.CONTRACT_ALLOCATION_NODE_NAME)) {
				JsonElement value = jo.get(Const.FIELD_PRIFIX + key);
				jsonObject.add(Const.FIELD_PRIFIX + key, value);
			}

			// ��ȡAllocation json�ַ���
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
							list.add(s);
						}
					}
				}
			}
		}
		list.add(jsonObject.toString());
		return list;
	}
}
