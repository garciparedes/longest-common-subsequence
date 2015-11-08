/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import algorithm.LCS;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import view.View;

/**
 *
 * @author garciparedes
 */
public class Controller {
    
    private final View view;
    private String fileLeft;
    private String fileRight;
    
    public Controller(View view){
        this.view = view;
        this.fileLeft = "";
        this.fileRight = "";
    }
    
    public void loadFileLeft(){
        try {
            fileLeft = readFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }  
        update();
    }
    
    public void loadFileRight(){
        try {
            fileRight = readFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        } 
        update();
    }
    
    public void update(){
        
        String[] fileLeftLines = fileLeft.split("\\r?\\n");
        String[] fileRightLines = fileRight.split("\\r?\\n");
        
        int [][] lcs = LCS.lcsPositions(fileLeftLines, fileRightLines);
        
        view.setJTextAreaLeftText(fileLeft);
        view.setJTextAreaRightText(fileRight);

        view.colorJTextAreaLeftText(lcs[0],
                inverse(lcs[0], fileLeftLines.length));
        view.colorJTextAreaRightText(lcs[1],
                inverse(lcs[1], fileRightLines.length));

        view.setJLabelLCS("The Longest Common Subsequence of Lines is: "+ lcs[0].length);
    }
    
    
    private int[][] lcs(){
        String[] fileLeftLines = fileLeft.split("\\r?\\n");
        String[] fileRightLines = fileRight.split("\\r?\\n");
        return LCS.lcsPositions(fileLeftLines, fileRightLines);
    }
    
    
    private static String readFile(String path, Charset encoding)
            throws IOException { 
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private String readFile()
            throws IOException {
        JFileChooser file=new JFileChooser(".");
        file.showOpenDialog(view);
        return readFile(file.getSelectedFile().getAbsolutePath());
    }
    
    private static String readFile(String path)
            throws IOException {
        return readFile(path, Charset.defaultCharset());
    }
   
    
    public static int[] inverse(int[] elements, int size){
        ArrayList<Integer> inverseList = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            inverseList.add(i);
        }
        
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < elements.length; index++) {
            intList.add(elements[index]);
        }
        
        inverseList.removeAll(intList);
        
        int[] inverse = new int[inverseList.size()];
        
        for(int i = 0; i < inverse.length; i++){
            inverse[i] = inverseList.get(i);
        } 
        return inverse;
        
    }
    
}
