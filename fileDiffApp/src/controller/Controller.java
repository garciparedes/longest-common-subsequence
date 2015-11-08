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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            fileLeft = readFile("src/algorithm/LCS.java");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        view.setJTextAreaLeftText(fileLeft);
        view.colorJTextAreaLeftText(lcsToLeft());
        view.colorJTextAreaRightText(lcsToRight());

    }
    
    public void loadFileRight(){
        try {
            fileRight = readFile("src/algorithm/LCS.java");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        view.setJTextAreaRightText(fileRight);
        
        view.colorJTextAreaLeftText(lcsToLeft());
        view.colorJTextAreaRightText(lcsToRight());
    }
    
    
    private int[] lcsToLeft(){
        String[] fileLeftLines = fileLeft.split("\\r?\\n");
        String[] fileRightLines = fileRight.split("\\r?\\n");
        return LCS.lcsPositionsOfY(fileRightLines
            , fileLeftLines);
    }
    
    
    private int[] lcsToRight(){
        String[] fileLeftLines = fileLeft.split("\\r?\\n");
        String[] fileRightLines = fileRight.split("\\r?\\n");
        return LCS.lcsPositionsOfY(fileLeftLines
            , fileRightLines);
    }
    
    
    private static String readFile(String path, Charset encoding)
            throws IOException {
        
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    private static String readFile(String path)
            throws IOException {
        return readFile(path, Charset.defaultCharset());
    }
    
}
