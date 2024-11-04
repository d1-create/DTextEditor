package widgets;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Basic.Basic;

public class DMenuBar extends MenuBar{
    //create menu and menuitems
    Menu file_menu = new Menu("File");


    MenuItem exit_app = new MenuItem("Exit");
    MenuItem new_file = new MenuItem("New");
    MenuItem open_file = new MenuItem("Open");
    MenuItem save_file = new MenuItem("Save");
    MenuItem save_file_as = new MenuItem("Safe As");


    //add all menuitems to menu's 
    private void addMenuItems(){
        file_menu.add(exit_app);
        file_menu.add(new_file);
        file_menu.add(open_file);
        file_menu.add(save_file);
        file_menu.add(save_file_as);
    }

    //only instantiated once so what's the difference between static and non-static?
    public static void findActionInMenu(DMainWindow main_window,DMenuBar menu_bar,ActionEvent event){

        if(event.getSource()==menu_bar.exit_app){
            System.exit(0);
        }

        else if(event.getSource()==menu_bar.new_file){
            main_window.clearAll();
        }

        else if(event.getSource()==menu_bar.open_file){
            JFileChooser file_chooser = new JFileChooser(System.getProperty("user.home"));
            int dialog_result = file_chooser.showOpenDialog(null);
            if (dialog_result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file_chooser.getSelectedFile();
                main_window.setCurrentFilePath(selectedFile.getAbsolutePath());

                String text_data = Basic.readFromFile(selectedFile);
                main_window.setTextData(text_data);
            }
            else{
                JOptionPane.showMessageDialog(null,"Operation cancelled OR Could not open file");
            }
        }

        else if(event.getSource()==menu_bar.save_file){
            File file = new File(main_window.getCurrentFilePath());
            Basic.writeToFile(file, main_window.getTextData());
        } 

        else if(event.getSource()==menu_bar.save_file_as){
            JFileChooser file_chooser = new JFileChooser(System.getProperty("user.home"));
            int dialog_result = file_chooser.showSaveDialog(null);
            if(dialog_result==JFileChooser.APPROVE_OPTION){
                File file = file_chooser.getSelectedFile();
                main_window.setCurrentFilePath(file.getAbsolutePath());
                Basic.writeToFile(file, main_window.getTextData());
            }
            else{
                JOptionPane.showMessageDialog(null, "File error - did you cancel the operation? ");
            }
        }

    }

    //add everything on instantiation
    public DMenuBar(){
        addMenuItems();
        //add everything to main menu bar
        add(file_menu);
    }
}
