package com.easy.rpc;

import junit.framework.TestCase;

public class JSONDecoderTest extends TestCase {

    public void testDecode() {
        TestClass test = new TestClass();
        test.setAge(22);
        test.setId("saaasdas");
        Encoder lab = new JSONEncoder();
        byte[] bytes = lab.encode(test);

        Decoder decoder = new JSONDecoder();
        TestClass tes = decoder.decode(bytes,TestClass.class);
        System.out.println(tes.getAge());
        System.out.println(tes.getId());
    }
}