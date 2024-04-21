import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        System.setProperty("console.encoding", "UTF-8");
        System.setProperty("file.encoding", "UTF-8");

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

            String filename = generateUniqueFileName();
            Path filePath = Paths.get(filename);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8));
            writer.write(surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (InvalidDataFormatException | NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateUniqueFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        return "user_" + timestamp + ".txt";
    }
}
