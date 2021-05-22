package aplication.upn.BodyHealthy;

import aplication.upn.BodyHealthy.Service.EjercicioService;
import aplication.upn.BodyHealthy.Service.MusculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class EjercicioTest {
    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    MusculoService musculoService;

    @Test
    public void getAllEjercicoTest(){
        String actual =ejercicioService.getAll().get(0).getNombre();
        System.out.println(actual);
        String esperado="Burpees2";
        assertEquals(esperado,actual);
    }
    @Test
    public void getEjercicoTest(){
        String actual =ejercicioService.getEjercicio(2).getNombre();
        System.out.println(actual);
        String esperado="Burpees2";
        assertEquals(esperado,actual);
    }
    @Test
    public void getEjercicoByMusculoTest(){
        String actual =musculoService.getAllEjeciciosByMusculos(2).get(0).getNombre();
        System.out.println(actual);
        String esperado="Burpees2";
        assertEquals(esperado,actual);
    }
}
