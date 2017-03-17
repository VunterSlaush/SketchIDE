/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.sketch.managers;

import ide.sketch.resources.WordsPainter;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
/**
 *
 * @author user
 */
public class HighLightDocumentFilter extends DocumentFilter
{
    private final StyledDocument styledDocument;
    private final JTextPane pane;
    private final StyleContext styleContext = StyleContext.getDefaultStyleContext();
    private final AttributeSet blackAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
    
    // Use a regular expression to find the words you are looking for
    
    private String [] WORDS = {
                               "int", "void", "float",
                               "char", "if", "else", "for",
                               "switch", "case", "do", 
                               "while", "return","goto",
                               "continue", "true", "false",
                               "include","unsigned","word",
                               "string", "String","([0-9])+"
                              
                              };
    Pattern pattern = buildPattern();
    public HighLightDocumentFilter(JTextPane comp)
    {
        styledDocument = comp.getStyledDocument();
        pane = comp;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
        text = text.replaceAll("\t", "  ");
        super.insertString(fb, offset, text, attributeSet);
        handleTextChanged();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        handleTextChanged();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
        text = text.replaceAll("\t", "  ");
        super.replace(fb, offset, length, text, attributeSet);
        handleTextChanged();
    }

    /**
     * Runs your updates later, not during the event notification.
     */
    private void handleTextChanged()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    updateTextStyles();
                } catch (BadLocationException ex) {
                    Logger.getLogger(HighLightDocumentFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Build the regular expression that looks for the whole word of each word that you wish to find.  The "\\b" is the beginning or end of a word boundary.  The "|" is a regex "or" operator.
     * @return
     */
    private Pattern buildPattern()
    {
        StringBuilder sb = new StringBuilder();
        for (String token : WORDS) 
        {
            sb.append("\\b"); // Start of word boundary
            sb.append(token);
            sb.append("\\b|"); // End of word boundary and an or for the next word
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
        }

        Pattern p = Pattern.compile(sb.toString());

        return p;
    }


    private void updateTextStyles() throws BadLocationException
    {
        // Clear existing styles
        styledDocument.setCharacterAttributes(0, pane.getText().length(), blackAttributeSet, true);

        // Look for tokens and highlight them
        Matcher matcher = pattern.matcher(styledDocument.getText(0, styledDocument.getLength()));
       
        while (matcher.find()) 
        {
            styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(),
                    generateAttributeSetForWord(styledDocument.getText(matcher.start(), matcher.end() - matcher.start()))
                    , false);
        }
    }
    
    private AttributeSet generateAttributeSetForWord(String word)
    {
        AttributeSet set = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, 
                WordsPainter.getInstance().getColor(word));
        return set;
    }

}