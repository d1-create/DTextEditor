import com.formdev.flatlaf.FlatDarkLaf;

import widgets.DMainWindow;

public class Main{
    public static void main(String[] args) {
        FlatDarkLaf.setup();//set to premade dark theme
        DMainWindow mainwindow = new DMainWindow();
        mainwindow.setVisible(true);
    }
}