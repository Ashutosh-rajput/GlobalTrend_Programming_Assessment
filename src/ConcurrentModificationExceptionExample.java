import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationExceptionExample {
    public static void main(String[] args) {
        // It is a example .ConcurrentModificationException in single thread
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
//        for (Integer integer : integers) { //here we get ConcurrentModificationException
//            integers.remove(1);
//        }

        Iterator<Integer> iterator = integers.iterator(); // by using iterator we can handle it
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2) {
                iterator.remove();
            }
        }

        for (Integer integer : integers) {
            System.out.println(integer);
        }

    }
}
