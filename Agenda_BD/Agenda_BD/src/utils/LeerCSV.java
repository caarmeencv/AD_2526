package utils;

import dto.ContactoDTO;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;


public class LeerCSV {

    private static final String CSV_PATH = "src/contactos.csv";

    public static List<ContactoDTO> loadContactosFromCsv() {
        List<ContactoDTO> contactos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_PATH))) {
            String[] row;


            while ((row = reader.readNext()) != null) {
                String nombre = row[0];
                String telefono = row[1];
                String email = row[2];

                ContactoDTO contacto = new ContactoDTO();
                contacto.setNombre(nombre);
                contacto.setTelefono(telefono);
                contacto.setEmail(email);

                contactos.add(contacto);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }

        return contactos;
    }
}
