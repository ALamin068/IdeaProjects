import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RunMain {


    public static void main(String[] args) throws IOException {

        //instantiate CreateSheet object
        CreateSheet sheet2 = new CreateSheet();

        //instantiate DataGenerator class object
        DataGenerator dataGenerator = new DataGenerator();

        //stores the newly created sheet id in variable named 'id'.
        String id = sheet2.createSpreadsheet("Sheet2");

        List<List<Object>> aList = new LinkedList<List<Object>>();
        aList = dataGenerator.addDataToList();

        dataGenerator.updateValues(id, "Sheet1!A2:F", "RAW", aList);

    }
}