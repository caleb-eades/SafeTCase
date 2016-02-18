package com.calebeades.java.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.AssertionFailedError;

import java.lang.Class;

public class SafeTCaseTest extends SafeTCase {
    public SafeTCaseTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( SafeTCaseTest.class );
    }

    // Test GetClass and ascertain correct failure
    public void testGetClass() {
        Class sampleClass = getClass("com.calebeades.java.test.SampleClass");
        assertNotNull("GetClass is null", sampleClass);
        assertEquals("GetClass not correct", SampleClass.class, sampleClass);
    }
    public void testGetClassFailure() {
        Class nonExistantClass = null;
        try {
            nonExistantClass = getClass("com.calebeades.java.test.NonExistantClass");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantClass was found", nonExistantClass);
        }
    }

    // Test GetInstance and ascertain correct failure
    public void testGetInstance() {
        SampleClass sampleClass = (SampleClass) getInstance("com.calebeades.java.test.SampleClass");
        assertNotNull("GetInstance is null", sampleClass);
    }
    public void testGetInstanceFailure() {
        Object nonExistantClass = null;
        try {
            nonExistantClass = getInstance("com.calebeades.java.test.NonExistantClass");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantClass was instantiated", nonExistantClass);
        }
    }

    // Test GetFieldValue and ascertain correct failure
    public void testGetFieldValue() {
        SampleClass sampleClass = (SampleClass) getInstance("com.calebeades.java.test.SampleClass");
        String className = (String) getFieldValue(sampleClass, "className");
        assertNotNull("GetFieldValue is null", className);
        assertEquals("GetFieldValue not correct", "SampleClass", className);
    }
    public void testGetFieldValueFailure() {
        SampleClass sampleClass = (SampleClass) getInstance("com.calebeades.java.test.SampleClass");
        Object nonExistantField = null;
        try {
            nonExistantField = getFieldValue(sampleClass, "nonExistantField");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantField was found", nonExistantField);
        }
    }

    // Test GetStaticFieldValue and ascertain correct failure
    public void testGetStaticFieldValue() {
        Class sampleClass = getClass("com.calebeades.java.test.SampleClass");
        String className = (String) getFieldValue(sampleClass, "classNameStatic");
        assertNotNull("GetFieldValue is null", className);
        assertEquals("GetFieldValue not correct", "SampleClass (static)", className);
    }
    public void testGetStaticFieldValueFailure() {
        Class sampleClass = getClass("com.calebeades.java.test.SampleClass");
        Object nonExistantStaticField = null;
        try {
            nonExistantStaticField = getFieldValue(sampleClass, "nonExistantStaticField");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantStaticField was found", nonExistantStaticField);
        }
    }

    // Test TestInvokeMethod and ascertain correct failure
    public void testInvokeMethod() {
        SampleClass sampleClass = (SampleClass) getInstance("com.calebeades.java.test.SampleClass");
        String className = (String) invokeMethod(sampleClass, "getClassName");
        assertNotNull("InvokeMethod is null", className);
        assertEquals("InvokeMethod not correct", "SampleClass", className);
    }
    public void testInvokeMethodFailure() {
        SampleClass sampleClass = (SampleClass) getInstance("com.calebeades.java.test.SampleClass");
        Object nonExistantReturn = null;
        try {
            nonExistantReturn = invokeMethod(sampleClass, "nonExistantMethod");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantMethod was found", nonExistantReturn);
        }
    }

    // Test TestInvokeStaticMethod and ascertain correct failure
    public void testInvokeStaticMethod() {
        Class sampleClass = getClass("com.calebeades.java.test.SampleClass");
        String className = (String) invokeMethod(sampleClass, "getClassNameStatic");
        assertNotNull("InvokeMethod is null", className);
        assertEquals("InvokeMethod not correct", "SampleClass (static)", className);
    }
    public void testInvokeStaticMethodFailure() {
        Class sampleClass = getClass("com.calebeades.java.test.SampleClass");
        Object nonExistantReturn = null;
        try {
            nonExistantReturn = invokeMethod(sampleClass, "nonExistantStaticMethod");
        } catch (AssertionFailedError afe) {} finally {
            assertNull("NonExistantStaticMethod was found", nonExistantReturn);
        }
    }
}

