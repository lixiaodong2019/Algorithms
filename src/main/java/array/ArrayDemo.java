package array;

import java.io.*;
import java.util.Arrays;


/**
 * 数组转稀疏数组
 */
public class ArrayDemo {

    public static void main(String[] args) {

        int[][] arr = getArr(1000, 1000);
//        showArr(arr);
        int[][] newArr = arr2xi(arr);
//        showArr(newArr);
        writeFileObj(newArr, "2.txt");
        writeFile(arr, "1.txt");
        int[][] arr2 = readFileObj("2.txt");
        assert arr2 != null;
//        showArr(arr2);
        System.out.println(Arrays.deepEquals(arr, arr2));
    }

    private static int[][] getArr(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = (int) (Math.random() * 3);
            }
        }
        return arr;
    }

    private static int[][] readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("1.txt"))) {
            String line = reader.readLine();
            String[] strArr = line.split("\t");
            int[][] newArr = new int[Integer.parseInt(strArr[2]) + 1][strArr.length];
            newArr[0][0] = Integer.parseInt(strArr[0]);
            newArr[0][1] = Integer.parseInt(strArr[1]);
            newArr[0][2] = Integer.parseInt(strArr[2]);
            int count = 1;
            while ((line = reader.readLine()) != null) {
                strArr = line.split("\t");
                for (int i = 0; i < strArr.length; i++) {
                    newArr[count][i] = Integer.parseInt(strArr[i]);
                }
                count++;
            }
            count = 1;
            int[][] arr = new int[newArr[0][0]][newArr[0][1]];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (count == newArr.length) {
                        break;
                    }
                    if (i == newArr[count][0] && j == newArr[count][1]) {
                        arr[i][j] = newArr[count++][2];
                    }
                }
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数组转稀疏数组
     *
     * @param arr
     * @return
     */
    private static int[][] arr2xi(int[][] arr) {
        //先得到稀疏数组的个数
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] newArr = new int[sum + 1][3]; //第一组用来保存数组的个数:  行 列 值
        newArr[0][0] = arr.length;
        newArr[0][1] = arr[0].length;
        newArr[0][2] = sum;
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    newArr[count][0] = i;
                    newArr[count][1] = j;
                    newArr[count++][2] = arr[i][j];
                }
            }
        }
        return newArr;
    }

    public static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void writeFile(int[][] arr, String FileName) {
        try (PrintWriter writer = new PrintWriter(FileName)) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    writer.print(arr[i][j]);
                    writer.print("\t");
                }
                writer.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileObj(int[][] arr, String fileName) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
            writer.writeObject(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int[][] readFileObj(String fileName) {
        try (ObjectInputStream reader
                     = new ObjectInputStream(new FileInputStream(fileName))) {
            int[][] newArr = (int[][]) reader.readObject();
            int[][] arr = new int[newArr[0][0]][newArr[0][1]];
            int count = 1;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (count == newArr.length) {
                        break;
                    }
                    if (i == newArr[count][0] && j == newArr[count][1]) {
                        arr[i][j] = newArr[count++][2];
                    }
                }
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
