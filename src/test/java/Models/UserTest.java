package Models;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
private User user;
private JFXPanel panel=new JFXPanel();
    @Before
    public void setUp() throws Exception {
        user=new User("Rares","Salai","Calin","salaicostel@yahoo.com","0769190520","Cumparator");
    }

    @Test
    public void testEquals() {
        User user2=new User("Rares","Salai","Calin","salaicostel@yahoo.com","0769190520","Cumparator");
        Assert.assertTrue(user2.equals(user));

    }
    @Test
    public void testTelefon(){
        try{
        user=new User("Rares","Salai","Calin","salaicostel@yahoo.com","asdfasdfas","Cumparator");
        fail("nu a trecut");}
        catch (NumberFormatException e){
            System.out.println("testul e in re");
        }
        catch (IllegalArgumentException exception){
            System.out.println("testul e in regula");
        }
    }
    }
