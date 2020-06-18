package JSON;

import Exceptions.UsernameAlreadyExistsException;
import Exceptions.UsernameOrPasswordWrongException;
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
import java.util.Objects;


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
    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }


    public void addUsers(User u) throws UsernameAlreadyExistsException {

      UserSer.FromAtoO();
      checkUserDoesNotAlreadyExist(u.getUsername());
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
    public  String  checkCredentials(String username,String password) throws UsernameOrPasswordWrongException {
      UserSer.FromAtoO();

      for(User u :users){
          if((u.getUsername().equals(username))&&(u.getPassword().equals(CryptWithMD5.cryptWithMD5(password)))) {
              String rol = u.getRol();
              return rol;
          }
      }
           throw new UsernameOrPasswordWrongException();
    }



    public static void main(String[] args) {
        User u1=new User("rare",CryptWithMD5.cryptWithMD5("ratata"),"dasd","s","d","k");
        User u2=new User("rares","df2s",CryptWithMD5.cryptWithMD5("darsd"),"s","d","k");
        UserSer userSer=new UserSer();
        try {
            userSer.addUsers(u1);
            userSer.addUsers(u2);
        }catch (UsernameAlreadyExistsException e){
            System.out.println("fds");;
        }
        try{
          userSer.checkCredentials("fifi","fifi");
        }catch (UsernameOrPasswordWrongException ex){
            System.out.println("mama");
        }



    }

    }





