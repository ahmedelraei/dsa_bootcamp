package com.ahmedhatem.collections;

public class Main {
    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        arr.add(10);
        arr.add(30);
        arr.add(30);
        System.out.println(arr);
        arr.remove(30);
        System.out.println(arr);


    }
}