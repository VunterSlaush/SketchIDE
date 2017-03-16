/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ide.scratch.frames;

import ide.sketch.managers.HighLightDocumentFilter;
import ide.sketch.managers.LineNumberManager;
import ide.sketch.managers.TextEditorManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Slaush
 */
public class MainFrame extends javax.swing.JFrame {
    
    public static int CTRL_KEY_CODE = 17;
    public static int SCAPE_KEY_CODE = 27;
    public static int MAX_FONT_SIZE = 25;
    public static int MIN_FONT_SIZE = 10;
    File file;
    boolean ctrlPressed;
    int fontSize;
    private boolean replacing;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        fontSize = 14;
        initComponents();
        initLineNumber();
        initTextPane();
        initShortcuts();
        addActions();
        addHighLiter();
        panelOpciones.setVisible(false);
        resultadoPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        fileNameTextView = new javax.swing.JLabel();
        panelOpciones = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        findTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        replaceTextField = new javax.swing.JTextField();
        checkCaseSensitive = new javax.swing.JCheckBox();
        sigButton = new javax.swing.JButton();
        replaceButton = new javax.swing.JButton();
        replaceAllBtn = new javax.swing.JButton();
        resultadoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newFileItem = new javax.swing.JMenuItem();
        openFileItem = new javax.swing.JMenuItem();
        saveFileItem = new javax.swing.JMenuItem();
        saveAsItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        findMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(textPane);

        fileNameTextView.setText("Archivo Nuevo");

        jLabel1.setText("Busqueda:");

        findTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Reemplazo:");

        checkCaseSensitive.setText("Sensibilidad Mayusculas y Minusculas");
        checkCaseSensitive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCaseSensitiveActionPerformed(evt);
            }
        });

        sigButton.setText("Siguiente");
        sigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigButtonActionPerformed(evt);
            }
        });

        replaceButton.setText("Reemplazar");
        replaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceButtonActionPerformed(evt);
            }
        });

        replaceAllBtn.setText("Reemplazar Todos");

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(findTextField)
                            .addComponent(replaceTextField)))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addComponent(checkCaseSensitive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sigButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(replaceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(replaceAllBtn)
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(replaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkCaseSensitive)
                    .addComponent(sigButton)
                    .addComponent(replaceButton)
                    .addComponent(replaceAllBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Resultado de la Compilacion");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout resultadoPanelLayout = new javax.swing.GroupLayout(resultadoPanel);
        resultadoPanel.setLayout(resultadoPanelLayout);
        resultadoPanelLayout.setHorizontalGroup(
            resultadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultadoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        resultadoPanelLayout.setVerticalGroup(
            resultadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        newFileItem.setText("Nuevo");
        jMenu1.add(newFileItem);

        openFileItem.setText("Abrir");
        openFileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileItemActionPerformed(evt);
            }
        });
        jMenu1.add(openFileItem);

        saveFileItem.setText("Guardar");
        saveFileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveFileItem);

        saveAsItem.setText("Guardar Como");
        jMenu1.add(saveAsItem);

        menuBar.add(jMenu1);

        jMenu2.setText("Texto");

        findMenuItem.setText("Buscar");
        findMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(findMenuItem);

        menuBar.add(jMenu2);

        jMenu3.setText("Ejecutar");
        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileNameTextView)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(resultadoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileNameTextView)
                .addGap(18, 18, 18)
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileItemActionPerformed
        if(file == null)
        {
          file = TextEditorManager.getInstance().saveToFile(textPane);
          if(file != null)
            fileNameTextView.setText(file.getName());
        }
        else
            TextEditorManager.getInstance().saveToFile(textPane, file);

    }//GEN-LAST:event_saveFileItemActionPerformed

    private void openFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileItemActionPerformed
        try {
            file = TextEditorManager.getInstance().loadFromFile(textPane);
            if(file != null)
                fileNameTextView.setText(file.getName());
        } catch (Exception ex) {
            file = null;
        }
    }//GEN-LAST:event_openFileItemActionPerformed

    private void checkCaseSensitiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCaseSensitiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCaseSensitiveActionPerformed

    private void replaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceButtonActionPerformed
        TextEditorManager.getInstance().replaceSelection(textPane, replaceTextField.getText());
    }//GEN-LAST:event_replaceButtonActionPerformed

    private void findMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findMenuItemActionPerformed
       panelOpciones.setVisible(true);
    }//GEN-LAST:event_findMenuItemActionPerformed

    private void findTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTextFieldActionPerformed
        
    }//GEN-LAST:event_findTextFieldActionPerformed

    private void sigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigButtonActionPerformed
        TextEditorManager.getInstance().search(textPane, findTextField.getText(), this.checkCaseSensitive.isSelected());
    }//GEN-LAST:event_sigButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkCaseSensitive;
    private javax.swing.JLabel fileNameTextView;
    private javax.swing.JMenuItem findMenuItem;
    private javax.swing.JTextField findTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newFileItem;
    private javax.swing.JMenuItem openFileItem;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JButton replaceAllBtn;
    private javax.swing.JButton replaceButton;
    private javax.swing.JTextField replaceTextField;
    private javax.swing.JPanel resultadoPanel;
    private javax.swing.JMenuItem saveAsItem;
    private javax.swing.JMenuItem saveFileItem;
    private javax.swing.JButton sigButton;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables

    private void initLineNumber() 
    {

        LineNumberManager tln = new LineNumberManager(textPane);
        jScrollPane1.setRowHeaderView( tln );
        tln.setBorderGap(2);
        tln.setDigitAlignment(tln.CENTER);
        tln.setUpdateFont(true);
    }
    
    private void initTextPane()
    {
        //textPane.setBackground(Color.red);
        textPane.setFont(createFont(fontSize));
    }
    
    private Font createFont(int size)
    {
        Font myFont;
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File("consolas.ttf"));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            myFont = new Font("serif", Font.PLAIN, size);
        }
        return myFont.deriveFont(Font.PLAIN, size);
    }
    
    private void initShortcuts()
    {
        KeyStroke shSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        this.saveFileItem.setAccelerator(shSave);
        KeyStroke shSaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
        saveAsItem.setAccelerator(shSaveAs);
        KeyStroke shOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        openFileItem.setAccelerator(shOpen);
        
        KeyStroke shNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        newFileItem.setAccelerator(shNew);
        
        KeyStroke shFind = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
        findMenuItem.setAccelerator(shFind);

    }
    
    private void addActions()
    {
        textPane.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                

                ctrlPressed = e.getKeyCode() == CTRL_KEY_CODE;
                if(e.getKeyCode() == SCAPE_KEY_CODE)
                    panelOpciones.setVisible(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               if(e.getKeyCode() == CTRL_KEY_CODE);
                ctrlPressed = false;
            }   
        });
        
        this.addMouseWheelListener((MouseWheelEvent e) -> {
            if(ctrlPressed) 
            {
                resizeFont(e.getUnitsToScroll() < 0);    
            }
        });
        
        
    }
    
    private void resizeFont(boolean zoomIn)
    {   
        if(zoomIn && fontSize< MAX_FONT_SIZE)
            fontSize++;
        else if(fontSize > MIN_FONT_SIZE)
            fontSize--;
        textPane.setFont(createFont(fontSize));
    }
    
    
    private void addHighLiter()
    {
        ((AbstractDocument) textPane.getDocument()).setDocumentFilter(new HighLightDocumentFilter(textPane));
    }
    
    
}
