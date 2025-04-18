import java.util.Scanner;

public class ChatBot {

    public static String getResponse(String input) {
        input = input.toLowerCase();

        if (input.contains("hi") || input.contains("hello")) {
            return "Hello there! How can I help you today?";
        } else if (input.contains("how are you")) {
            return "I'm just a bot, but I'm doing fine. Thanks for asking!";
        } else if (input.contains("your name")) {
            return "I'm your friendly chatbot :)";
        } else if (input.contains("bye")) {
            return "Goodbye! Have a great day!";
        } else if (input.contains("help")) {
            return "Sure! You can ask me about general topics.";
        } 
        else {
            return "I'm not sure I understand. Can you rephrase that?";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ChatBot: Hello! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if (userInput.toLowerCase().contains("bye")) {
                System.out.println("ChatBot: " + getResponse("bye"));
                break;
            }

            String response = getResponse(userInput);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }
}
