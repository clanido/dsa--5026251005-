package lw01.unguided;


import java.io.FileNotFoundException;
import java.util.Scanner;

import lw01.prelab.ColourPrint;
import lw01.prelab.MonoPrint;
import lw01.prelab.PrintJob;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("rentals.txt"));

        String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File jobs.txt tidak ditemukan: " + e.getMessage());
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}

    }