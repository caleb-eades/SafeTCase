package com.calebeades.java.test;

import java.lang.reflect.*;
import java.lang.Class;

import junit.framework.TestCase;

public abstract class SafeTCase extends TestCase {

	public SafeTCase( String testName ) {
        super( testName );
    }

    // Return the Class represented by the ClassName
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

	// Get and instance of a Class using constructor with args
	public Object getInstance(String fullyQualifiedClassName, Object... args) {
		// Create array of classes for args
		Class[] classes = getArgumentClasses(args);
		// Get the class from its name
		Class c = getClass(fullyQualifiedClassName);
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
			e.printStackTrace();
			return null;
		}
		return instance;
	}

	// As tempting as it is, do not try to combine these two methods.
	// Doing so will cause getFieldValue(Object, String) to fail 
	// Get the value stored in a field from an Object
	public Object getFieldValue(Object parent, String fieldName) {
		Field field = getField(parent.getClass(), fieldName);
		return getValueFromField(parent, field);
	}
	// Get the value stored in a field from a Class
	// Requires that the field be static
	public Object getFieldValue(Class parent, String fieldName) {
		Field field = getField(parent, fieldName);
		return getValueFromField(parent, field);
	}

	// As tempting as it is, do not try to combine these two methods.
	// Doing so will cause invokeMethod(Object, String, Object...) to fail 
	// Get the result of a method on an Object
	public Object invokeMethod(Object parent, String methodName, Object... args) {
		Class[] classes = getArgumentClasses(args);

		// Get the method
		Method method = getMethod(parent.getClass(), methodName, classes);

		// Call the method for result
		return getMethodResult(parent, parent.getClass(), method, args);
	}
	// Get the result of a method on a Class
	// Requires that the method be static
	public Object invokeMethod(Class parent, String methodName, Object... args) {
		Class[] classes = getArgumentClasses(args);

		// Get the method
		Method method = getMethod(parent, methodName, classes);

		// Call the method for result
		return getMethodResult(parent, parent, method, args);
	}

	// Helper methods
	// Create an array of Classes from an array of objects
	// Necessary for getting constructors and methods with argument signatures
	private Class[] getArgumentClasses(Object[] args) {
		Class[] classes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			Class argsClass = args[i].getClass();
			classes[i] = argsClass;
		}
		return classes;
	}

	// Get a field from a class
	private Field getField(Class parent, String fieldName) {
		Field field = null;
		try {
			field = parent.getDeclaredField(fieldName);
		} catch (java.lang.NoSuchFieldException e) {
			assertNotNull("Class " + parent.getCanonicalName() + " does not have designated field", field);
			return null;
		}
		return field;
	}

	// Get the value stored in a field on a Class or Object
	// Getting a value from a Class requires that the field be static
	private Object getValueFromField(Object parent, Field field) {
		Object value = null;
		try {
			value = field.get(parent);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Field " + field.getName() + " does not have public access in " + parent.getClass().getCanonicalName(), value);
			return null;
		}
		return value;
	}

	// Get a method from a Class
	private Method getMethod(Class parent, String methodName, Class... classes) {
		Method method = null;
		try {
			method = parent.getMethod(methodName, classes);
		} catch (java.lang.NoSuchMethodException e) {
			StringBuilder sb = new StringBuilder("Class " + parent.getCanonicalName() + " does not have method " + methodName + "(");
			String prefix = "";
			for (Class item : classes) {
				sb.append(prefix + item.getCanonicalName());
				prefix = ", ";
			}
			sb.append(")");
			assertNotNull(sb.toString(), method);
			return null;
		}
		return method;
	}

	// Get the result from a method on a Class or Object
	// Getting a result from a Class requires that the method be static
	private Object getMethodResult(Object parent, Class parentClass, Method method, Object... args) {
		Object result = null;
		try {
			result = method.invoke(parent, args);
		} catch (java.lang.IllegalAccessException e) {
			assertNotNull("Method " + method.getName() + " does not have public access in " + parentClass.getCanonicalName(), result);
			return null;
		} catch (java.lang.reflect.InvocationTargetException e) {
			assertNotNull("An error occurred in method " + method.getName() + " of " + parentClass.getCanonicalName(), result);
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
