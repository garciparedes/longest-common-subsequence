/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algorithm.LCS;
import view.View;

/**
 *
 * @author garciparedes
 */
public class ProofMain {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        
        pruebas();
    }
    

    private static void pruebas() {

        
        String[] prueba1 = {"Lorem Ipsum is simply dummy text of the printing and typesetting "
                ,"industry. Lorem Ipsum has been the industry's standard dummy text "
                , "ever since the 1500s, when an unknown printer took a galley of type ",};
        
        String[] prueba2 = {"Lorem Ipsum is simply dummy text of the printing and typesetting "
                ,"industry. Lorem Ipsvum has been the industry's standard dummy text "
                , "ever since the 1500s, when an unknown printer took a galley of type ",};
        
        //LCS.lcs(prueba1,prueba2);
        
        Character[] a  = {'q', 'p'};
        Character[] b  = {'q', 'h', 'p'};

        int[] lcs = LCS.lcsPositionsOfY(a,b);
        //int[] lcs = LCS.lcs(prueba1,prueba2);
        for (int i = 0; i < lcs.length;i++){
            System.out.println(lcs[i]);
        }
        
    }
    
    public static Character[] strToCharArray(String str){
        char[] charArray = str.toCharArray();
        return null;
    }
}
