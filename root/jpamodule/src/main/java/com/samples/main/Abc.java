package com.samples.main;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import com.samples.entities.User;

public class Abc<T> {
	
	
	public static void main(String[] args) {
		
		Abc<User> a= new Abc<User>();
		Type[] genericInterfaces = a.getClass().getGenericInterfaces();
		System.out.println(genericInterfaces.length);
		for (Type type : genericInterfaces) {
		System.out.println(type.toString());	
		}
		TypeVariable<?>[] typeParameters = a.getClass().getTypeParameters();
		System.out.println(typeParameters.length);
		for (TypeVariable<?> typeVariable : typeParameters) {
			Type[] bounds = typeVariable.getBounds();
			for (Type type : bounds) {
				System.out.println(type);
			}
		}
		
	}

	public Abc() {
		super();
		Class<?> superclass = getClass().getSuperclass();
		if(superclass==Object.class)
		{
			System.out.println("true");
		}
	}

	
}
