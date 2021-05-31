package aplication.upn.BodyHealthy;

import aplication.upn.BodyHealthy.Service.RutinaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RutinaTest {
    @Autowired
    RutinaService rutinaService;

    @Test
    public void getAllRutinaTest(){
        String actual =rutinaService.findAllDefault().get(0).getNombre();
        System.out.println(actual);
        String esperado="Principiante Divertida";
        assertEquals(esperado,actual);
    }
    @Test
    public void getRutinaTest(){
        String actual =rutinaService.getRutina(1).getNombre();
        System.out.println(actual);
        String esperado="Principiante Divertida";
        assertEquals(esperado,actual);
    }

}
