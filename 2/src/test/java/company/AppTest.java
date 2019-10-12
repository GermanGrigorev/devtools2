package company;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

public class AppTest {
    
    @Test
    public void testApp() throws IOException {
        FileIni fileINI = new FileIni();
        fileINI.setInformation("C:\\maven2222\\2\\input.ini");

        double d = fileINI.getDoubleValue("ADC_DEV", "BufferLenSeconds");
        assertEquals(d, 0.65, 0.001);
    }
    @Test
    public void testInput() throws IOException {
        FileIni fileINI = new FileIni();
        fileINI.setInformation("C:\\maven2222\\2\\input.ini");
        FileIni testINI = new FileIni();
        testINI.setInformation("C:\\maven2222\\2\\test.ini");

        assertEquals(fileINI.toString(), testINI.toString()) ;
    }
}
