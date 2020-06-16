package aplicatie.tester;
import JSON.CryptWithMD5;
import JSON.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
public class User_Test {
    public static void main(String[] args) {
        JSONArray jrr=new JSONArray();
        JSONObject obj=new JSONObject();
        JSONObject obj2=new JSONObject();

        User u1 = new User("Dyablohd",CryptWithMD5.cryptWithMD5("parola"),"Rares", "salaicostel@yahoo.com", "0769190520", "Magazin");
        User u2 = new User("Hanibal",CryptWithMD5.cryptWithMD5("idk"),"Raresawe", "salaicostel@yahooaseaw.com", "076919eqw0520", "Client");
        obj.put("username",u1.getUsername());
        obj.put("password",u1.getPassword());
        obj.put("email",u1.getEmail());
        obj.put("telefon",u1.getTelefon());
        obj.put("rol",u1.getRol());
        obj2.put("username",u2.getUsername());
        obj2.put("password",u2.getPassword());
        obj2.put("email",u2.getEmail());
        obj2.put("telefon",u2.getTelefon());
        obj2.put("rol",u2.getRol());
        jrr.add(obj);
        jrr.add(obj2);


        String fileName = "src/main/Users.json";

        try{
            FileWriter fileWriter=new FileWriter(fileName);
            fileWriter.write(jrr.toJSONString());
            fileWriter.close();
        }catch (Exception e){
            e.getStackTrace();
        }


    }
}
