package com.appdet.quizhub;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Randomizer {

    public static int[] generateRandomArray(int minimum, int maximum, int length) {

        if (maximum - minimum + 1 < length) {
            System.out.println("Error: Range is smaller than the desired length of the array.");
            return null;
        }

        int[] numbers = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(maximum - minimum + 1) + minimum;
            while (contains(numbers, i, randomNumber)) {
                randomNumber = random.nextInt(maximum - minimum + 1) + minimum;
            }
            numbers[i] = randomNumber;
        }

        return numbers;
    }
    //2nd function
    public static boolean contains(int[] array, int endIndex, int value) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // palitan na lang minimum at maximum para sa pag generate ng number
        //tapos length ng array
        // no dupli narin yan
        //dalawang function or method nagawa kaya dapat ayung dalawa yung need
        int[] result = generateRandomArray(0,3,4);
        //System.out.println(Arrays.toString(result));
    }

}
