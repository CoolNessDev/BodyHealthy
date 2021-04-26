package aplication.upn.BodyHealthy;

import aplication.upn.BodyHealthy.Service.RutinaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class RutinaTest {
    @Autowired
    RutinaService rutinaService;

    @Test
    public void testGetRutina(){
        System.out.println(rutinaService.getRutina(1));
    }
}
