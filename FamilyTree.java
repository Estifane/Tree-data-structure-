
import java.util.HashMap;
import java.util.Scanner;

class FamilyMember {
    String name;
    String relationship;
    FamilyMember parent;

    public FamilyMember(String name, String relationship, FamilyMember parent) {
        this.name = name;
        this.relationship = relationship;
        this.parent = parent;
    }
}

public class FamilyTree {

    static HashMap<String, FamilyMember> familyMembers = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FamilyMember root = null;

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Member");
            System.out.println("2. Display Family Tree");
            System.out.println("3. Find Member");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addMember(scanner, root);
                    break;
                case 2:
                    displayTree(root);
                    break;
                case 3:
                    findMember(scanner);
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMember(Scanner scanner, FamilyMember root) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        if (familyMembers.containsKey(name)) {
            System.out.println("Member with this name already exists.");
            return;
        }

        System.out.print("Enter relationship to parent (e.g., father, mother, child): ");
        String relationship = scanner.nextLine();

        System.out.print("Enter parent's name (or 'root' if this is the root member): ");
        String parentName = scanner.nextLine();

        FamilyMember parent = null;

        if (parentName.equals("root")) {
            if (root != null) {
                System.out.println("The root member is already set.");
                return;
            }
            root = new FamilyMember(name, relationship, null);
        } else {
            if (familyMembers.containsKey(parentName)) {
                parent = familyMembers.get(parentName);
            } else {
                System.out.println("Parent member not found.");
                return;
            }
            FamilyMember newMember = new FamilyMember(name, relationship, parent);
            familyMembers.put(name, newMember);
        }
    }

    private static void displayTree(FamilyMember root) {
        if (root == null) {
            System.out.println("Family tree is empty.");
            return;
        }

        System.out.println("\nFamily Tree:");
        printTree(root, 0);
    }

    private static void printTree(FamilyMember member, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(member.name + " (" + member.relationship + ")");

        if (member.parent != null) {
            printTree(member.parent, level + 1);
        }
    }

    private static void findMember(Scanner scanner) {
        System.out.print("Enter member name to find: ");
        String name = scanner.nextLine();

        if (familyMembers.containsKey(name)) {
            FamilyMember member = familyMembers.get(name);
            System.out.println("\nMember found:");
            System.out.println("Name: " + member.name);
            System.out.println("Relationship: " + member.relationship);

            if (member.parent != null) {
                System.out.println("Parent: " + member.parent.name);
            } else {
                System.out.println("This is the root member.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }
}

