package com.easy.rpc;

import junit.framework.TestCase;

public class JSONEncoderTest extends TestCase {

    public void testEncode() {
        TestClass test = new TestClass();
        test.setAge(22);
        test.setId("saaasdas");
        Encoder lab = new JSONEncoder();
        assertNotNull(lab.encode(test));
    }
}