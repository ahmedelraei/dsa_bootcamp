public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.addToHead(15);
        list.addToTail(25);
        System.out.println(list);
        list.add(77, 4);
        System.out.println(list);


    }
}