package aplicatie.tester;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class Json_Test {
    @Test
    public void testCountRevision(){
        JsonParser parser=new JsonParser();
     InputStream inputStream= getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader=new InputStreamReader(inputStream);
      JsonElement rootElement= parser.parse(reader);
        JsonObject rootObject=rootElement.getAsJsonObject();
       JsonArray phoneNumbers= rootObject.getAsJsonArray("phoneNumbers");
       Assert.assertEquals(2, phoneNumbers.size());


       }


    }



