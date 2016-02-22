# SafeTCase
Wrapper for JUnit to make Test-Driven-Development easier by allowing attempted calls to non-existant classes, fields, and methods (Runtime checks)

## Usage:

`SampleClass.java`
```
private class SampleClass {
    public String getValue() {
        return "value";
    }
}
```

`ExampleTest.java`
```
public class ExampleTest extends SafeTCase {
    public ExampleTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( Example.class );
    }

    public void testMethod() {
        Object sampleClass = getInstance("SampleClass");
        invokeMethod(sampleClass, "getValue"); // => "value"
    }
}
```

## Reference: 

* `Class getClass(String fullyQualifiedClassName)`
    * Return: Class of `fullyQualifiedClassName` if exists
    * Params: 
        * `fullyQualifiedClassName` : e.g. `"com.calebeades.java.test.SampleClass"`
* `Object getInstance(String fullyQualifiedClassName, Object... args)`
    * Return: Instance of `fullyQualifiedClassname`, using constructor which takes `args`, if exists
    * Params: 
        * `fullyQualifiedClassName` : e.g. `"com.calebeades.java.test.SampleClass"`
        * `args`                    : if applicable
* `Object getFieldValue(Object parent, String fieldName)`
    * Return: Value of field `fieldName`, if exists
    * Params: 
        * `parent`                  : Object to search for field
        * `fieldName`               : FieldName to search
* `Object getFieldValue(Class parent, String fieldName)`
    * Return: Value of field `fieldName`, if exists
    * Params: 
        * `parent`                  : Class to search for field
        * `fieldName`               : FieldName to search
* `Object invokeMethod(Object parent, String methodName, Object... args)`
    * Return: Return value of method `methodName`, if exists
    * Params:
        * `parent`                  : Object to search for method
        * `methodName`              : MethodName to execute
        * `args`                    : If applicable
* `Object invokeMethod(Class parent, String methodName, Object... args)`
    * Return: Return value of method `methodName`, if exists
    * Params:
        * `parent`                  : Class to search for method
        * `methodName`              : MethodName to execute
        * `args`                    : If applicable
