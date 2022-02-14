package com.sdt.fsc.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;

import java.util.List;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-30 13:05
 */
public class Xml2JsonUtil {
    public static JSONObject parseXmlString2Json(String xmlString) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        Element node = document.getRootElement();
        return elementToJSONObject(node);
    }

    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        List<Attribute> listAttr = node.attributes();
        for (Attribute attr : listAttr) {
            result.put(attr.getName(), attr.getValue());
        }
        List<Element> listElement = node.elements();
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {
                if (e.attributes().isEmpty() && e.elements().isEmpty()) {
                    result.put(e.getName(), e.getTextTrim());
                } else {
                    if (!result.containsKey(e.getName()))
                        result.put(e.getName(), new JSONArray());
                    ((JSONArray)result.get(e.getName())).add(elementToJSONObject(e));
                }
            }
        }
        return result;
    }

}
