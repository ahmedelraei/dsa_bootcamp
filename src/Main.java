public class Main {
    public static void main(String[] args) {

        Stack<String> st = new Stack<>("Ahmed");
        st.push("Hello");
        st.push("Hello World");
        st.push("Hi");
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        System.out.println(st.size());
    }
}