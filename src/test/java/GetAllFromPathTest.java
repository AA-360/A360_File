import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.samples.commands.basic.GetAllFromPath;
import org.testng.annotations.Test;

public class GetAllFromPathTest {
    @Test
    public void teste(){
        GetAllFromPath a = new GetAllFromPath();
        TableValue values = a.action("C:\\Users\\melque\\Documents",".*");

        System.out.println("==================");
        System.out.println("Expected First Value: " + values.get(1));
    }
}