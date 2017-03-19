/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.sketch.managers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Slaush
 */
public class ScreenOutManager 
{
    private static ScreenOutManager instance;
    private JTextPane screen;
    
    
    private ScreenOutManager()
    {
    
    }
    
    public static ScreenOutManager getInstance()
    {
        if(instance == null)
            instance = new ScreenOutManager();
        return instance;
    }
    
    public void changeScreen( JTextPane screen)
    {
        this.screen = screen;
    }
    
    public void addLine(String line) 
    {
        if(screen != null)
        {
            DefaultStyledDocument doc = (DefaultStyledDocument) screen.getStyledDocument();
            try {
                doc.insertString(doc.getLength(),line, null);
            } catch (BadLocationException ex) {
                Logger.getLogger(ScreenOutManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clearScreen()
    {
        if(screen != null)
           screen.setText("");
    }
}
