/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.sketch.resources;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 *
 * @author user
 */
public class WordsPainter 
{
    private final String [] TIPOS = {
                               "int", "void", "float",
                               "char", "word",
                               "string", "String"
                              };
    private final Color color_tipos = new Color(25,118,210);//Ejemplo, los colores son editables
    
    private final String [] SENTENCIAS = {
                               "if", "else", "for",
                               "switch", "case", "do", 
                               "while", "return",
                              };
    private final Color color_sentencias = new Color(216,27,96);//Ejemplo, los colores son editables
    
        private final String [] OPERADORES = {
                               "/", "=", "*",
                               "-", ">", "<", 
                               "+",
                              };
    private final Color color_operadores = new Color(216,27,96);//Ejemplo, los colores son editables
    
    private HashMap<String [],Color> colorTable;
    private static WordsPainter instance;
    
    private WordsPainter()
    {
        colorTable = new HashMap<>();
        colorTable.put(TIPOS, color_tipos);
        colorTable.put(SENTENCIAS, color_sentencias);
        colorTable.put(OPERADORES, color_operadores);
    }
    
    public static WordsPainter getInstance()
    {
        if(instance == null)
            instance = new WordsPainter();
        return instance;
    }
    
    public Color getColor(String word)
    {   
        System.out.println("Color for"+word);
        for(Map.Entry<String [],Color> par : colorTable.entrySet())
        {
           if(Arrays.asList(par.getKey()).contains(word))
               return par.getValue();
        }
        if(isNumber(word))
            return new Color(126,87,194);
        return Color.BLACK;
    }
}
