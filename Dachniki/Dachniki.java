package Dachniki;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dachniki {
    ArrayList<int[]> listOfArrays;
    public static final String INPUT_FILE_PATH = "src/Dachniki/input.txt"; // путь к файлу входных файлов
    public static final String OUTPUT_FILE_PATH = "src/Dachniki/output.txt"; // путь к файлу выходных файлов
    public static final int[][] field = new int[5000][5000];


    public void readFile(String fileName) { // метод чтения файлов
        listOfArrays = new ArrayList<int[]>(); // мы "загружаем" в список массивы
        try {
            Scanner input = new Scanner(new File(fileName).getAbsoluteFile()); // считыввается файл
            if(input.hasNextInt()){ // если есть следующий элемент, мы будем считывать данный файл
                int countOfArrays = input.nextInt(); // счёт массивов
                if(countOfArrays < 1 || countOfArrays > 1000) {
                    System.out.println("Incorrect number - number must be more or equals 1 or less or equals 1000");
                    return;
                }
            }
            while (input.hasNextInt()){ // пока ещё есть следующие элементы, мы будем считывать файл
                int n = input.nextInt(); // данные по строкам
                int[] tmp = new int[n]; // создаём массив /не основная переменная/
                for (int i = 0; i < n; i++) { // гуляемм по строкам
                    int inputNumber = input.nextInt(); // числа из массивов
                    tmp[i] = inputNumber; // записываем значения в массив
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

    public void checkArrays(){
        readFile(INPUT_FILE_PATH);
        for (int[] array : listOfArrays) {
//            writeFile(OUTPUT_FILE_PATH, );
        }
    }

//    public static String checkInput(int countOfArrays) {
//        Scanner in = new Scanner(INPUT_FILE_PATH);
//        if (in.hasNext()) {
//            countOfArrays = in.nextInt();
//        }
//        if(countOfArrays < 1 || countOfArrays > 1000) {
//            return "Incorrect number of people";
//        }
//        return "Correct number of people";
//    }
//
    public static void main(String[] args) {
        Dachniki d = new Dachniki();
        d.readFile(INPUT_FILE_PATH);
    }

    // TODO 1. сделать проверку входных данных

    /*
     *  натуральное число N (1 ≤ N ≤ 1000) – количество дачников, далее идут N строк, в каждой из которых описаны координаты каждого дачника и его участка:
     *  X Y X1 Y1 X2 Y2 X3 Y3 X4 Y4, где
     *  - (X,Y) – координаты приземления парашютиста
     *  - (X1, Y1, X2, Y2, X3, Y3, X4,Y4) – координаты прямоугольного участка на плоскости, указанные последовательно.
     *   Все координаты – целые числа, не превышающие 50000 по абсолютной величине
     */
    // TODO 2. сохранение входных данных
    // TODO 3. проверка данных (?)

}
