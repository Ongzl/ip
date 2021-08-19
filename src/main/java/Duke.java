import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // scan the line for the user's input
        List list = new List();
        boolean shouldContinue = true;
        while (shouldContinue) {//create loop for the chat
            if (input.startsWith("done ")) {
                String[] parts = input.split(" ");
                String numStr = parts[1];
                int numInt = Integer.valueOf(numStr);
                System.out.println("Nice! I've marked this task as done: ");
                list.setIndexDone(numInt);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("todo ")) {
                list.addToDo(input.replaceFirst("todo ", ""));
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("deadline ")) {
                String task = input.replaceFirst("deadline ", "");
                String[] parts = task.split("/by");
                list.addDeadline(parts[0], parts[1]);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("event ")) {
                String task = input.replaceFirst("event ", "");
                String[] parts = task.split("/at");
                list.addEvent(parts[0], parts[1]);
                input = sc.nextLine();
                continue;
            }

            switch(input) {
                case "bye":
                case "Bye":
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    shouldContinue = false;
                    break;
                case "list":
                    list.show();
                    input = sc.nextLine();
                    break;

                default:
                    System.out.println("added: " + input);
                    list.addToDo(input);
                    input = sc.nextLine();
            }
        }
        sc.close();
    }
}
