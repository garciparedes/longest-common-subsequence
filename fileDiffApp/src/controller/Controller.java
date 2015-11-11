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
 * Controller class.
 * 
 * It connects the model with the View
 * 
 * @author garciparedes
 */
public class Controller {
    
    private final View view;
    private String fileLeft;
    private String fileRight;
    
    /**
     * Constructor of Controller.
     * 
     * Initializes the object and assigns the View as attribute.
     * Also initializes fileLeft and fileRight
     * 
     * @param view 
     */
    public Controller(View view){
        this.view = view;
        this.fileLeft = "";
        this.fileRight = "";
    }
    
    
    /**
     * loadFileLeft method.
     * 
     * It calls <code>readFile()</code> method 
     * and assigns it return to fileLeft
     * attribute, so it acts as fileLeft's setter.
     */
    public void loadFileLeft(){
        try {
            fileLeft = readFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }  
        update();
    }
    
    
    
    /**
     * loadFileRight method.
     * 
     * It calls <code>readFile()</code> method 
     * and assigns it return to fileRight
     * attribute, so it acts as fileRight's setter.
     */
    public void loadFileRight(){
        try {
            fileRight = readFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        } 
        update();
    }
    
    
    /**
     * update method.
     * 
     * It updates graphic interface with LCS algorithm results.
     */
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
    
    
    /**
     * readFile function.
     * 
     * It method reads the file.
     * 
     * @param path File's route.
     * @param encoding File's encoding.
     * 
     * @return Content of file.
     * 
     * @throws IOException 
     */
    private static String readFile(String path, Charset encoding)
            throws IOException { 
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

        /**
     * readFile function.
     * 
     * It method reads the file opens 
     * new windows to select file to read.
     * 
     * @return Content of file.
     * 
     * @throws IOException 
     */
    private String readFile()
            throws IOException {
        JFileChooser file=new JFileChooser(".");
        file.showOpenDialog(view);
        return readFile(file.getSelectedFile().getAbsolutePath());
    }
    
    
    /**
     * readFile function.
     * 
     * It method reads the file.
     * 
     * @param path File's route.
     * 
     * @return Content of file.
     * 
     * @throws IOException 
     */
    private static String readFile(String path)
            throws IOException {
        return readFile(path, Charset.defaultCharset());
    }
   
    
    /**
     * Inverse function.
     * 
     * It method calculates the inverse of 
     * input set from complete <code>size</code> set.
     * 
     * @param elements Set to inverse.
     * @param size Size of global set.
     * @return Integer array containing the inverse of input set.
     */
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
