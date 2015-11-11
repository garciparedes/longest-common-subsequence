/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;


/**
 * Longest Common Subsequence Algorithm.
 *
 * Static Class to solve Longest Common Subsequence problem
 *
 * @author garciparedes
 */
public class LCS{


    /**
     * Longest Common Subsequence Algorithm.
     *
     * It problem is solved by Dynamic programming.
     *
     * @param <E> Type of elements.
     * @param X First sequence of elements.
     * @param Y Second sequence of elements.
     * @return 2-dimensional array of integers containing
     * the positions of Longest Common Subsequence at inputs
     */
    public static <E> int[][] lcsPositions(E[] X, E[] Y){
        int m = X.length, n = Y.length;
        int T[][] = lcsTable(X, Y, m, n);
        
        int index= T[m][n];

        int[] lcsX = new int[index];
        int[] lcsY = new int[index];

        int i = m;
        int j = n;
        while(i > 0 && j > 0){
            if (X[i-1].equals(Y[j-1])){
                lcsX[index-1] = i-1;
                lcsY[index-1] = j-1;
                i--;
                j--;
                index--;
            }else if (T[i-1][j] > T[i][j-1]){
                i --;
            }else{
                j--;
            }
        }
        int[][] result = {lcsX,lcsY};
        return result;
    }


    /**
     * Longest Common Subsequence Algorithm.
     *
     * It problem is solved by Dynamic programming.
     *
     * @param <E> Type of elements.
     * @param X First sequence of elements.
     * @param Y Second sequence of elements.
     * @return Length of Longest Common Subsequence.
     */
    public static <E> int lcslen(E[] X, E[] Y){
        int m = X.length, n = Y.length;
        int  T[][] = lcsTable(X, Y, m, n);

        return T[m][n];
    }


    /**
     * Function that generates dynamic table,
     *
     * It problem is solved by Dynamic programming.
     *
     * @param <E> Type of elements.
     * @param X First sequence of elements.
     * @param Y Second sequence of elements.
     * @param m Length of first sequence of elements.
     * @param n Length of second sequence of elements.
     * @return 2-dimensional array of integers containing
     * the length of the possible subsequences.
     */
    private static <E> int[][] lcsTable(E[] X, E[] Y, int m, int n){

        int  T[][] = new int[m+1][n+1];

        for (int i = 1 ; i < m+1;i++){
            for (int j = 1 ; j < n+1;j++){
                if(X[i-1].equals(Y[j-1])){
                    T[i][j]= T[i-1][j-1] +1;
                }else{
                    T[i][j] = Math.max(T[i][j-1], T[i-1][j]);
                }
            }
        }
        return T;
    }
}
