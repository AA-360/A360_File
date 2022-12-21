import com.automationanywhere.botcommand.data.impl.FileValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.basic.ReadFile;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileTest {
    @Test
    public void tt() throws IOException {
        File  f = new File("C:\\Temp\\test1\\test2");
        Files.createDirectories(Paths.get("C://Temp//test1//test2"));
        //System.out.println(f.mkdir());
    }


    public void teste(){
        ReadFile a = new ReadFile();

        FileValue file = new FileValue("C:/Temp/test.txt");

        StringValue txt = a.action("C:/Temp/teste.txt");
        alert(txt.toString());

    }
    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}