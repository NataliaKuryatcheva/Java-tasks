package Sudoku_Mine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {

    private List<int[][]> listOfArrays;
    public static final String INPUT_FILE_PATH = "src/Sudoku/input.txt";
    public static final String OUTPUT_FILE_PATH = "src/Sudoku/output.txt";

    public List<int[][]> getListOfArrays() {
        return listOfArrays;
    }

    public void readFile(String fileName) {
        listOfArrays = new ArrayList<>();
        int size;

        try {
            Scanner input = new Scanner(new File(fileName).getAbsoluteFile());
            while (input.hasNext()) {
                size = (int) Math.pow(input.nextInt(), 2);
                int[][] tmp = new int[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int inputNumber = input.nextInt(); // число, которое мы считываем (судоку)
                        tmp[i][j] = inputNumber;
                    }
                }
                listOfArrays.add(tmp); //  добавляем наше судоку в список
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String fileName, String result) {
        try {
            Writer writer = new FileWriter(fileName);
            writer.append(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String isSudoku(int[][] arraysOfNumbeers) {

        return "Correct";
    }

    public static void playSudoku() {
        Sudoku sudoku = new Sudoku(); // для того, чтобы можно было запустить данное приложение
        sudoku.readFile(INPUT_FILE_PATH);
        List<int[][]> arrays = sudoku.getListOfArrays();                // :)
        for (int[][] array : arrays) {                                  // это можно заделать в отдельный метод и вызывать
            sudoku.writeFile(OUTPUT_FILE_PATH, Sudoku.isSudoku(array)); // по рассписанию
        }
    }

    public static void main(String[] args) {
        Sudoku.playSudoku();

    }
}
