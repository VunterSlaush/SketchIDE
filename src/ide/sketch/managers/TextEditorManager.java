/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.sketch.managers;

/**
 *
 * @author Slaush
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

/*
 * Created on Apr 12, 2005
 * Barbara Lerner
 * 
 * This is a simple text editor that demonstrates String functionality.  The editor supports
 * searching, replacing, spelling checking and spelling correction.
 */
public class TextEditorManager 
{
    public static TextEditorManager instance;
	private JFileChooser chooser;
	
	private TextEditorManager() 
        {
            chooser = new JFileChooser();
        }
        
        public static TextEditorManager getInstance()
        {
            if(instance == null)
                instance = new TextEditorManager();
            return instance;
        }


	public File loadFromFile(JTextPane editor) throws BadLocationException {
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File loadFile = chooser.getSelectedFile();
			try {
				BufferedReader in = new BufferedReader (new FileReader(loadFile));
				editor.setText("");
				String nextLine = in.readLine();
                                StringBuffer buffer = new StringBuffer();
				while (nextLine != null) {
					//editor.(nextLine + "\n");
                                        buffer.append(nextLine + "\n");
					nextLine = in.readLine();
                                }
                                editor.getStyledDocument().insertString(0, buffer.toString(), null);
				in.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "El Archivo no pudo ser Cargado" + e.getMessage());
			}
                        return loadFile;
		}
                return null;
	}

	public File saveToFile(JTextPane editor) 
        {
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File saveFile = chooser.getSelectedFile();
			try {
				PrintWriter out = new PrintWriter (new FileWriter(saveFile));
				out.print (editor.getText());
				out.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "No se Pudo guardar el Archivo " + e.getMessage());
			}
                        return saveFile;
		}
                return null;
	}
        
        public void saveToFile(JTextPane editor, File file)
        {
            				
            try {
		PrintWriter out = new PrintWriter (new FileWriter(file));
		out.print (editor.getText());
		out.close();
		} 
            catch (IOException e)
            {
		JOptionPane.showMessageDialog(null, "No se Pudo guardar el Archivo " + e.getMessage());
	    }
        }


	public void replaceAll(JTextPane editor,String oldString, String newString) {
		if (!oldString.equals("")) {
			String editorText = editor.getText();
			editorText = editorText.replaceAll (oldString, newString);
			editor.setText(editorText);
		}
	}


	public void replaceSelection(JTextPane editor, String replace) 
        {

		String editorText = editor.getText();

		String start = editorText.substring (0, editor.getSelectionStart());
		String end = editorText.substring (editor.getSelectionEnd());

		editorText = start + replace + end;
		editor.setText (editorText);
	}

	public void search(JTextPane editor, String searchValue, boolean caseSensitive) 
        {
		String editorText = editor.getText();
				
		if (caseSensitive) {
			editorText = editorText.toLowerCase();
			searchValue = searchValue.toLowerCase();
		}
		
		int start;
		start = editorText.lastIndexOf(searchValue, editor.getSelectionStart()-1);

		if (start != -1) {
			editor.setCaretPosition (start);
			editor.moveCaretPosition (start + searchValue.length());
			editor.getCaret().setSelectionVisible(true);
		}
	}
}
