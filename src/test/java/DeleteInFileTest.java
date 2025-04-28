import com.automationanywhere.botcommand.samples.commands.basic.DeleteRows;
import com.automationanywhere.botcommand.samples.commands.basic.ReplaceInFile;
import org.testng.annotations.Test;

public class DeleteInFileTest {
    @Test
    public void deleteRows(){
        System.out.println("==================INICIO======================");
        DeleteRows a = new DeleteRows();
        a.action("C:/Temp/asd.txt",0.0,0.0,true);

        System.out.println("==================FIM======================");
        //System.out.println("Expected First Value: " + values.get(1));
    }
}