package utils;

import com.opencsv.CSVReader;

import dto.ContactoDTO;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {
        public static List<ContactoDTO> loadContactsFromCsv(String path) {
        List<ContactoDTO> contactos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] row;

            // Saltar cabecera si la hay
            reader.readNext();

            while ((row = reader.readNext()) != null) {
                String nombre = row[0];
                String email = row[1];
                String telefono =row[2];
                ContactoDTO contacto = new ContactoDTO();
                contacto.setNombre(nombre);
                contacto.setEmail(email);
                contacto.setTelefono(telefono);
                contactos.add(contacto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contactos;
    }
}