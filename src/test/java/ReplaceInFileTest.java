import com.automationanywhere.botcommand.samples.commands.basic.ReplaceInFile;
import org.testng.annotations.Test;

public class ReplaceInFileTest {
    @Test
    public void teste(){
        System.out.println("==================INICIO======================");
        ReplaceInFile a = new ReplaceInFile();
        a.action("C:/Temp/08052021_PENDENCIAS_EM_ABERTO.txt","","","-1",true);

        System.out.println("==================FIM======================");
        //System.out.println("Expected First Value: " + values.get(1));
    }
}