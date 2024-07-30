import java.util.*;
public class incher {
    protected static int precision = 2; //Параметр округления выходного значения
    protected static double inchInMm = 25.4; //Размер трубы в мм
    public static Map<Double, String> pipes = new HashMap<>(); //Словарь для хранения типовых размеров труб
    public static void main (String[] args) //Запуск программы
    {
        loadPipes();
        double mm;
        String postfix;
        String message = "Введите число цифрами, которое хотите перевести в дюймы из миллиметров"  + "\n";
        String inputMissmatchMessage = "Нужно ввести цифру";
        String correspondsMessage = ". Что соответствует типовому размеру ";
        String mayCorrespondsMessage = ". Что наиболее близко к типовому размеру ";
        String notCorrespondsMessage = ". Что не соотвует типовым размерам труб";
        String inches = "дюйм";
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        while (true) {
            try {
                String resultMessage = "Результат: ";
                mm = input.nextDouble();
                double calc = mm / inchInMm;
                calc = round(calc, precision);
                String result;
                postfix = fixNumerical(calc, "", "а", "ов");
                if (calc % 1 == 0) {
                    result = String.valueOf((int) calc);
                } else {
                    result = String.valueOf(calc);
                }
                resultMessage = (resultMessage + result + " " + inches + postfix);
                if (pipes.get(calc)!= null) {
                    resultMessage = (resultMessage + correspondsMessage + " " + pipes.get(calc));
                } else if (pipes.get((double) Math.round(round(calc, 2) * 10) / 10)!= null) {
                    resultMessage = (resultMessage + mayCorrespondsMessage + " " + pipes.get((double) Math.round(round(calc, 2) * 10) / 10));
                }
                else {
                    resultMessage = (resultMessage + notCorrespondsMessage + " ");
                }
                resultMessage = (resultMessage + "\n");
                System.out.print(resultMessage + message);
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println(inputMissmatchMessage + "\n");
            }
        }
    }
    private static String fixNumerical (double num, String... arr) //Метод для постановки окончаний числительных
    {
        String result;
        if (num % 1 != 0) {
            num = num - Math.floor(num);
            num = round(num, precision);
            int quantyfyer = 1;
            for (int i = 1; i <= precision; i++) {
                quantyfyer = quantyfyer * 10;
            }
            num = num * quantyfyer;
        }
        int num100 = (int) num % 100;
        if(num100 > 4 && num100 < 21) result = arr[2];
        else {
            int num10 = num100 % 10;
            if(num10 == 1) result = arr[0];
            else if(num10 > 1 && num10 < 5) result = arr[1];
            else result = arr[2];
        }
        return result;
    }
    private static double round (double value, int precision) //Метод для округления
    {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    private static void loadPipes() //Метод для загрузки типовых размеров труб
    {
        pipes.put(0.4, "3/8\"");
        pipes.put(0.5, "1/2\"");
        pipes.put(0.75, "3/4\"");
        pipes.put(1.0, "1\"");
        pipes.put(1.25, "1 1/4\"");
        pipes.put(1.5, "1 1/2\"");
        pipes.put(2.0, "2\"");
        pipes.put(2.5, "2 1/2\"");
        pipes.put(3.5, "3 1/2\"");
        pipes.put(3.0, "3\"");
        pipes.put(4.0, "4\"");
        pipes.put(5.0, "5\"");
        pipes.put(6.0, "6\"");
    }
}