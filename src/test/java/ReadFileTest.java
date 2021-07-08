import com.automationanywhere.botcommand.data.impl.FileValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.File;
import com.automationanywhere.botcommand.samples.commands.basic.ReadFile;
import org.testng.annotations.Test;

import javax.swing.*;

public class ReadFileTest {
    @Test
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