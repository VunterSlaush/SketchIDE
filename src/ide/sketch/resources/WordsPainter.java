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
    
    private final String [] ESTRUCTURAS = {
                               "setup", "loop"
                              };
    private final Color color_estructuras = new Color(0,151,156);;

    private final String [] FSINTAX = {
                              "#define", "#include", 
                              };
private final Color color_fsintax = new Color(63,81,181);                              

private final String [] CONSTANTE = {
                              "HIGH", "LOW", "INPUT", "OUTPUT", "INPUT_PULLUP",
                              "LED_BUILTIN", "true", "false", 
                              };
private final Color color_constante = new Color(216,67,21);  


private final String [] CALIFICADORES = {
                              "variable_scope", "static", "volatile", "const"
                              };
private final Color color_calificadores = new Color(48,63,159);

private final String [] UTILIDADES = {
                              "sizeof", "PROGMEM", 
                              };
private final Color color_utilidades = new Color(0,151,156);

private final String [] DIGITAL = {
                              "pinMode", "digitalWrite", "digitalRead", 
                              };
private final Color color_digital = new Color(0,151,156);

private final String [] ANALOGO = {
                              "analogReference", "analogRead", "analogWrite", 
                              };
private final Color color_analogo = new Color(0,151,156);

private final String [] DUEYZERO = {
                              "analogReadResolution", "analogWriteResolution", 
                              };
private final Color color_dueyzero = new Color(0,151,156);

private final String [] AVANZADOIO = {
                              "tone", "noTone", "shiftOut", "shiftIn", "pulseIn", 
                              };
private final Color color_avanzadoio = new Color(0,151,156);

private final String [] TIEMPO = {
                              "millis", "micros" , "delay", "delayMicroseconds", 
                              };
private final Color color_tiempo = new Color(0,151,156);

private final String [] MATEMATICA = {
                              "mmin", "max", "abs" , "constrain", "map", "pow", "sqrt", 
                              };
private final Color color_matematica = new Color(0,151,156);

private final String [] TRIGONOMETRIA = {
                              "sin", "cos", "tan", 
                                };
private final Color color_trigonometria = new Color(0,151,156);

private final String [] CARACTERES = {
                              "isAlphaNumeric", "isAlpha", "isAscii", "isWhitespace", "isControl", "isDigit", 
                              "isGraph", "isLowerCase", "isPrintable", "isPunct", "isSpace", "isUpperCase", 
                              "isHexadecimalDigit", 
                                };
private final Color color_caracteres = new Color(0,151,156);

private final String [] RANDOM = {
                              "randomSeed", "random", 
                                };
private final Color color_random = new Color(0,151,156);

private final String [] BITSYBYTES = {
                              "lowByte", "highByte", "bitRead", "bitWrite", 
                              "bitSet", "bitClear", "bit", 
                                };
private final Color color_bitsybytes = new Color(0,151,156);

private final String [] EINTERRUMPTS = {
                              "attachInterrupt", "detachInterrupt", 
                                };
private final Color color_einterrumpts = new Color(0,151,156);

private final String [] INTERRUMPTS = {
                              "interrupts", "noInterrupts", 
                                };
private final Color color_interrumpts = new Color(0,151,156);

private final String [] COMUNICACION = {
                              "Serial", "Stream", 
                                };
private final Color color_comunicacion = new Color(156,39,176);

private final String [] USB = {
                              "Keyboard" , "Mouse", 
                                };
private final Color color_usb = new Color(249,168,37);

    
    private HashMap<String [],Color> colorTable;
    private static WordsPainter instance;
    
    private WordsPainter()
    {
        colorTable = new HashMap<>();
        colorTable.put(TIPOS, color_tipos);
        colorTable.put(SENTENCIAS, color_sentencias);
        colorTable.put(USB,color_usb);
        colorTable.put(COMUNICACION,color_comunicacion);
        colorTable.put(INTERRUMPTS,color_interrumpts);
        colorTable.put(EINTERRUMPTS,color_einterrumpts);
        colorTable.put(BITSYBYTES,color_bitsybytes);
        colorTable.put(RANDOM,color_random);
        colorTable.put(CARACTERES, color_caracteres);
        colorTable.put(TRIGONOMETRIA, color_trigonometria);
        colorTable.put(MATEMATICA, color_matematica);
        colorTable.put(TIEMPO, color_tiempo);
        colorTable.put(AVANZADOIO, color_avanzadoio);
        colorTable.put(FSINTAX, color_fsintax);
        colorTable.put(CONSTANTE, color_constante);
        colorTable.put(DUEYZERO, color_dueyzero);
        colorTable.put(ESTRUCTURAS,color_estructuras);
        
    }
    
    public static WordsPainter getInstance()
    {
        if(instance == null)
            instance = new WordsPainter();
        return instance;
    }
    
    public Color getColor(String word)
    {   
        for(Map.Entry<String [],Color> par : colorTable.entrySet())
        {
           if(Arrays.asList(par.getKey()).contains(word))
               return par.getValue();
        }
        if(isNumber(word))
            return new Color(126,87,194);
        if(word.contains("("))
            return Color.GREEN;
        return Color.BLACK;
    }
}
