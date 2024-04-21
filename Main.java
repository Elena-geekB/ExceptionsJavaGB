import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в следующем порядке через пробел: Фамилия Имя Отчество Дата_рождения Номер_телефона Пол");

        try {
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                throw new InvalidDataFormatException("Неверное количество данных");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String dateOfBirth = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            if (gender != 'f' && gender != 'm') {
                throw new InvalidDataFormatException("Неверный формат пола. Используйте 'f' или 'm'");
            }

            // Здесь можно добавить проверки формата даты рождения и номера телефона

            String filename = surname + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (InvalidDataFormatException | NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
