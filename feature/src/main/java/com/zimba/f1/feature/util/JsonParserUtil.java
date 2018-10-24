package com.zimba.f1.feature.util;

import org.json.JSONObject;

public class JsonParserUtil {

     public static String getString(JSONObject pJsonObj, String pProp){
        try {
            if (pJsonObj != null) {
                return pJsonObj.getString(pProp);
            }
            return "";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
