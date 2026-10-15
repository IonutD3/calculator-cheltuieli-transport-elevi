
public class CalculatorTransport {

    // Numele elevului.
    private final String numeElev;

    // Cheltuielile de transport pentru cele șapte zile.
    private final double[] cheltuieliZilnice;

    // Inițializăm datele elevului.
    public CalculatorTransport(String numeElev, double[] cheltuieliZilnice) {

        if (numeElev == null || numeElev.trim().isEmpty()) {
            throw new IllegalArgumentException("Numele elevului este obligatoriu.");
        }

        if (cheltuieliZilnice == null || cheltuieliZilnice.length != 7) {
            throw new IllegalArgumentException("Sunt necesare 7 cheltuieli.");
        }

        // Verificăm toate cheltuielile.
        for (double valoare : cheltuieliZilnice) {
            if (!Double.isFinite(valoare) || valoare < 0) {
                throw new IllegalArgumentException(
                    "Cheltuielile trebuie să fie numere pozitive sau zero."
                );
            }
        }

        this.numeElev = numeElev.trim();
        this.cheltuieliZilnice = cheltuieliZilnice.clone();
    }

    // Returnăm numele elevului.
    public String getNumeElev() {
        return numeElev;
    }

    // Returnăm cheltuiala pentru o zi, cu indice între 0 și 6.
    public double getCheltuiala(int zi) {
        if (zi < 0 || zi >= 7) {
            throw new IllegalArgumentException("Indice de zi invalid.");
        }

        return cheltuieliZilnice[zi];
    }

    // Returnăm o copie a cheltuielilor zilnice.
    public double[] getCheltuieliZilnice() {
        return cheltuieliZilnice.clone();
    }

    // Calculăm totalul cheltuielilor săptămânale.
    public double totalSaptamanal() {
        double total = 0;

        for (double valoare : cheltuieliZilnice) {
            total += valoare;
        }

        return total;
    }

    // Calculăm media zilnică.
    public double medieZilnica() {
        return totalSaptamanal() / 7;
    }

    // Estimăm cheltuielile pentru 30 de zile.
    public double estimareLunara() {
        return medieZilnica() * 30;
    }

    // Calculăm costul pentru un număr ales de zile.
    public double costPentruZile(int numarZile) {
        if (numarZile < 0) {
            throw new IllegalArgumentException(
                "Numărul de zile nu poate fi negativ."
            );
        }

        return medieZilnica() * numarZile;
    }
}
