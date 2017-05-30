package com.annotation;

public class Main {

	public static void main(String[] args) {

		//final String ANNOTATION_CLASS = "com.annotation.TestClass";
		
		Class<?> className = TestClass.class;

		AnnotationService annotationRunner = new AnnotationService();
		annotationRunner.annotationServiceLoader(className);
		
	}
	
	
}
