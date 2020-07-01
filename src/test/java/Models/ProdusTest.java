package Models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProdusTest {
    private Produs produs;
    @Before
    public void setUp() throws Exception {
       produs=new Produs("sa",3,"sad","as");
    }
    @Test
    public void setPret() {
        try {
            produs.setPret(-2);
            fail("nu merge");
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Passed!");
        }
    }

}