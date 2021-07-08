import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.samples.commands.basic.GetFilesFromPath;
import org.testng.annotations.Test;

public class GetFilesFromPathTest {
    @Test
    public void teste(){
        GetFilesFromPath a = new GetFilesFromPath();
        ListValue<String> values = a.action("C:/Temp",".*",false);

        System.out.println("==================");
        System.out.println("Expected First Value: " + values.get(1));
    }
}