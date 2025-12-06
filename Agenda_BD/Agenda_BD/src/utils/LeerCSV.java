package utils;

import com.opencsv.CSVReader;
import dto.ContactoDTO;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {

    // Ruta fija donde está el CSV
    private static final String CSV_PATH = "src/contactos.csv";
    // Cambia la ruta arriba si tu archivo está en otro sitio

    public static List<ContactoDTO> loadContactosFromCsv() {
        List<ContactoDTO> Contactos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_PATH))) {
            String[] row;

            // Saltar cabecera
            reader.readNext();

            while ((row = reader.readNext()) != null) {
                String nombre = row[0];
                String telefono = row[1];
                String email = row[2];

                ContactoDTO Contacto = new ContactoDTO();
                Contacto.setNombre(nombre);
                Contacto.setTelefono(telefono);
                Contacto.setEmail(email);

                Contactos.add(Contacto);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }

        return Contactos;
    }
