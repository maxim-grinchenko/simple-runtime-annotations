package com.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.annotation.utils.AnnotationMethodLoader;

public class AnnotationService {

	private Object classInstance;
	private AnnotationMethodLoader annotationMethodLoader;

	void annotationServiceLoader(Class<?> className) {

		try {
			classInstance = className.newInstance();
			annotationMethodLoader = new AnnotationMethodLoader(className);
			
			runnerBeforeList(annotationMethodLoader.getAnnotationBeforeList());
			runnerTestsList(annotationMethodLoader.getAnnotationTestList());
			runnerIgnoreList(annotationMethodLoader.getAnnotationIgnoreList());
			runnerAfterList(annotationMethodLoader.getAnnotationAfterList());
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	private void runnerBeforeList(List<Method> annotationBeforeList) {
		for (Method method : annotationBeforeList) {
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

	private void runnerAfterList(List<Method> annotationAfterList) {
		for (Method method : annotationAfterList) {
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

	private void runnerIgnoreList(List<Method> annotationIgnoreList) {
		for (Method method : annotationIgnoreList) {
			System.out.printf("Test method %s - ignored!\n", method.getName());
		}
	}

}
