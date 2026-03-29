import java.security.SecureRandom;
import java.util.*;

public class Password {

    public static final String[] ARRPASSWORD = {
            "abcdefghijklmnopqrstuvwxyz",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "0123456789",
            "!@#$%^&*()-_=+"
    };

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Введите желаемую длину пароля (от 8 до 12): ");
            int userNumber = scanner.nextInt();
            if (userNumber >= 8 && userNumber <= 12){
                System.out.println(generatePassword(userNumber));
                System.out.print("\nСгенерировать ещё? (Y/N): ");
                scanner.nextLine();
                if (scanner.nextLine().equalsIgnoreCase("Y")) continue;
                else break;
            }
            else System.out.println("Неверный ввод!");
        }

    }

    private static String generatePassword(int lenPassword){
        String[] password = new String[lenPassword];
        SecureRandom random = new SecureRandom();
        System.out.print("Ваш пароль: ");

        //Заполняем
        for (int i = 0; i < lenPassword; i++) {
            String s = (i <= 3) ? ARRPASSWORD[i] : ARRPASSWORD[random.nextInt(ARRPASSWORD.length)];
            password[i] = String.valueOf(s.charAt(random.nextInt(s.length())));
        }

        //Перемешиваем
        int randomNumber;
        String temp;
        for (int i = password.length-1; i > 0; i--) {
            randomNumber = random.nextInt(i+1);
            temp = password[randomNumber];
            password[randomNumber] = password[i];
            password[i] = temp;
        }

        return String.join("", password);
    }
}
