package com.ahmedhatem.collections;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0;
    private int capacity;

    public Array() {
        this(32);
    }

    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < len; i++)
            arr[i] = null;
        len = 0;
    }

    public int add(T elem) {
        if (len + 1 > capacity) {
            if (isEmpty()) capacity = 1;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            if (len >= 0) System.arraycopy(arr, 0, new_arr, 0, len);
            arr = new_arr;
        }
        arr[len++] = elem;
        return len;
    }

    public T removeAt(int index) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        T removed_elem = arr[index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == index) j--;
            else new_arr[j] = arr[i];
        arr = new_arr;
        capacity = len--;
        return removed_elem;
    }

    public boolean remove(Object elem) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(elem)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object elem) {
        for (int i = 0; i < len; i++)
            if (arr[i].equals(elem))
                return i;
        return -1;
    }

    public boolean exists(T elem) {
        return indexOf(elem) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++)
                sb.append(arr[i]).append(", ");
            return sb.append(arr[len - 1]).append("]").toString();
        }
    }
}
