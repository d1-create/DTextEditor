package widgets;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DMainWindow extends JFrame implements ActionListener {
    //instances of components
    DMenuBar menu_bar = new DMenuBar();
    DText text_box = new DText();
    
    //variables of class
    private String text_data = "";
    private String current_file_path = "";
    //get and set stuff with terrible looking code but it works
    public String getTextData(){return text_data;}
    public String getCurrentFilePath(){return current_file_path;}
    public void setTextData(String text_data){
        this.text_data = text_data;setTextBoxText();
    }
    public void setCurrentFilePath(String current_file_path) { 
        this.current_file_path = current_file_path;
    }

    //setup actions for the menu bar
    private void setupMenuBar(){
        menu_bar.exit_app.addActionListener(this);
        menu_bar.exit_app.setShortcut(new MenuShortcut(KeyEvent.VK_ESCAPE));
        menu_bar.new_file.addActionListener(this);
        menu_bar.new_file.setShortcut(new MenuShortcut(KeyEvent.VK_N));
        menu_bar.open_file.addActionListener(this);
        menu_bar.open_file.setShortcut(new MenuShortcut(KeyEvent.VK_O));
        menu_bar.save_file.addActionListener(this);
        menu_bar.save_file.setShortcut(new MenuShortcut(KeyEvent.VK_S));
        menu_bar.save_file_as.addActionListener(this);    }
    //setup actions for the text box
    private void setupTextBox(){
        text_box.getDocument().addDocumentListener(new DocumentListener() {
            //Gives notification that an attribute or set of attributes changed.
            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("Text Box Attributes Changed");
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                text_data = text_box.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                text_data = text_box.getText();
            }
        });
    }
    
    //add all actions
    private void addActions(){
        setupMenuBar();
        setupTextBox();
    }
    //clear all data 
    public void clearAll(){
        text_data = "";
        current_file_path = "";
        text_box.setText("");
    }
    //set text in textbox to text data
    public void setTextBoxText(){
        text_box.setText(text_data);
    }
    //mainly menubar logic
    @Override
    public void actionPerformed(ActionEvent event) {
        DMenuBar.findActionInMenu(this, menu_bar, event);
    }


    public DMainWindow(){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(800,600));
        setTitle("DText Editor");

        addActions();
        //add menu bar stuff
        setMenuBar(menu_bar);

        //add other stuff
        add(text_box);
    }

}
