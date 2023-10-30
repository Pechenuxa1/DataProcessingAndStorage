import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        Thread sorter = new Thread(new Sorter(list));
        sorter.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if(line.isEmpty()) {
                synchronized (list) {
                    System.out.println(list);
                }
            } else if (line.length() > 80) {
                String[] strings = line.split("(?<=\\G.{10})");
                for(String string : strings) {
                    synchronized (list) {
                        list.addFirst(string);
                    }
                }
            } else {
                list.addFirst(line);
            }
        }
    }
}