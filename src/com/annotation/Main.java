package com.annotation;

public class Main {

	public static void main(String[] args) {
		
		AnnotationService annotationRunner = new AnnotationService();
		Class<?> className = TestClass.class;
		annotationRunner.annotationServiceLoader(className);
		
	}
}
