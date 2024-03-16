package hus.TestZone.ChessGameTest.TheGame;

import hus.TestZone.ChessGameTest.TheGame.Board.Board;

import java.util.Arrays;

public class Utility {


    public static void printIntArray(int[] arr) {
        if (arr == null) {
            System.out.println("Array is null");
            return;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static int[] mergeArrays(int[]... arrays) {
        int totalLength = 0;
        for (int[] array : arrays) {
            if (array != null) {
                totalLength += array.length;
            }
        }

        int[] mergedArray = new int[totalLength];
        int index = 0;
        for (int[] array : arrays) {
            if (array != null) {
                for (int num : array) {
                    mergedArray[index++] = num;
                }
            }
        }

        return mergedArray;
    }

    public static boolean containsElement(int[] arr, int target) {
        for (int element : arr) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }

    public static int rowColToId(int row, int col, int size) {
        return row * size + col;
    }

    public static int coordinateToId(String coordinate) {
        return Board.coordinateToId.getOrDefault(coordinate, -1);
    }

    public static int[] coordinatesArrayToIdsArray(String[] coordinates) {
        int[] ids = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            ids[i] = coordinateToId(coordinates[i]);
        }
        return ids;
    }

    public static String[] idsArrayToCoordinatesArray(int[] ids) {
        String[] coordinates = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            coordinates[i] = idToCoordinate(ids[i]);
        }
        return coordinates;
    }

    public static String idToCoordinate(int id) {
        return Board.idToCoordinate.getOrDefault(id, "");
    }

    public static int[] idToRowCol(int id, int size) {
        int row = id / size;
        int col = id % size;
        return new int[]{row, col};
    }

}
