import org.json.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

public class parseJSON {
    public static void main(String[] args) throws Exception {
        String jsonFileName = "test.json";

        List<Map<String, String>> browserCapList
                = new ArrayList<>();

        File f = new File(jsonFileName);
        if (f.exists()) {
            InputStream is = new FileInputStream(jsonFileName);
            String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);

            JSONObject json = new JSONObject(jsonTxt);
            JSONArray browserArray = json.getJSONArray("browsers");

            for (int i = 0; i < browserArray.length(); i++) {
                Map<String, String> currentMap = new HashMap<>();

                Iterator<?> keys = browserArray.getJSONObject(i).keys();

                for (String key : browserArray.getJSONObject(i).keySet()) {
                    currentMap.put(key, browserArray.getJSONObject(i).getString(key));
                }

                browserCapList.add(currentMap);
            }

            for (Map<String, String> aMap : browserCapList) {
                for (String key : aMap.keySet()) {
                    System.out.println("Current Key: " + key + " : Current Value: " + aMap.get(key));
                }
            }
        }


        System.out.println("test");
    }
}
