package com.ad.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ad.agenda.entity.Contacto;
import com.ad.agenda.entity.Grupo;
import com.ad.agenda.repository.ContactoRepository;
import com.ad.agenda.repository.GrupoRepository;

import jakarta.transaction.Transactional;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner
{
    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private GrupoRepository grupoRepository;
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        Contacto alberto = new Contacto("Alberto", "alberto@example.com", "123456789");

        System.out.println("Contacto antes de guardar: " + alberto.getId());

        contactoRepository.save(alberto);

        System.out.println("Contacto después de guardar: " + alberto.getId());

        Grupo g1 = new Grupo("Letras");

        g1.addContacto(alberto);

        grupoRepository.save(g1);         
        
        System.out.println(alberto.getNombre() + " pertenece a los siguientes grupos:");
        for (Grupo g : alberto.getGrupos()) {
            System.out.println("- " + g.getNombre());
        }

        Contacto contactoActualizado = contactoRepository.findById(1L).orElse(null);

        System.out.println(contactoActualizado.getNombre() + " ACTUALIZADO pertenece a los siguientes grupos (después de recargar desde la base de datos):");
        for (Grupo g : contactoActualizado.getGrupos()) {
            System.out.println("- " + g.getNombre());
        }

        System.out.println("¿Es Alberto actualizado igual a Alberto? " + (contactoActualizado == alberto));

    }
    
}
