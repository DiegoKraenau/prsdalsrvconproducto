package com.grupogloria.prsdalsrvconproducto.registration.aop.logging;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class JSONUtil {

	public static GsonBuilder gsonGeneric = new GsonBuilder();
	public static GsonBuilder gsonBuilder = new GsonBuilder();

	public static<T> T getObjectForJSON(String json, Class<T> class1) {
		Gson gson= gsonGeneric.create();
		return (T)gson.fromJson(json, class1);	
	}


	public static<T> String getJSONForObject(T t) {
		Gson gson= gsonGeneric.setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		return gson.toJson(t);	
	}

	public static<T> String getJSONForObjectWithNull(T t) {
		gsonBuilder.serializeNulls();
		Gson gsonWithNull= gsonBuilder.setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		return gsonWithNull.toJson(t);	
	}
}