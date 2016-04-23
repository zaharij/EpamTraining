package com.company.game;

import java.util.Random;
import java.util.Scanner;

import static com.company.game.DefaultValues.*;

/**
 * Java Game;
 * guess the number;
 *
 * creating random number in default range: 0 <= n <= 100, or input range manually;
 * input and check inputed number;
 * output the result
 * Created by Zakhar on 23.04.2016.
 */

public class JavaGame {
    private int inputMin = INPUT_MIN;
    private int inputMax = INPUT_MAX;
    private int inputNumber = DEFAULT_INPUT;
    private int randomNumber;
    private int [] inputNumberArray;
    private int indexArray;
    private boolean result;

    public JavaGame (int inputMin, int inputMax) {
        this.inputMin = inputMin;
        this.inputMax = inputMax;
        randomNumber = getRandomNumber();
    }

    public JavaGame () {
        randomNumber = getRandomNumber();
    }

    /**
     * start game
     * input number in given range
     */
    public void inputNumber () {
        inputNumberArray = new int[INPUT_MAX + TO_FULL_RANGE];
        printResult(GIVEN_RANGE + inputMin + RANGE + inputMax);
        begin:do {
            printResult(CURRENT_RANGE + inputMin + RANGE + inputMax);
            printResult(ANSWERS, true);
            printResult(LAST_INPUT + inputNumber);
            printResult(ENTER_NUMBER);
            Scanner scanIn = new Scanner(System.in);
            try{
                inputNumber = scanIn.nextInt();
                checkNumber();
            }catch(Exception ex){
                printResult(INPUT_INTEGER_ERROR);
                continue begin;
            }
        } while (!result);
    }

    /**
     * check inputed number
     * @return boolean result
     */
    private void checkNumber () {
        if(inputNumber < inputMin || inputNumber > inputMax){
            printResult(OUT_OF_RANGE + inputMin + RANGE + inputMax);
        }else if(inputNumber == randomNumber) {
            printResult(WIN_MESSAGE + inputNumber);
            printResult(ANSWERS, true);
            result = true;
        }else start:{
            for (int i = 0; i < indexArray; i++) {
                if (inputNumberArray[i] == inputNumber) {
                    printResult(ALREADY_TRIED, true);
                    break start;
                }
            }
            inputNumberArray[indexArray] = inputNumber;
            indexArray++;
            if(inputNumber > randomNumber){
                inputMax = inputNumber;
                printResult(GREATER_NUMBER);
            }else{
                inputMin = inputNumber;
                printResult(LESS_NUMBER);
            }
        }
    }

    /**
     * print result to console
     * @param resultString - String to print
     * @param printInputed - print inputed numbers or no (boolean)
     */
    private void printResult (String resultString, boolean printInputed) {
        System.out.print(resultString);

        if (printInputed) {
            for(int index = 0; index < indexArray; index++){
                System.out.print(inputNumberArray[index] + SEPARATOR);
            }
        }
    }

    /**
     * print result to console
     * @param resultString - String to print
     */
    private void printResult (String resultString) {
        printResult(resultString, false);
    }

    /**
     * get random number
     * @return random number (int)
     */
    private int getRandomNumber() {
        randomNumber = new Random().nextInt((inputMax + TO_FULL_RANGE) - inputMin) + inputMin;
        return randomNumber;
    }
}
