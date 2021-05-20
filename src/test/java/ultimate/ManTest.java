package ultimate;

import HomeTask21_04.Man;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManTest {
    @Test
    public void notEmptyMan()
    {
        Man man=new Man(1,"Matvei","Vecher",18);
        assertFalse(man == null);
    }
}
