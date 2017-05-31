package Sympathetic_Arrays;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SympatheticArrays {
    private List<int[][]> listOfArrays; // здесь будут храниться массивы
    public static final String INPUT_FILE_PATH = "src/Sympathetic_Arrays/input.txt"; // путь к файлу входных данных
    public static final String OUTPUT_FILE_PATH = "src/Sympathetic_Arrays/output.txt"; // путь к файлу выходных файлов


    private void validateInputCountOfArrays(int base){ // количество возможных массивов
        if(base < 0 || base < 1 || base > 10){
            throw new IllegalArgumentException("Input size should be > 0 and 1<= size <=10"); // будет выдавать ошибку
        }
    }

    public void readFile(String fileName) { // метод чтения файлов
        listOfArrays = new ArrayList<int[][]>(); // мы "загружаем" в список массивы
        try {
            Scanner input = new Scanner(new File(fileName).getAbsoluteFile()); // считыввается файл
            if(input.hasNextInt()){ // если есть следующий элемент, мы будем считывать данный файл
                int countOfArrays = input.nextInt(); // счёт массивов
                validateInputCountOfArrays(countOfArrays); // проверка данных
            }
            while (input.hasNextInt()){ // пока ещё есть следующие элементы, мы будем считывать файл
                int n = input.nextInt(); // данные по строкам
                int m = input.nextInt(); // данные по столбцам
                int[][] tmp = new int[n][m]; // создаём массив /не основная переменная/
                for (int i = 0; i < n; i++) { // гуляемм по строкам
                    for (int j = 0; j < m; j++) { // гуляем по столбцам
                        int inputNumber = input.nextInt(); // числа из массивов
                        tmp[i][j] = inputNumber; // записываем значения в массив
                    }
                }
                listOfArrays.add(tmp); // добавляем массив в список
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // вернёт ошибку, в случае ошибки
        }
    }

    public void writeFile(String fileName, String result){ // медот вписывания данных в файл
        try {
            Writer writer = new FileWriter(fileName, true); // передаём имя файла, куда будем вписывать данные
            writer.append(result + "\n"); // записываем результат
            writer.flush(); // очищаем "место"
            writer.close(); // закрываем поток
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* arrayOfNumbers.length - количество строк
    *  arrayOfNumbers[0].length - количество столбцов
    * */
    public String isSympathetic(int[][] arrayOfNumbers) { // метод проверки, являются ли массивы симпатичными
        for (int i = 0; i < arrayOfNumbers.length - 1; i++) { // проходимся по строкам
            for (int j = 0; j < arrayOfNumbers[0].length - 1; j++) { // проходим по столбцам
                if (
                        arrayOfNumbers[i][j + 1] == arrayOfNumbers[i][j]             && // первый столбец сравниваем с нулевым столбцом
                                arrayOfNumbers[i][j] == arrayOfNumbers[i + 1][j + 1] && //0-ой ст., 0-ой стр. сравниваем с след. ст. и стр.
                                arrayOfNumbers[i][j] == arrayOfNumbers[i + 1][j] //0-ой ст., 0-ой стр. сравниваем со след. стр. этого же ст.
                        ) {
                    return "NO"; // если они все равны, то увы, этот массив не симпатичный
                }
            }
        }

        return "YES";
    }

    public void checkArrays(){
        readFile(INPUT_FILE_PATH);
        for (int[][] array : listOfArrays) {
            writeFile(OUTPUT_FILE_PATH, isSympathetic(array));
        }
    }

    public static void main(String[] args) {
        SympatheticArrays sympatheticArrays = new SympatheticArrays();
        sympatheticArrays.checkArrays();
    }
}