package com.ex.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.util.TypedArrayList;


@Service
public class ReflectionService {
	public List<String> getDeclaredFields(TypedArrayList<?> list) {
		List<String> declaredFields = new ArrayList<>();
		Field[] fields = list.getParameterKeyClass().getDeclaredFields();
		
        for(int i = 0; i < fields.length; i++) {
        	declaredFields.add(list.getLowercaseShortParameterKeyTypePlural() + "." + fields[i].getName());
        }
        
        return declaredFields;
	}
	
	public List<String> getDeclaredFields(Class clazz) {
		List<String> dfields = new ArrayList<>(); 
		Field[] fields = clazz.getDeclaredFields();
		for(Field fld: fields) {
			dfields.add(fld.getName());
		}
		return dfields;
	}
}
