package aplication.upn.BodyHealthy;

import aplication.upn.BodyHealthy.Model.Musculo;
import aplication.upn.BodyHealthy.Security.Service.RolService;
import aplication.upn.BodyHealthy.Security.Service.UsuarioService;
import aplication.upn.BodyHealthy.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class GetTest {
    @Autowired
    RutinaService rutinaService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    PublicacionService publicacionService;
    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    ComentarioService comentarioService;
    @Autowired
    MusculoService musculoService;

    @Test
    public void testGetRutina() {
        System.out.println(rutinaService.getAll().get(0).getNombre());
    }

    @Test
    public void testGetByrol() {
        System.out.println(usuarioService.getByRol(1));
    }

    @Test
    public void testGetByMusculo() {
        System.out.println(musculoService.getAll().get(0).toString());
    }
}
