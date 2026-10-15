
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestionareFisierCSV {

    // Numele fișierului în care păstrăm datele.
    private static final String NUME_FISIER = "cheltuieli_elevi.csv";

    // Salvăm lista elevilor într-un fișier CSV.
    public static void salveaza(List<CalculatorTransport> elevi)
            throws IOException {

        Path caleFisier = Paths.get(NUME_FISIER);

        try (BufferedWriter scriitor = Files.newBufferedWriter(
                caleFisier, StandardCharsets.UTF_8)) {

            // Scriem antetul fișierului.
            scriitor.write(
                "Nume,Luni,Marti,Miercuri,Joi,Vineri,Sambata,Duminica"
            );
            scriitor.newLine();

            // Scriem câte un rând pentru fiecare elev.
            for (CalculatorTransport elev : elevi) {

                // Protejăm numele dacă acesta conține virgule sau ghilimele.
                scriitor.write(incadreazaCSV(elev.getNumeElev()));

                // Scriem cheltuielile zilnice.
                for (double valoare : elev.getCheltuieliZilnice()) {
                    scriitor.write(",");
                    scriitor.write(Double.toString(valoare));
                }

                scriitor.newLine();
            }
        }

        System.out.println("Datele au fost salvate în: "
                + caleFisier.toAbsolutePath());
    }

    // Încărcăm elevii din fișierul CSV.
    public static List<CalculatorTransport> incarca() throws IOException {

        Path caleFisier = Paths.get(NUME_FISIER);

        if (!Files.exists(caleFisier)) {
            throw new IOException(
                "Fișierul " + NUME_FISIER + " nu există."
            );
        }

        List<CalculatorTransport> elevi = new ArrayList<>();

        try (BufferedReader cititor = Files.newBufferedReader(
                caleFisier, StandardCharsets.UTF_8)) {

            String linie;
            int numarLinie = 0;

            while ((linie = cititor.readLine()) != null) {
                numarLinie++;

                // Ignorăm liniile goale.
                if (linie.trim().isEmpty()) {
                    continue;
                }

                // Prima linie conține antetul CSV.
                if (numarLinie == 1) {
                    continue;
                }

                try {
                    List<String> coloane = citesteColoane(linie);

                    // Fiecare rând trebuie să conțină numele și 7 valori.
                    if (coloane.size() != 8) {
                        throw new IllegalArgumentException(
                            "Rândul trebuie să conțină 8 coloane."
                        );
                    }

                    String nume = coloane.get(0).trim();

                    double[] cheltuieli = new double[7];

                    // Transformăm valorile din text în numere.
                    for (int i = 0; i < 7; i++) {
                        cheltuieli[i] = Double.parseDouble(
                            coloane.get(i + 1).trim()
                        );
                    }

                    // Constructorul verifică datele elevului.
                    elevi.add(new CalculatorTransport(nume, cheltuieli));

                } catch (IllegalArgumentException e) {
                    throw new IOException(
                        "Date invalide la linia " + numarLinie
                            + ": " + e.getMessage(),
                        e
                    );
                }
            }
        }

        System.out.println("Au fost încărcați " + elevi.size() + " elevi.");

        return elevi;
    }

    // Încadrăm numele între ghilimele dacă este necesar.
    private static String incadreazaCSV(String text) {
        if (text.contains(",") || text.contains("\"")
                || text.contains("\n") || text.contains("\r")) {

            return "\"" + text.replace("\"", "\"\"") + "\"";
        }

        return text;
    }

    // Separăm coloanele CSV, ținând cont de virgulele din ghilimele.
    private static List<String> citesteColoane(String linie)
            throws IOException {

        List<String> coloane = new ArrayList<>();
        StringBuilder valoare = new StringBuilder();
        boolean intreGhilimele = false;

        for (int i = 0; i < linie.length(); i++) {
            char caracter = linie.charAt(i);

            if (caracter == '"') {

                // Două ghilimele consecutive reprezintă o ghilimea în text.
                if (intreGhilimele && i + 1 < linie.length()
                        && linie.charAt(i + 1) == '"') {

                    valoare.append('"');
                    i++;

                } else {
                    intreGhilimele = !intreGhilimele;
                }

            } else if (caracter == ',' && !intreGhilimele) {

                coloane.add(valoare.toString());
                valoare.setLength(0);

            } else {
                valoare.append(caracter);
            }
        }

        // Un rând cu ghilimele neînchise este invalid.
        if (intreGhilimele) {
            throw new IOException("Ghilimele neînchise în fișierul CSV.");
        }

        coloane.add(valoare.toString());

        return coloane;
    }
}
