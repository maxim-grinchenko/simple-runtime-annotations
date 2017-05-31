package com.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.annotation.tools.AnnotationListBuilder;

public class AnnotationService {

	private Object classInstance;
	private AnnotationListBuilder annotationListBuilder;

	public void annotationServiceLoader(Class<?> className) {

		try {
			classInstance = className.newInstance();
			annotationListBuilder = new AnnotationListBuilder(className);
			
			runServiceList(annotationListBuilder.getAnnotationBeforeList());
			runTestsList(annotationListBuilder.getAnnotationTestList());
			runIgnoreList(annotationListBuilder.getAnnotationIgnoreList());
			runServiceList(annotationListBuilder.getAnnotationAfterList());
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	private void runServiceList(List<Method> annotationList) {
		for (Method method : annotationList) {
			try {
				method.invoke(classInstance);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private void runTestsList(List<Method> annotationTestsList) {
		for (Method method : annotationTestsList) {
			try {
				method.invoke(classInstance);
				System.out.printf("Test method %s - passed!\n", method.getName());
			} catch (IllegalAccessException e) {
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.printf("Test method %s - failed!\n", method.getName());
			}
		}
	}

	private void runIgnoreList(List<Method> annotationIgnoreList) {
		for (Method method : annotationIgnoreList) {
			System.out.printf("Test method %s - ignored!\n", method.getName());
		}
	}

}
