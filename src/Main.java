
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Numele zilelor săptămânii.
    private static final String[] ZILE = {
        "Luni", "Marti", "Miercuri", "Joi",
        "Vineri", "Sambata", "Duminica"
    };

    // Păstrăm toți elevii introduși sau încărcați.
    private static final List<CalculatorTransport> elevi =
        new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        // Repetăm meniul până când utilizatorul alege ieșirea.
        while (continua) {
            afiseazaMeniu();

            int optiune = citesteIntreg(scanner, "Alege opțiunea: ");

            switch (optiune) {
                case 1:
                    introduElevi(scanner);
                    break;

                case 2:
                    afiseazaElevi();
                    break;

                case 3:
                    comparaCheltuielile();
                    break;

                case 4:
                    salveazaDatele();
                    break;

                case 5:
                    incarcaDatele();
                    break;

                case 0:
                    continua = false;
                    System.out.println("Programul s-a încheiat.");
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
            }
        }

        scanner.close();
    }

    // Afișăm opțiunile disponibile.
    private static void afiseazaMeniu() {
        System.out.println("\n======================================");
        System.out.println(" CALCULATOR CHELTUIELI TRANSPORT");
        System.out.println("======================================");
        System.out.println("1. Introdu elevi și cheltuieli");
        System.out.println("2. Afișează toți elevii");
        System.out.println("3. Compară cheltuielile elevilor");
        System.out.println("4. Salvează datele în CSV");
        System.out.println("5. Încarcă datele din CSV");
        System.out.println("0. Ieșire");
        System.out.println("======================================");
    }

    // Citim și validăm numărul elevilor.
    private static void introduElevi(Scanner scanner) {

        int numarElevi;

        do {
            numarElevi = citesteIntreg(
                scanner,
                "Câți elevi dorești să introduci? "
            );

            if (numarElevi <= 0) {
                System.out.println(
                    "Trebuie să introduci cel puțin un elev."
                );
            }

        } while (numarElevi <= 0);

        // Adăugăm elevii noi la lista existentă.
        for (int i = 1; i <= numarElevi; i++) {

            System.out.println("\n--- Elevul " + i + " ---");

            String nume;

            // Verificăm dacă numele nu este gol.
            do {
                System.out.print("Numele elevului: ");
                nume = scanner.nextLine().trim();

                if (nume.isEmpty()) {
                    System.out.println("Numele nu poate fi gol.");
                }

            } while (nume.isEmpty());

            double[] cheltuieli = new double[7];

            // Citim cheltuiala pentru fiecare zi.
            for (int zi = 0; zi < 7; zi++) {
                cheltuieli[zi] = citesteCheltuiala(
                    scanner,
                    "Cheltuiala pentru " + ZILE[zi] + " (lei): "
                );
            }

            elevi.add(new CalculatorTransport(nume, cheltuieli));

            System.out.println("Elevul a fost adăugat.");
        }
    }

    // Citim un număr întreg valid.
    private static int citesteIntreg(Scanner scanner, String mesaj) {

        while (true) {
            System.out.print(mesaj);
            String text = scanner.nextLine().trim();

            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Introdu un număr întreg valid.");
            }
        }
    }

    // Citim o cheltuială și respingem valorile invalide.
    private static double citesteCheltuiala(
        Scanner scanner,
        String mesaj
    ) {

        while (true) {
            System.out.print(mesaj);

            String text = scanner.nextLine()
                .trim()
                .replace(',', '.');

            try {
                double valoare = Double.parseDouble(text);

                if (Double.isFinite(valoare) && valoare >= 0) {
                    return valoare;
                }

                System.out.println(
                    "Cheltuiala trebuie să fie pozitivă sau zero."
                );

            } catch (NumberFormatException e) {
                System.out.println("Introdu o valoare numerică validă.");
            }
        }
    }

    // Afișăm rezumatul tuturor elevilor.
    private static void afiseazaElevi() {

        if (elevi.isEmpty()) {
            System.out.println("Nu există elevi înregistrați.");
            return;
        }

        for (CalculatorTransport elev : elevi) {
            afiseazaRezumat(elev);
        }
    }

    // Afișăm cheltuielile și calculele unui singur elev.
    private static void afiseazaRezumat(CalculatorTransport elev) {

        System.out.println(
            "\n========== " + elev.getNumeElev().toUpperCase()
                + " =========="
        );

        for (int i = 0; i < ZILE.length; i++) {
            System.out.printf(
                "%-10s: %.2f lei%n",
                ZILE[i],
                elev.getCheltuiala(i)
            );
        }

        System.out.printf(
            "Total săptămânal: %.2f lei%n",
            elev.totalSaptamanal()
        );

        System.out.printf(
            "Medie zilnică: %.2f lei%n",
            elev.medieZilnica()
        );

        System.out.printf(
            "Estimare lunară: %.2f lei%n",
            elev.estimareLunara()
        );

        System.out.printf(
            "Cost estimat pentru 20 de zile: %.2f lei%n",
            elev.costPentruZile(20)
        );
    }

    // Comparăm elevii și îi ordonăm după estimarea lunară.
    private static void comparaCheltuielile() {

        if (elevi.isEmpty()) {
            System.out.println("Nu există elevi de comparat.");
            return;
        }

        // Copiem lista pentru a păstra ordinea inițială.
        List<CalculatorTransport> clasament = new ArrayList<>(elevi);

        // Sortăm crescător după estimarea cheltuielilor lunare.
        clasament.sort(
            Comparator.comparingDouble(
                CalculatorTransport::estimareLunara
            )
        );

        System.out.println("\n=== COMPARAȚIA CHELTUIELILOR ===");
        System.out.println("Ordine: de la cel mai mic cost la cel mai mare.");

        for (int i = 0; i < clasament.size(); i++) {
            CalculatorTransport elev = clasament.get(i);

            System.out.printf(
                "%d. %-20s | Săptămână: %8.2f lei | Lună: %8.2f lei%n",
                i + 1,
                elev.getNumeElev(),
                elev.totalSaptamanal(),
                elev.estimareLunara()
            );
        }

        // Primul elev are cea mai mică estimare lunară.
        CalculatorTransport minim = clasament.get(0);

        // Ultimul elev are cea mai mare estimare lunară.
        CalculatorTransport maxim = clasament.get(clasament.size() - 1);

        System.out.printf(
            "%nCheltuiala lunară cea mai mică: %s (%.2f lei)%n",
            minim.getNumeElev(),
            minim.estimareLunara()
        );

        System.out.printf(
            "Cheltuiala lunară cea mai mare: %s (%.2f lei)%n",
            maxim.getNumeElev(),
            maxim.estimareLunara()
        );

        System.out.printf(
            "Diferența dintre estimări: %.2f lei%n",
            maxim.estimareLunara() - minim.estimareLunara()
        );
    }

    // Salvăm lista curentă de elevi în fișierul CSV.
    private static void salveazaDatele() {

        try {
            GestionareFisierCSV.salveaza(elevi);
        } catch (IOException e) {
            System.out.println(
                "Eroare la salvarea datelor: " + e.getMessage()
            );
        }
    }

    // Încărcăm datele din CSV și înlocuim lista curentă.
    private static void incarcaDatele() {

        try {
            List<CalculatorTransport> eleviIncarcati =
                GestionareFisierCSV.incarca();

            elevi.clear();
            elevi.addAll(eleviIncarcati);

            System.out.println("Lista elevilor a fost actualizată.");

        } catch (IOException e) {
            System.out.println(
                "Eroare la încărcarea datelor: " + e.getMessage()
            );
        }
    }
}
