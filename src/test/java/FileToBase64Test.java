import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.basic.Base64ToFile;
import com.automationanywhere.botcommand.samples.commands.basic.FileToBase64;
import com.automationanywhere.botcommand.samples.commands.basic.GetFileFolderDetails;
import org.testng.annotations.Test;

public class FileToBase64Test {
    @Test
    public void fileDetails(){
        GetFileFolderDetails a = new GetFileFolderDetails();

        DictionaryValue val = a.action("C:/Users/melque/Documents");


    }


    public void teste(){
        FileToBase64 a = new FileToBase64();
        Base64ToFile b = new Base64ToFile();

        StringValue values = a.action("C:/Temp/asd.exe");
        System.out.println("Expected First Value: " + values.toString());
        b.action(values.toString(),"C:/Temp/heroku.exe");

        System.out.println("ACABOU");
    }
}