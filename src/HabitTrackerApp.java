import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Base class Habit
 * Demonstrates encapsulation, constructors, access modifiers.
 */
class Habit {
    private String name;
    private int goal;       // e.g., number of times per day
    private int progress;   // completed times

    // Constructor
    public Habit(String name, int goal) {
        this.name = name;
        this.goal = goal;
        this.progress = 0;
    }

    // Overloaded constructor (method overloading example)
    public Habit(String name) {
        this(name, 1); // default goal = 1
    }

    // Getters & Setters (Encapsulation)
    public String getName() { return name; }
    public int getGoal() { return goal; }
    public int getProgress() { return progress; }

    // Virtual method (can be overridden in subclass)
    public void markComplete() {
        if (progress < goal) {
            progress++;
            System.out.println(name + " marked complete! Progress: " + progress + "/" + goal);
        } else {
            System.out.println(name + " already completed for today!");
        }
    }

    public void resetProgress() {
        progress = 0;
        System.out.println("Progress reset for: " + name);
    }

    @Override
    public String toString() {
        return "Habit: " + name + " | Progress: " + progress + "/" + goal;
    }
}

/**
 * WeeklyHabit extends Habit
 * Demonstrates inheritance + method overriding (runtime polymorphism).
 */
class WeeklyHabit extends Habit {
    private int dayOfWeek;  // e.g., 1=Monday, 7=Sunday

    public WeeklyHabit(String name, int goal, int dayOfWeek) {
        super(name, goal);
        this.dayOfWeek = dayOfWeek;
    }

    // Overridden method
    @Override
    public void markComplete() {
        super.markComplete();
        System.out.println("(Weekly Habit, scheduled on day: " + dayOfWeek + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " | Weekly on day: " + dayOfWeek;
    }
}

/**
 * HabitTracker manages a list of Habits
 * Demonstrates aggregation (has-a relationship).
 */
class HabitTracker {
    private List<Habit> habits = new ArrayList<>();

    public void addHabit(Habit habit) {
        habits.add(habit);
        System.out.println("Habit added: " + habit.getName());
    }

    public void deleteHabit(String name) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(name)) {
                habits.remove(h);
                System.out.println("Habit deleted: " + name);
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    public void markHabit(String name) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(name)) {
                h.markComplete(); // dynamic binding (which markComplete is called depends on object type)
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    public void resetHabit(String name) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(name)) {
                h.resetProgress();
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    public void showHabits() {
        if (habits.isEmpty()) {
            System.out.println("No habits added yet.");
        } else {
            System.out.println("Your Habits:");
            for (Habit h : habits) {
                System.out.println(h);
            }
        }
    }

    public void searchHabit(String name) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(name)) {
                System.out.println("Found: " + h);
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    public void showStats() {
        int total = habits.size();
        int completed = 0;
        for (Habit h : habits) {
            if (h.getProgress() >= h.getGoal()) {
                completed++;
            }
        }
        int pending = total - completed;

        System.out.println("----- Stats -----");
        System.out.println("Total Habits: " + total);
        System.out.println("Completed Habits: " + completed);
        System.out.println("Pending Habits: " + pending);
    }
}

/**
 * Main Console App
 * Demonstrates control statements, Scanner I/O, loops, packages (default).
 */
public class HabitTrackerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HabitTracker tracker = new HabitTracker();

        int choice;
        do {
            System.out.println("\n=== Daily Habit Tracker (Console Version) ===");
            System.out.println("1. Add Habit");
            System.out.println("2. Add Weekly Habit");
            System.out.println("3. Mark Habit as Complete");
            System.out.println("4. View All Habits");
            System.out.println("5. Delete Habit");
            System.out.println("6. Search Habit");
            System.out.println("7. Reset Habit Progress");
            System.out.println("8. Show Statistics");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a number between 1-9.");
                sc.next(); // discard invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter habit name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter goal (times per day): ");
                    int goal = sc.nextInt();
                    sc.nextLine();
                    tracker.addHabit(new Habit(name, goal));
                    break;

                case 2:
                    System.out.print("Enter weekly habit name: ");
                    String wname = sc.nextLine();
                    System.out.print("Enter goal (times per week): ");
                    int wgoal = sc.nextInt();
                    System.out.print("Enter day of week (1=Mon...7=Sun): ");
                    int day = sc.nextInt();
                    sc.nextLine();
                    tracker.addHabit(new WeeklyHabit(wname, wgoal, day));
                    break;

                case 3:
                    System.out.print("Enter habit name to mark complete: ");
                    String habitName = sc.nextLine();
                    tracker.markHabit(habitName);
                    break;

                case 4:
                    tracker.showHabits();
                    break;

                case 5:
                    System.out.print("Enter habit name to delete: ");
                    String del = sc.nextLine();
                    tracker.deleteHabit(del);
                    break;

                case 6:
                    System.out.print("Enter habit name to search: ");
                    String search = sc.nextLine();
                    tracker.searchHabit(search);
                    break;

                case 7:
                    System.out.print("Enter habit name to reset: ");
                    String reset = sc.nextLine();
                    tracker.resetHabit(reset);
                    break;

                case 8:
                    tracker.showStats();
                    break;

                case 9:
                    System.out.println("Exiting Habit Tracker. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 9);

        sc.close();
    }
}
