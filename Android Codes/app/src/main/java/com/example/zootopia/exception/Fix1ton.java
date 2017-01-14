package com.example.zootopia.exception;

/**
 * Fix1ton - helper class to correct exception
 * <p/>
 * Created by Linpeng Lyu on 3/24/16.
 * Andrew ID: linpengl
 */

public class Fix1ton {
    public void fix1(int errno) {
        System.out.println("Please input a non-negative value to the field.");
    }

    public void fix2(int errno) {
        System.out.println("Please input a new value less that 100 to the field.");
    }
}

