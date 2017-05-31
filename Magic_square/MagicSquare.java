package Magic_square;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicSquare {
    private ArrayList<int[][]> listOfArrays; // здесь будут храниться массивы
    public static final String INPUT_FILE_PATH = "src/MagicSquare/files/input.txt"; // путь к файлу входных данных
    public static final String OUTPUT_FILE_PATH = "src/MagicSquare/files/output.txt"; // путь к файлу выходных файлов
    int base;

    private void readFile(String fileName){
        listOfArrays = new ArrayList<int[][]>();
        try {
            Scanner input = new Scanner(new File(fileName).getAbsoluteFile());
            while (input.hasNextInt()){
                base = input.nextInt();
                int[][] tmp = new int[base][base];
                for (int i = 0; i < base; i++) {
                    for (int j = 0; j < base; j++) {
                        int inputNumber = input.nextInt();
                        tmp[i][j] = inputNumber;
                    }
                }
                listOfArrays.add(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void writeFile(String fileName, String result){ // медот вписывания данных в файл
        try {
            Writer writer = new FileWriter(fileName, true); // передаём имя файла, куда будем вписывать данные
            writer.append(result + "\n"); // записываем результат
            writer.flush(); // очищаем "место"
            writer.close(); // закрываем поток
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isSquareCorrect(int[][]arrayOfNumbers){
        //Квадрат называется магическим, если он состоит из n^2 разных чисел и его суммы по всем направлениям равны
        int base = arrayOfNumbers.length * arrayOfNumbers[0].length; // количество элементов в массиве
        Set<Integer> differentNumbers = new HashSet<Integer>();
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            for (int j = 0; j < arrayOfNumbers[0].length; j++) {
                differentNumbers.add(arrayOfNumbers[i][j]);
            }
        }
        return differentNumbers.size() == base;
    }

    public String isMagicSquare(int[][] arrayOfNumbers) {
        ArrayList<Integer> checkedSums = new ArrayList<Integer>();
        if(isSquareCorrect(arrayOfNumbers)) {
            int sumVertical = 0;
            int sumHorizontal = 0;
            int mainDiagonalSum = 0;//главная диагональ
            int sideDiagonalSum = 0;//побочная диагональ
            int n = arrayOfNumbers.length; //количество строк
            int m = arrayOfNumbers[0].length; //количество столбцов
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sumVertical += arrayOfNumbers[j][i];
                    sumHorizontal += arrayOfNumbers[i][j];
                }
                checkedSums.add(sumVertical);
                checkedSums.add(sumHorizontal);
                sumVertical = 0;
                sumHorizontal = 0;
                mainDiagonalSum += arrayOfNumbers[i][i];
                sideDiagonalSum += arrayOfNumbers[i][m - i - 1];
            }
            checkedSums.add(mainDiagonalSum);
            checkedSums.add(sideDiagonalSum);
            // convert to Array is redundant, because List has checkedSums.get(index) method
            return checkSums(checkedSums.toArray(new Integer[checkedSums.size()]));
            //return checkSumsViaSet(checkedSums); //TODO The second method
        }
        return "NO";
    }


    private String checkSumsViaSet(ArrayList<Integer> sums){
        return new HashSet<Integer>(sums).size() == 1 ? "YES" : "NO";
    }


    private String checkSums(Integer[] sums){
        for (int i = 0; i < sums.length - 1; i++) {
            if (sums[i].intValue() != sums[i + 1].intValue()) {
                return "NO";
            }
        }
        return "YES";
    }


    public void checkArrays(){
        readFile(INPUT_FILE_PATH);
        for (int[][] array : listOfArrays) {
            writeFile(OUTPUT_FILE_PATH, isMagicSquare(array));
        }
    }


    public static void main(String[] args) {
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.checkArrays();
    }



}

// катится катится голубой вагон