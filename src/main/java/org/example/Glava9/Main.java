package org.example.Glava9;

import java.io.*;
import java.util.*;
import java.text.*;

public class Main {

    public static void readNumbersFromFile(String filename) throws IOException, InvalidNumberFormatException {
        List<Double> numbers = new ArrayList<>();
        Locale locale = Locale.getDefault();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    throw new InvalidNumberFormatException("Неверный формат строки в файле: " + line);
                }

                String localeStr = parts[0].toUpperCase();
                String numberStr = parts[1];

                switch (localeStr) {
                    case "US":
                        locale = Locale.US;
                        break;
                    case "DE":
                        locale = Locale.GERMANY;
                        break;
                    case "FR":
                        locale = Locale.FRANCE;
                        break;
                    default:
                        throw new InvalidNumberFormatException("Неизвестная локаль: " + localeStr);
                }

                try {
                    NumberFormat format = NumberFormat.getInstance(locale);
                    format.setParseIntegerOnly(false);
                    Number number = format.parse(numberStr);
                    double value = number.doubleValue();

                    if (value < Double.MIN_VALUE || value > Double.MAX_VALUE) {
                        throw new InvalidNumberFormatException("Число выходит за пределы допустимых значений: " + value);
                    }

                    numbers.add(value);
                } catch (ParseException e) {
                    throw new InvalidNumberFormatException("Невозможно распарсить число: " + numberStr);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Файл не найден: " + filename);
        } catch (OutOfMemoryError e) {
            throw new IOException("Недостаточно памяти для обработки файла.");
        }

        if (!numbers.isEmpty()) {
            double sum = 0;
            for (double num : numbers) {
                sum += num;
            }

            double average = sum / numbers.size();
            System.out.println("Сумма чисел: " + sum);
            System.out.println("Среднее значение: " + average);
        } else {
            System.out.println("Нет чисел для обработки.");
        }
    }

    public static void main(String[] args) {
        String filename = "src/main/java/org/example/Glava9/numbers";
        try {
            readNumbersFromFile(filename);
        } catch (IOException | InvalidNumberFormatException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
