package com.ahmedhatem.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DoublyLinkedListTest {
    private static final int LOOPS = 10000;
    private static final int TEST_SZ = 40;
    private static final int NUM_NULLS = TEST_SZ / 5;
    private static final int MAX_RAND_NUM = 250;

    DoublyLinkedList<Integer> list;

    // Generate a list of random numbers
    static List<Integer> genRandList(int sz) {
        List<Integer> lst = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) lst.add((int) (Math.random() * MAX_RAND_NUM));
        for (int i = 0; i < NUM_NULLS; i++) lst.add(null);
        Collections.shuffle(lst);
        return lst;
    }

    // Generate a list of unique random numbers
    static List<Integer> genUniqueRandList(int sz) {
        List<Integer> lst = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) lst.add(i);
        for (int i = 0; i < NUM_NULLS; i++) lst.add(null);
        Collections.shuffle(lst);
        return lst;
    }

    @BeforeEach
    public void setup() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testEmptyList() {
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void testRemoveFirstOfEmpty() {
        assertThrows(Exception.class, () -> list.removeHead());
    }

    @Test
    public void testRemoveLastOfEmpty() {
        assertThrows(Exception.class, () -> list.removeTail());
    }

    @Test
    public void testPeekFirstOfEmpty() {
        assertThrows(Exception.class, () -> list.peekHead());
    }

    @Test
    public void testPeekLastOfEmpty() {
        assertThrows(Exception.class, () -> list.peekTail());
    }

    @Test
    public void testAddFirst() {
        list.addToHead(3);
        assertEquals(1, list.size());
        list.addToHead(5);
        assertEquals(2, list.size());
    }

    @Test
    public void testAddLast() {
        list.addToTail(3);
        assertEquals(1, list.size());
        list.addToTail(5);
        assertEquals(2, list.size());
    }

    @Test
    public void testAddAt() throws Exception {
        list.add(0, 1);
        assertEquals(1, list.size());
        list.add(1, 2);
        assertEquals(2, list.size());
        list.add(1, 3);
        assertEquals(3, list.size());
        list.add(2, 4);
        assertEquals(4, list.size());
        list.add(1, 8);
        assertEquals(5, list.size());
    }

    @Test
    public void testRemoveFirst() {
        list.addToHead(3);
        assertEquals(3, list.removeHead());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        list.addToTail(4);
        assertEquals(4, list.removeTail());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPeekFirst() {
        list.addToHead(4);
        assertEquals(4, list.peekHead());
        assertEquals(list.size(), 1);
    }

    @Test
    public void testPeekLast() {
        list.addToTail(4);
        assertEquals(4, list.peekTail());
        assertEquals(1, list.size());
    }

    @Test
    public void testPeeking() {
        // 5
        list.addToHead(5);
        assertEquals(5, list.peekHead());
        assertEquals(5, list.peekTail());

        // 6 - 5
        list.addToHead(6);
        assertEquals(6, list.peekHead());
        assertEquals(5, list.peekTail());

        // 7 - 6 - 5
        list.addToHead(7);
        assertEquals(7, list.peekHead());
        assertEquals(5, list.peekTail());

        // 7 - 6 - 5 - 8
        list.addToTail(8);
        assertEquals(7, list.peekHead());
        assertEquals(8, list.peekTail());

        // 7 - 6 - 5
        list.removeTail();
        assertEquals(7, list.peekHead());
        assertEquals(5, list.peekTail());

        // 7 - 6
        list.removeTail();
        assertEquals(7, list.peekHead());
        assertEquals(6, list.peekTail());

        // 6
        list.removeHead();
        assertEquals(6, list.peekHead());
        assertEquals(6, list.peekTail());
    }

    @Test
    public void testRemoving() {
        DoublyLinkedList<String> strs = new DoublyLinkedList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        strs.add("e");
        strs.add("f");
        strs.remove("b");
        strs.remove("a");
        strs.remove("d");
        strs.remove("e");
        strs.remove("f");
        strs.remove("c");
        assertEquals(0, strs.size());
    }

    @Test
    public void testRemoveAt() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeAt(0);
        list.removeAt(2);
        assertEquals(2, list.peekHead());
        assertEquals(3, list.peekTail());
        list.removeAt(1);
        list.removeAt(0);
        assertEquals(0, list.size());
    }

    @Test
    public void testClear() {
        list.add(22);
        list.add(33);
        list.add(44);
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add(22);
        list.add(33);
        list.add(44);
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testRandomizedRemoving() {
        java.util.LinkedList<Integer> javaLinkedList = new java.util.LinkedList<>();
        for (int loops = 0; loops < LOOPS; loops++) {

            list.clear();
            javaLinkedList.clear();

            List<Integer> randNums = genRandList(TEST_SZ);
            for (Integer value : randNums) {
                javaLinkedList.add(value);
                list.add(value);
            }

            Collections.shuffle(randNums);

            for (int i = 0; i < randNums.size(); i++) {

                Integer rm_val = randNums.get(i);
                assertEquals(javaLinkedList.remove(rm_val), list.remove(rm_val));
                assertEquals(javaLinkedList.size(), list.size());

                java.util.Iterator<Integer> iter1 = javaLinkedList.iterator();
                java.util.Iterator<Integer> iter2 = list.iterator();
                while (iter1.hasNext()) assertEquals(iter1.next(), iter2.next());

                iter1 = javaLinkedList.iterator();
                iter2 = list.iterator();
                while (iter1.hasNext()) assertEquals(iter1.next(), iter2.next());
            }

            list.clear();
            javaLinkedList.clear();

            for (Integer value : randNums) {
                javaLinkedList.add(value);
                list.add(value);
            }

            // Try removing elements whether not they exist
            for (int i = 0; i < randNums.size(); i++) {

                Integer rm_val = (int) (MAX_RAND_NUM * Math.random());
                assertEquals(javaLinkedList.remove(rm_val), list.remove(rm_val));
                assertEquals(javaLinkedList.size(), list.size());

                java.util.Iterator<Integer> iter1 = javaLinkedList.iterator();
                java.util.Iterator<Integer> iter2 = list.iterator();
                while (iter1.hasNext()) assertEquals(iter1.next(), iter2.next());
            }
        }
    }

    @Test
    public void testRandomizedRemoveAt() {
        java.util.LinkedList<Integer> javaLinkedList = new java.util.LinkedList<>();

        for (int loops = 0; loops < LOOPS; loops++) {

            list.clear();
            javaLinkedList.clear();

            List<Integer> randNums = genRandList(TEST_SZ);

            for (Integer value : randNums) {
                javaLinkedList.add(value);
                list.add(value);
            }

            for (int i = 0; i < randNums.size(); i++) {

                int rm_index = (int) (list.size() * Math.random());

                Integer num1 = javaLinkedList.remove(rm_index);
                Integer num2 = list.removeAt(rm_index);
                assertEquals(num1, num2);
                assertEquals(javaLinkedList.size(), list.size());

                java.util.Iterator<Integer> iter1 = javaLinkedList.iterator();
                java.util.Iterator<Integer> iter2 = list.iterator();
                while (iter1.hasNext()) assertEquals(iter1.next(), iter2.next());
            }
        }
    }

    @Test
    public void testRandomizedIndexOf() {
        java.util.LinkedList<Integer> javaLinkedList = new java.util.LinkedList<>();

        for (int loops = 0; loops < LOOPS; loops++) {

            javaLinkedList.clear();
            list.clear();

            List<Integer> randNums = genUniqueRandList(TEST_SZ);

            for (Integer value : randNums) {
                javaLinkedList.add(value);
                list.add(value);
            }

            Collections.shuffle(randNums);

            for (int i = 0; i < randNums.size(); i++) {
                Integer elem = randNums.get(i);
                Integer index1 = javaLinkedList.indexOf(elem);
                Integer index2 = list.indexOf(elem);

                assertEquals(index1, index2);
                assertEquals(javaLinkedList.size(), list.size());

                java.util.Iterator<Integer> iter1 = javaLinkedList.iterator();
                java.util.Iterator<Integer> iter2 = list.iterator();
                while (iter1.hasNext()) assertEquals(iter1.next(), iter2.next());
            }
        }
    }

    @Test
    public void testToString() {
        DoublyLinkedList<String> strs = new DoublyLinkedList<>();
        assertEquals("[  ]", strs.toString());
        strs.add("a");
        assertEquals("[ a ]", strs.toString());
        strs.add("b");
        assertEquals("[ a, b ]", strs.toString());
        strs.add("c");
        strs.add("d");
        strs.add("e");
        strs.add("f");
        assertEquals("[ a, b, c, d, e, f ]", strs.toString());
    }
}