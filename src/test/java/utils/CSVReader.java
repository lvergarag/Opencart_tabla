package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> getData(String path) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // Saltar la primera línea (encabezados: product,code)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // 1. Limpiar espacios en blanco y omitir si la línea está vacía
                // o si comienza con '#' (comentario)
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }

                // 2. Dividir la línea por comas
                String[] values = line.split(",");

                // 3. Validar que la línea tenga al menos las dos columnas esperadas
                if (values.length >= 2) {
                    data.add(values);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return data;
    }
}