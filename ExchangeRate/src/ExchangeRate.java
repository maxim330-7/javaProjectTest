import java.util.*;

public class ExchangeRate {
    public static Scanner sc = new Scanner(System.in);
    private static final List<String> CURRENCIES =
            Arrays.asList("USD", "EUR", "KZT", "CNY", "RUB");

    public static void main(String[] args) {
        int country;
        double userCount;
        System.out.println("-------Курс валют--------------");
        System.out.println("""
                Выберите валюту:
                USD - Доллар США (1)
                EUR - Евро (2)
                KZT - Казахстанский тенге (3)
                CNY - Китайский юань (4)
                RUB - Российский рубль (5)
                """);

        country = indexCountry();

        userCount = sc.nextDouble();
        System.out.println("----Курс относительно рубля----");
        Map<String, Double> rate = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            String to = getCountry(i);
            System.out.print("Введите курс " + to + ": ");
            rate.put(to, sc.nextDouble());
        }
        rate.put("RUB", 1.0D); //рубль равен себе же

        exchangeRate(userCount, country, rate);

        while (true){
            sc.nextLine();
            System.out.println("--------------------------------\n");
            System.out.print("Пересчитать? (Y/N): ");
            if (sc.nextLine().equalsIgnoreCase("Y")){
                country = indexCountry();
                userCount = sc.nextDouble();
                exchangeRate(userCount, country, rate);
            }else break;
        }
    }

    private static int indexCountry(){
        int number;
        String numIndexCountry;

        while (true) {
            System.out.print("Введите номер нужной валюты: ");
            number = sc.nextInt();
            numIndexCountry = getCountry(number);
            if (numIndexCountry.equals("ERROR")) {
                System.out.println("Некорректный ввод");
            } else {
                System.out.print("Введите значение " + numIndexCountry + ": ");
                break;
            }
        }

        return number;
    }

    private static void exchangeRate(double x, int indexCountru, Map<String, Double> rate){
        String from = getCountry(indexCountru);

        System.out.println("--------------------------------");

        for (int i = 1; i < CURRENCIES.size()+1; i++){
            String to = getCountry(i);
            double result = x * rate.get(from) / rate.get(to);
            System.out.printf("%s %s = %.2f %s\n", x, from, result, to);
        }
    }

    private static String getCountry(int n){
        if (n-1 >= CURRENCIES.size()){
            return "ERROR";
        }else return CURRENCIES.get(n - 1);
    }
}
