package com.annotation.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.annotation.alist.AfterTests;
import com.annotation.alist.BeforeTests;
import com.annotation.alist.Ignore;
import com.annotation.alist.Tests;

public class AnnotationMethodLoader {

	private List<Method> annotationBeforeList;
	private List<Method> annotationAfterList;
	private List<Method> annotationTestList;
	private List<Method> annotationIgnoreList;
	
	public AnnotationMethodLoader(Class<?> className){
		this.annotatedValidator(className);
	}

	private void annotatedValidator(Class<?> className) {
		
		annotationBeforeList = new ArrayList<Method>();
		annotationAfterList = new ArrayList<Method>();
		annotationTestList = new ArrayList<Method>();
		annotationIgnoreList = new ArrayList<Method>();
		
		for (Method method : className.getDeclaredMethods()) {
			if (method.isAnnotationPresent(BeforeTests.class)) {
				annotationBeforeList.add(method);
			}
			if (method.isAnnotationPresent(AfterTests.class)) {
				annotationAfterList.add(method);
			}
			if (method.isAnnotationPresent(Ignore.class)) {
				annotationIgnoreList.add(method);
			}
			if (method.isAnnotationPresent(Tests.class) && !method.isAnnotationPresent(Ignore.class)) {
				annotationTestList.add(method);
			}
		}
	}

	public List<Method> getAnnotationBeforeList() {
		return annotationBeforeList;
	}

	public List<Method> getAnnotationAfterList() {
		return annotationAfterList;
	}

	public List<Method> getAnnotationTestList() {
		return annotationTestList;
	}

	public List<Method> getAnnotationIgnoreList() {
		return annotationIgnoreList;
	}

}
