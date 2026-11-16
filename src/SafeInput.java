import java.util.Scanner;

    public class SafeInput {

        public static int getRangedInt(Scanner console, String prompt, int low, int high) {
            int number;

            do {
                System.out.print(prompt + " (" + low + "-" + high + "): ");

                while (!console.hasNextInt()) {
                    System.out.println("Please enter a whole number.");
                    console.nextLine();
                    System.out.print(prompt + " (" + low + "-" + high + "): ");
                }

                number = console.nextInt();
                console.nextLine();

                if (number < low || number > high) {
                    System.out.println("Number must be between " + low + " and " + high);
                }

            } while (number < low || number > high);

            return number;
        }

        public static boolean getYNConfirm(Scanner console, String prompt) {
            String answer;

            do {
                System.out.print(prompt + " (Y/N): ");
                answer = console.nextLine();

            } while (!answer.equalsIgnoreCase("Y") &&
                    !answer.equalsIgnoreCase("N"));

            return answer.equalsIgnoreCase("Y");
        }
    }
