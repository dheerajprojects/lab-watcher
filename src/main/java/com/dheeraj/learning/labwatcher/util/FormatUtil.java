package com.dheeraj.learning.labwatcher.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FormatUtil {
    public static String convertToJSON(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(obj);// obj is your object
        return json;
    }
}
