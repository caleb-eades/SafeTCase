package com.Bilbo0fBagEnd.java.test;

import java.lang.reflect.*;
import java.lang.Class;

import junit.framework.TestCase;

public abstract class SafeTCase extends TestCase {

	public SafeTCase( String testName ) {
        super( testName );
    }

	public Class getClass(String fullyQualifiedClassName) {
		// Get the class from its name
		Class c = null;
		try {
			c = Class.forName(fullyQualifiedClassName);
		} catch (java.lang.ClassNotFoundException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " does not exist", c);
			return null;
		}
		return c;
	}

	public Object getInstance(String fullyQualifiedClassName, Object... args) {
		// Create array of classes for args
		Class[] classes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			Class argsClass = args[i].getClass();
			classes[i] = argsClass;
		}
		// Get the class from its name
		Class c = null;
		try {
			c = Class.forName(fullyQualifiedClassName);
		} catch (java.lang.ClassNotFoundException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " does not exist", c);
			return null;
		}
		// Get the constructor
		Constructor constructor = null;
		try {
			constructor = c.getConstructor(classes);
		} catch (java.lang.NoSuchMethodException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " does not have designated constructor", constructor);
			return null;
		}
		// Instantiate the object
		Object instance = null;
		try {
			instance = constructor.newInstance(args);
		} catch (java.lang.InstantiationException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " could not be instantiated", instance);
			return null;
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " does not have public access", instance);
			return null;
		} catch (java.lang.reflect.InvocationTargetException e) {
			assertNotNull("Class " + fullyQualifiedClassName + " has an error in designated constructor", instance);
			return null;
		}
		return instance;
	}

	public Object getFieldValue(Object parent, String fieldName) {
		Field field = null;
		try {
			field = parent.getClass().getDeclaredField(fieldName);
		} catch (java.lang.NoSuchFieldException e) {
			assertNotNull("Class " + parent.getClass().getCanonicalName() + " does not have designated field", field);
			return null;
		}
		Object value = null;
		try {
			value = field.get(parent);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Field " + fieldName + " does not have public access in " + parent.getClass().getCanonicalName(), value);
			return null;
		}
		return value;
	}

	public Object getFieldValue(Class parent, String fieldName) {
		Field field = null;
		try {
			field = parent.getDeclaredField(fieldName);
		} catch (java.lang.NoSuchFieldException e) {
			assertNotNull("Class " + parent.getCanonicalName() + " does not have designated field", field);
			return null;
		}
		Object value = null;
		try {
			value = field.get(parent);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Field " + fieldName + " does not have public access in " + parent.getCanonicalName(), value);
			return null;
		}
		return value;
	}

	public Object invokeMethod(Object parent, String methodName, Object... args) {
		// Create array of classes for args
		Class[] classes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			Class argsClass = args[i].getClass();
			classes[i] = argsClass;
		}
		// Get the method
		Method method = null;
		try {
			method = parent.getClass().getMethod(methodName, classes);
		} catch (java.lang.NoSuchMethodException e) {
			StringBuilder sb = new StringBuilder("Class " + parent.getClass().getCanonicalName() + " does not have method " + methodName + "(");
			String prefix = "";
			for (Class item : classes) {
				sb.append(prefix + item.getSimpleName());
				prefix = ", ";
			}
			sb.append(")");
			assertNotNull(sb.toString(), method);
			return null;
		}
		// Call the method for result
		Object result = null;
		try {
			result = method.invoke(parent, args);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Method " + methodName + " does not have public access in " + parent.getClass().getCanonicalName(), result);
			return null;
		} catch (java.lang.reflect.InvocationTargetException e) {
			assertNotNull("An error occurred in method " + methodName + " of " + parent.getClass().getCanonicalName(), result);
			return null;
		}
		return result;
	}

	public Object invokeMethod(Class parent, String methodName, Object... args) {
		// Create array of classes for args
		Class[] classes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			Class argsClass = args[i].getClass();
			classes[i] = argsClass;
		}
		// Get the method
		Method method = null;
		try {
			method = parent.getMethod(methodName, classes);
		} catch (java.lang.NoSuchMethodException e) {
			StringBuilder sb = new StringBuilder("Class " + parent.getCanonicalName() + " does not have method " + methodName + "(");
			String prefix = "";
			for (Class item : classes) {
				sb.append(prefix + item.getSimpleName());
				prefix = ", ";
			}
			sb.append(")");
			assertNotNull(sb.toString(), method);
			return null;
		}
		// Call the method for result
		Object result = null;
		try {
			result = method.invoke(parent, args);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Method " + methodName + " does not have public access in " + parent.getCanonicalName(), result);
			return null;
		} catch (java.lang.reflect.InvocationTargetException e) {
			assertNotNull("An error occurred in method " + methodName + " of " + parent.getCanonicalName(), result);
			return null;
		}
		return result;
	}
}