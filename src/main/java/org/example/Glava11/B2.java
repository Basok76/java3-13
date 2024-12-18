package org.example.Glava11;
import java.util.List;
import java.util.ArrayList;
public class B2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(3);
        list.add(10);
        list.add(7);
        list.add(2);
        int X = 6;

        rearrangeList(list, X);

        System.out.println(list);
    }

    public static void rearrangeList(List<Integer> list, int X) {
        int i = 0;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) <= X) {
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
            }
        }
    }
}
