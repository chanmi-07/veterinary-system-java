import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VeterinaryService service = new VeterinaryService();
        Scanner input = new Scanner(System.in);
        ConsoleMenu menu = new ConsoleMenu(service, input);
        menu.start();
        input.close();
    }
}
