package Basic;
//a bunch of functions that are repeated throughout the project


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


public class Basic {
    public static void writeToFile(File file,String new_file_contents){
        try{
            FileWriter writer = new FileWriter(file);
            if(file.exists()){
                if(file.canWrite()){
                    writer.write(new_file_contents);
                }
                else{
                    JOptionPane.showMessageDialog(null,"File cannot be written to - please change permissions");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"File does not exist");
            }
            writer.close();
        }
        catch(IOException ioexcept){
            ioexcept.printStackTrace();
            System.out.println("Something went wrong when writing to a file");
        }
    }

    public static String readFromFile(File file){
        try {
            String string_file_contents = "";

            FileReader reader = new FileReader(file);
            char[] array_contents = new char[(int) file.length()];
            reader.read(array_contents, 0, array_contents.length);
            for(char c: array_contents){
                string_file_contents+= String.valueOf(c);
            }                
            System.out.println(string_file_contents);
            reader.close();

            return string_file_contents;
        } 
        catch (IOException ioexcept) {
            ioexcept.printStackTrace();
            JOptionPane.showMessageDialog(null,"cannot read data from file (update permissions?)");
            return "";
        }
    }
}
