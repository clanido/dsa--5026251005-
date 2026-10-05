package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map<String, Integer> courses = new LinkedHashMap<>();
        List<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0;

        while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ");
            String operation = parts[0];

            if (operation.equals("REGISTER")) {
                String courseCode = parts[1];
                int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else {
                    if (courses.containsKey(courseCode)) {
                        int currentCount = courses.get(courseCode);
                        courses.put(courseCode, currentCount + count);
                    } else {
                        courses.put(courseCode, count);
                    }
                }
            } else if (operation.equals("WITHDRAW")) {
            String courseCode = parts[1];
            int count = Integer.parseInt(parts[2]);

                if (count <= 0) {
                    rejectedOperations++;
                } else if (courses.containsKey(courseCode) && courses.get(courseCode) >= count) {
                    int currentCount = courses.get(courseCode);
                    courses.put(courseCode, currentCount - count);
                } else {
                    rejectedOperations++;
                }
          } else if (operation.equals("CHECK")) {
                String courseCode = parts[1];
                if (courses.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": " + courses.get(courseCode) + " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("===== Final Enrollment =====");
        for (String course : courses.keySet()) {
            System.out.println(course + ": " + courses.get(course) + " students");
        }
        System.out.println("Rejected operations: " + rejectedOperations);
    }
}