package lw02.unguided;

import java.util.*;

public class Main {

    public static void main(String[] args) {

    LinkedList<String[]> requests = new LinkedList<>();
    LinkedList<String[]> books = new LinkedList<>();
    LinkedList<String[]> members = new LinkedList<>();

    books.add(new String[]{"Kalkulus", "2"});
    books.add(new String[]{"Fisika", "1"});
    books.add(new String[]{"Statistika", "2"});

    Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();
        LinkedList<String[]> success = new LinkedList<>();

    Scanner scanner = new Scanner(
    Main.class.getResourceAsStream("borrowing.txt")
    );

    while (scanner.hasNext()) {
    String name = scanner.next();
        String bookTitle = scanner.next();
        requests.add(new String[]{name, bookTitle});

    boolean memberExists = false;
            for (String[] member : members) {
                if (member[0].equals(name)) {
                    memberExists = true;
                    break;
                }
            }

            if (!memberExists) {
                members.add(new String[]{name, "0"});
            }
        }

    scanner.close();
    queue.addAll(requests);

        while (!queue.isEmpty()) {

        String[] request = queue.poll();
        String name = request[0];
        String bookTitle = request[1];

        String[] currentBook = null;
            for (String[] b : books) {
                if (b[0].equals(bookTitle)) {
                    currentBook = b;
                    break;
                }
            }
 String[] currentMember = null;
            for (String[] m : members) {
                if (m[0].equals(name)) {
                    currentMember = m;
                    break;
                }
            }

            int stock = Integer.parseInt(currentBook[1]);
            int borrowed = Integer.parseInt(currentMember[1]);

            if (stock > 0 && borrowed < 2) {
                stock -= 1;
                borrowed += 1;

                currentBook[1] = String.valueOf(stock);
                currentMember[1] = String.valueOf(borrowed);

                success.add(request);
            } else {
                failed.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] req : success) {
            System.out.println(req[0] + " " + req[1]);
        }

        System.out.println("=== Remaining Book Stock ===");
        for (String[] b : books) {
            System.out.println(b[0] + ": " + b[1]);
        }

        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] req = failed.pop();
            System.out.println(req[0] + " " + req[1]);
        }
    }
}
     
           