package com.ex.util;

import java.util.ArrayList;

public class TypedArrayList<T> extends ArrayList<T> {
	Class<?> clazz;
	
	@SafeVarargs
    public TypedArrayList (T... typeInfo)
    {
        // Get generic type at runtime ...
		this.clazz = typeInfo.getClass().getComponentType();
    }
	
	public String getLowercaseShortParameterKeyType() {
		return this.clazz.getSimpleName().toLowerCase();
	}
	
	public String getLowercaseShortParameterKeyTypePlural() {
		return this.getLowercaseShortParameterKeyType() + "s";
	}
	
	public Class<?> getParameterKeyClass() {
		return this.clazz;
	}
}
