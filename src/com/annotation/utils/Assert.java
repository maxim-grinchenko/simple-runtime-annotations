package com.annotation.utils;

public class Assert {

    public static void assertEquals(int expected, int actual)throws MismatchedData{
        if(expected != actual){
                throw new MismatchedData();
        }
    }
}
