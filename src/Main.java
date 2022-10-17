public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(10);
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        arr.add(50);
        arr.add(60);
        arr.add(70);
        System.out.println(arr);
        System.out.println(arr.remove(20));
        System.out.println(arr);
    }
}