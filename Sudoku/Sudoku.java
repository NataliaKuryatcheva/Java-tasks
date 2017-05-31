package Sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
    private List<int[][]> listOfArrays;
    public static final String INPUT_FILE_PATH = "src/Sudoku/INPUT.txt";
    public static final String OUTPUT_FILE_PATH = "src/Sudoku/OUTPUT.txt";

    public List<int[][]> getListOfArrays(){
        return listOfArrays;
    }

    private void validateInputSize(int base){
        if(base < 0 || base < 1 || base > 10){
            throw new IllegalArgumentException("Input size should be > 0 and 1<= size <=10");
        }
    }

    private void validateInputNumber(int number, int base){
        if (number < 0 || number > 100 || number > base){
            throw new IllegalArgumentException("Input number of array should be positive and grate or equals 100 and grate or equals base");
        }
    }

    public void readFile(String fileName){
        listOfArrays = new ArrayList<int[][]>();
        int size;
        try {
            Scanner input = new Scanner(new File(fileName).getAbsoluteFile());
            while (input.hasNextInt()){
                int base = input.nextInt();
                validateInputSize(base);
                size = (int) Math.pow(base, 2);
                int[][] tmp = new int[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int inputNumber = input.nextInt();
                        validateInputNumber(inputNumber, size);
                        tmp[i][j] = inputNumber;
                    }
                }
                listOfArrays.add(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeFile(String fileName, String result) {
        try {
            Writer writer = new FileWriter(fileName, true);
            writer.append(result + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String isSudoku(int[][] arrayOfNumbers){
        List<Integer> checkedNumbers = new ArrayList<Integer>();
        for (int i = 0; i < arrayOfNumbers[0].length; i++) { // проход по столбцам
            if(checkedNumbers.contains(arrayOfNumbers[0][i])){
                return "Incorrect";
            }
            checkedNumbers.add(arrayOfNumbers[0][i]);
            for (int j = 1; j < arrayOfNumbers[0].length; j++) { // проход по строчкам текущего столбца
                if(arrayOfNumbers[0][i] == arrayOfNumbers[j][i]){
                    return "Incorrect";
                }
            }
        }

        return "Correct";
    }

    public void checkArrays(){
        readFile(INPUT_FILE_PATH);
        for (int[][] array : listOfArrays) {
            writeFile(OUTPUT_FILE_PATH, isSudoku(array));
        }

    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.checkArrays();
    }

}