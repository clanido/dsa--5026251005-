package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PlaylistManager playlistManager = new PlaylistManager();
        playlistManager.process("playlist.txt");
        playlistManager.display();

        WorkshopRegistration workshopRegistration = new WorkshopRegistration();
        workshopRegistration.process("participants.txt");
        workshopRegistration.display();

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.process("inventory.txt");
        inventoryManager.display();
    }

    public static File resolveFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            return file;
        }
        File nestedFile = new File("src/lw03/prelab/" + filename);
        if (nestedFile.exists()) {
            return nestedFile;
        }
        return file;
    }
}

class PlaylistManager {
    private List<String> playlist = new ArrayList<>();

    public void process(String filename) {
        File file = Main.resolveFile(filename);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String command = sc.next();
                if (command.equalsIgnoreCase("ADD")) {
                    String song = sc.nextLine().trim();
                    playlist.add(song);
                } else if (command.equalsIgnoreCase("INSERT")) {
                    int index = sc.nextInt();
                    String song = sc.nextLine().trim();
                    playlist.add(index, song);
                } else if (command.equalsIgnoreCase("REMOVE")) {
                    String song = sc.nextLine().trim();
                    playlist.remove(song);
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public void display() {
        System.out.println("===== Problem 1");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }
}

class WorkshopRegistration {
    private Set<String> participants = new LinkedHashSet<>();
    private int duplicateRegistrations = 0;

    public void process(String filename) {
        File file = Main.resolveFile(filename);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (!participants.add(line)) {
                    duplicateRegistrations++;
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public void display() {
        System.out.println("Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String participant : participants) {
            System.out.println(rank + ". " + participant);
            rank++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }
}

class InventoryManager {
    private Map<String, Integer> inventory = new LinkedHashMap<>();
    private int failedSales = 0;

    public void process(String filename) {
        File file = Main.resolveFile(filename);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String type = sc.next();
                String product = sc.next();
                int quantity = sc.nextInt();

                if (type.equalsIgnoreCase("ADD")) {
                    if (inventory.containsKey(product)) {
                        inventory.put(product, inventory.get(product) + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }
                } else if (type.equalsIgnoreCase("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public void display() {
        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getKey().equals("Headset")) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("Failed sales: " + failedSales);
    }
}