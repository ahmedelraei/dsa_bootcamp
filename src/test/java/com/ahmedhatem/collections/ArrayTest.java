package com.ahmedhatem.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

    Array<Integer> arr;

    @BeforeEach
    void setup() {
        arr = new Array<>();
    }

    @Test
    void add() {
        arr.add(5);
        assertEquals(1, arr.size());
        arr.add(7);
        assertEquals(2, arr.size());
    }

    @Test
    void size() {
        assertEquals(0, arr.size());
        arr.add(5);
        assertEquals(1, arr.size());
    }

    @Test
    void isEmpty() {
        assertTrue(arr.isEmpty());
    }

    @Test
    void get() {
        arr.add(1);
        arr.add(2);
        assertEquals(2, arr.get(1));
    }

    @Test
    void set() {
        arr.add(10);
        arr.add(20);
        arr.add(30);

        arr.set(1, 5);
        assertEquals(5, arr.get(1));
    }

    @Test
    void clear() {
        arr.add(10);
        arr.add(20);
        arr.add(30);
        assertEquals(3, arr.size());
        arr.clear();
        assertTrue(arr.isEmpty());
    }

    @Test
    void removeAt() {
        arr.add(10);
        arr.add(12);
        arr.add(33);
        assertEquals(3, arr.size());
        assertEquals(12, arr.removeAt(1));
        assertEquals(2, arr.size());

    }

    @Test
    void remove() {
        arr.add(10);
        arr.add(12);
        arr.add(33);
        arr.add(43);
        arr.add(63);
        assertEquals(5, arr.size());
        assertTrue(arr.remove(33));
        assertEquals(4, arr.size());
    }

    @Test
    void indexOf() {
        arr.add(10);
        arr.add(12);
        arr.add(33);
        arr.add(43);
        arr.add(63);

        assertEquals(4, arr.indexOf(63));
        assertEquals(-1, arr.indexOf((int) Math.random() * 250));
    }

    @Test
    void exists() {
        arr.add(33);
        arr.add(43);
        arr.add(63);

        assertTrue(arr.exists(43));
        assertFalse(arr.exists((int) Math.random() * 250));
    }
}