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
* `Object getInstance(String fullyQualifiedClassName, Object... args)`
* `Object getFieldValue(Object parent, String fieldName)`
* `Object getFieldValue(Class parent, String fieldName)`
* `Object invokeMethod(Object parent, String methodName, Object... args)`
* `Object invokeMethod(Class parent, String methodName, Object... args)`
