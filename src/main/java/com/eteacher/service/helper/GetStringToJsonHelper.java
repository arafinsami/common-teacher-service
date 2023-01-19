package com.eteacher.service.helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GetStringToJsonHelper<T> {

    final Class<T> typeParameterClass;

    public GetStringToJsonHelper(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public <T> T parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("data");
        return (T) new Gson().fromJson(jobject, typeParameterClass);
    }
}