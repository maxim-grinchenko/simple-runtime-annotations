package com.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.annotation.tools.AnnotationMethodLoader;

public class AnnotationService {

	private Object classInstance;
	private AnnotationMethodLoader annotationMethodLoader;

	public void annotationServiceLoader(Class<?> className) {

		try {
			classInstance = className.newInstance();
			annotationMethodLoader = new AnnotationMethodLoader(className);
			
			runnerList(annotationMethodLoader.getAnnotationBeforeList());
			runnerTestsList(annotationMethodLoader.getAnnotationTestList());
			runnerIgnoreList(annotationMethodLoader.getAnnotationIgnoreList());
			runnerList(annotationMethodLoader.getAnnotationAfterList());
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	private void runnerList(List<Method> annotationList) {
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

	private void runnerTestsList(List<Method> annotationTestsList) {
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

	private void runnerIgnoreList(List<Method> annotationIgnoreList) {
		for (Method method : annotationIgnoreList) {
			System.out.printf("Test method %s - ignored!\n", method.getName());
		}
	}

}
