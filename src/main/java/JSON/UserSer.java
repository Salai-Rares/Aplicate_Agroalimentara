package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserSer {
  protected static Gson gson=new GsonBuilder().setPrettyPrinting().create();
  protected static List<User> users=new ArrayList<>();
  protected Writer write;

  public static void FromAtoO(){
      try{

          Reader reader= Files.newBufferedReader(Paths.get("User1.json"));
          List<User> u = Arrays.asList(gson.fromJson(reader, User[].class));
          users.removeAll(users);
          users.addAll(u);
          reader.close();


      }catch (Exception ex){
          return;
      }

  }

    public void addUsers(User u) {

      UserSer.FromAtoO();
        try {
            write = Files.newBufferedWriter(Paths.get("User1.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
      users.add(u);
      try{
      gson.toJson(users,write);

            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        User u1=new User("rare",CryptWithMD5.cryptWithMD5("ratata"),"dasd","s","d","k");
        User u2=new User("mama","df2s",CryptWithMD5.cryptWithMD5("darsd"),"s","d","k");
        UserSer userSer=new UserSer();
            userSer.addUsers(u1);
            userSer.addUsers(u2);


    }

    }





