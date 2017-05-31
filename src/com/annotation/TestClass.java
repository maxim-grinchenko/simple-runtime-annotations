package com.annotation;


import com.annotation.alist.BeforeTests;
import com.annotation.alist.Ignore;
import com.annotation.alist.Tests;
import com.annotation.tools.Assert;
import com.annotation.tools.MismatchedData;


public class TestClass {

    private Integer a;
    private Integer b;
    private Integer c;

    @BeforeTests
    public void init() {
        System.out.println("Data initialization");
        this.a = 0;
        this.b = 5;
        this.c = 120;
    }

    @Tests
    public void sum() throws MismatchedData {
        int result = b + c;
        Assert.assertEquals(125, result);
    }

    @Tests
    public void division() throws MismatchedData{
        int result = b / a;
        Assert.assertEquals(0, result);
    }

    @Tests
    @Ignore
    public void multiply() throws MismatchedData{
        int result = b * c;
        Assert.assertEquals(600, result);
    }

    
    
}





