# Calculator Cheltuieli Transport Elevi

## 🇷🇴 Română

### Descriere

**Calculator Cheltuieli Transport Elevi** este o aplicație Java de tip consolă care permite înregistrarea, calcularea și compararea cheltuielilor săptămânale de transport pentru mai mulți elevi.

Aplicația calculează totalul săptămânal, media zilnică, estimarea lunară și costul pentru un număr ales de zile. De asemenea, permite salvarea și încărcarea datelor folosind fișiere CSV.

Proiectul a fost realizat pentru exersarea programării orientate pe obiecte, a colecțiilor Java, a validării datelor și a operațiilor cu fișiere.

## ✨ Funcționalități

- Înregistrarea mai multor elevi și a cheltuielilor lor zilnice.
- Validarea numelor și a valorilor introduse.
- Calcularea totalului cheltuielilor pentru 7 zile.
- Calcularea mediei zilnice.
- Estimarea cheltuielilor pentru 30 de zile.
- Calcularea costului pentru un număr personalizat de zile.
- Compararea și ordonarea elevilor după estimarea lunară.
- Identificarea cheltuielilor lunare minime și maxime și calcularea diferenței dintre ele.
- Salvarea datelor într-un fișier CSV.
- Încărcarea datelor dintr-un fișier CSV existent.
- Gestionarea erorilor de introducere și a problemelor de citire sau scriere a fișierelor.

## 🛠️ Tehnologii utilizate

- Java
- Java Collections Framework (`ArrayList`, `List`)
- Java NIO (`Files`, `Path`, `Paths`)
- Citirea și scrierea fișierelor
- Formatul CSV pentru stocarea datelor

## 📁 Structura proiectului

```text
calculator-cheltuieli-transport-elevi/
├── src/
│   ├── Main.java
│   ├── CalculatorTransport.java
│   └── GestionareFisierCSV.java
└── README.md
```

### Rolul claselor

**`Main.java`**
- Conține metoda `main()` și meniul interactiv.
- Gestionează introducerea și afișarea elevilor.
- Compară cheltuielile și afișează rezultatele.
- Coordonează salvarea și încărcarea datelor.

**`CalculatorTransport.java`**
- Păstrează numele elevului și cheltuielile pentru cele 7 zile.
- Validează datele primite prin constructor.
- Oferă metode pentru accesarea datelor și efectuarea calculelor:
  - `getNumeElev()`
  - `getCheltuiala(int zi)`
  - `getCheltuieliZilnice()`
  - `totalSaptamanal()`
  - `medieZilnica()`
  - `estimareLunara()`
  - `costPentruZile(int numarZile)`

**`GestionareFisierCSV.java`**
- Salvează elevii și cheltuielile într-un fișier CSV.
- Încarcă și validează datele din fișier.
- Gestionează numele care conțin virgule sau ghilimele.
- Semnalează erorile de citire și datele invalide.

## ▶️ Instalare și rulare

### Cerințe

- JDK instalat.
- Un terminal sau un mediu de dezvoltare Java, precum IntelliJ IDEA, Eclipse ori Visual Studio Code.

### Compilare

Din directorul principal al proiectului, rulează:

```bash
javac -d out src/Main.java src/CalculatorTransport.java src/GestionareFisierCSV.java
```

### Pornirea aplicației

```bash
java -cp out Main
```

## 📋 Utilizare

La pornire, aplicația afișează următorul meniu:

```text
==================================
 CALCULATOR CHELTUIELI TRANSPORT
==================================
1. Introdu elevi și cheltuieli
2. Afișează toți elevii
3. Compară cheltuielile
4. Salvează datele în CSV
5. Încarcă datele din CSV
0. Ieșire
==================================
```

| Opțiune | Acțiune |
|---|---|
| `1` | Introduce elevi și cheltuielile lor |
| `2` | Afișează rezumatul tuturor elevilor |
| `3` | Compară cheltuielile elevilor |
| `4` | Salvează datele în CSV |
| `5` | Încarcă datele din CSV |
| `0` | Închide aplicația |

Pentru fiecare elev, se introduc cheltuielile de luni până duminică. Valorile pot folosi punctul sau virgula ca separator zecimal.

## 💻 Exemplu de rulare

### 1. Introducerea unui elev

Utilizatorul selectează opțiunea `1` și introduce cheltuielile pentru fiecare zi:

```text
Alege opțiunea: 1
Câți elevi dorești să introduci? 1

--- Elevul 1 ---
Numele elevului: Andrei
Cheltuiala pentru Luni (lei): 5
Cheltuiala pentru Marti (lei): 5
Cheltuiala pentru Miercuri (lei): 5
Cheltuiala pentru Joi (lei): 5
Cheltuiala pentru Vineri (lei): 5
Cheltuiala pentru Sambata (lei): 4
Cheltuiala pentru Duminica (lei): 5

Elev adăugat cu succes.
```

### 2. Afișarea rezumatului

După selectarea opțiunii `2`, programul afișează:

```text
=== REZUMAT ANDREI ===
Luni      : 5,00 lei
Marti     : 5,00 lei
Miercuri  : 5,00 lei
Joi       : 5,00 lei
Vineri    : 5,00 lei
Sambata   : 4,00 lei
Duminica  : 5,00 lei
Total săptămânal: 34,00 lei
Medie zilnică: 4,86 lei
Estimare lunară: 145,71 lei
Cost pentru 20 de zile: 97,14 lei
```

Rezultatele sunt calculate astfel:

- Total săptămânal: `5 + 5 + 5 + 5 + 5 + 4 + 5 = 34 lei`
- Media zilnică: `34 / 7 ≈ 4,86 lei`
- Estimarea lunară: `(34 / 7) × 30 ≈ 145,71 lei`
- Costul pentru 20 de zile: `(34 / 7) × 20 ≈ 97,14 lei`

### 3. Compararea cheltuielilor

Dacă sunt introduși mai mulți elevi, opțiunea `3` îi ordonează crescător după cheltuiala lunară estimată.

Exemplu ilustrativ pentru doi elevi:

```text
=== COMPARAȚIA CHELTUIELILOR ===
1. Andrei | Săptămână: 34,00 lei | Lună: 145,71 lei
2. Maria | Săptămână: 42,00 lei | Lună: 180,00 lei

Costul lunar minim: Andrei - 145,71 lei
Costul lunar maxim: Maria - 180,00 lei
Diferența: 34,29 lei
```

În acest exemplu, Andrei are cheltuieli de 5 lei de luni până vineri, 4 lei sâmbăta și 5 lei duminica. Maria are cheltuieli de 6 lei în fiecare zi.

### 4. Salvarea datelor

După selectarea opțiunii `4`, datele sunt salvate în fișierul CSV:

```text
Date salvate în: .../cheltuieli_elevi.csv
```

Calea completă depinde de directorul din care rulează aplicația.

### 5. Încărcarea datelor

Dacă fișierul există și conține date valide, opțiunea `5` încarcă elevii:

```text
Au fost încărcați 1 elevi.
Datele au fost încărcate cu succes.
```

Încărcarea înlocuiește lista de elevi existentă în memoria programului cu datele citite din fișier.

### 6. Închiderea aplicației

```text
Alege opțiunea: 0
Programul s-a încheiat.
```

## 🧮 Formule de calcul

- **Total săptămânal** = suma cheltuielilor pentru toate cele 7 zile.
- **Media zilnică** = totalul săptămânal / 7.
- **Estimarea lunară** = media zilnică × 30.
- **Costul pentru un număr de zile** = media zilnică × numărul de zile.

Estimarea lunară este o aproximare pentru 30 de zile, nu un calcul bazat pe numărul exact de zile al unei luni calendaristice.

## 💾 Salvarea datelor în CSV

Datele sunt salvate în fișierul `cheltuieli_elevi.csv`, creat în directorul de lucru al aplicației.

Exemplu de conținut:

```csv
Nume,Luni,Marti,Miercuri,Joi,Vineri,Sambata,Duminica
Andrei,5.0,5.0,5.0,5.0,5.0,4.0,5.0
Maria,6.0,6.0,6.0,6.0,6.0,6.0,6.0
```

Prima linie conține antetul, iar fiecare linie următoare reprezintă un elev și cheltuielile sale zilnice.

Datele nu se încarcă automat la pornirea programului. Pentru recuperarea datelor salvate, selectează opțiunea `5`.

**Atenție:** salvarea rescrie fișierul CSV existent. Fă o copie de siguranță dacă dorești să păstrezi datele anterioare.

## 📚 Concepte demonstrate

- Programare orientată pe obiecte (OOP).
- Clase, obiecte, constructori și metode.
- Încapsularea datelor.
- Liste și colecții Java.
- Validarea datelor și gestionarea excepțiilor.
- Sortarea colecțiilor.
- Citirea și scrierea fișierelor.
- Parsarea și generarea datelor CSV.

---

# Student Transport Expense Calculator

## 🇬🇧 English

### Description

**Student Transport Expense Calculator** is a Java console application that allows users to record, calculate, and compare weekly transportation expenses for multiple students.

The application calculates weekly totals, daily averages, estimated monthly expenses, and costs for a specified number of days. It also supports saving and loading data using CSV files.

The project was developed to practice object-oriented programming, Java collections, input validation, and file handling.

## ✨ Features

- Register multiple students and their daily transportation expenses.
- Validate student names and expense values.
- Calculate total expenses across 7 days.
- Calculate the daily average.
- Estimate expenses over a 30-day period.
- Calculate costs for a specified number of days.
- Compare and sort students by estimated monthly expenses.
- Identify minimum and maximum estimated monthly costs and calculate the difference.
- Save student data to a CSV file.
- Load data from an existing CSV file.
- Handle invalid input and file-related errors.

## 🛠️ Technologies

- Java
- Java Collections Framework (`ArrayList`, `List`)
- Java NIO (`Files`, `Path`, `Paths`)
- File input and output
- CSV data storage

## 📁 Project structure

```text
calculator-cheltuieli-transport-elevi/
├── src/
│   ├── Main.java
│   ├── CalculatorTransport.java
│   └── GestionareFisierCSV.java
└── README.md
```

### Class responsibilities

**`Main.java`**
- Contains the `main()` method and interactive menu.
- Handles student input and display.
- Compares expenses and displays results.
- Coordinates CSV saving and loading.

**`CalculatorTransport.java`**
- Stores the student's name and expenses for all 7 days.
- Validates data in its constructor.
- Provides data access and calculation methods:
  - `getNumeElev()`
  - `getCheltuiala(int zi)`
  - `getCheltuieliZilnice()`
  - `totalSaptamanal()`
  - `medieZilnica()`
  - `estimareLunara()`
  - `costPentruZile(int numarZile)`

**`GestionareFisierCSV.java`**
- Saves students and their expenses to a CSV file.
- Loads and validates data from the file.
- Handles names containing commas or quotation marks.
- Reports file-reading errors and invalid data.

## ▶️ Installation and execution

### Requirements

- An installed JDK.
- A terminal or Java development environment such as IntelliJ IDEA, Eclipse, or Visual Studio Code.

### Compile

From the project root directory, run:

```bash
javac -d out src/Main.java src/CalculatorTransport.java src/GestionareFisierCSV.java
```

### Run

```bash
java -cp out Main
```

## 📋 Usage

When launched, the application displays the following menu:

```text
==================================
 CALCULATOR CHELTUIELI TRANSPORT
==================================
1. Introdu elevi și cheltuieli
2. Afișează toți elevii
3. Compară cheltuielile
4. Salvează datele în CSV
5. Încarcă datele din CSV
0. Ieșire
==================================
```

| Option | Action |
|---|---|
| `1` | Enter students and their expenses |
| `2` | Display all student summaries |
| `3` | Compare student expenses |
| `4` | Save data to CSV |
| `5` | Load data from CSV |
| `0` | Exit the application |

For each student, enter expenses from Monday through Sunday. Decimal values can use either a period or a comma as the decimal separator.

## 💻 Example run

### 1. Entering a student

Select option `1` and enter the expenses for each day:

```text
Alege opțiunea: 1
Câți elevi dorești să introduci? 1

--- Elevul 1 ---
Numele elevului: Andrei
Cheltuiala pentru Luni (lei): 5
Cheltuiala pentru Marti (lei): 5
Cheltuiala pentru Miercuri (lei): 5
Cheltuiala pentru Joi (lei): 5
Cheltuiala pentru Vineri (lei): 5
Cheltuiala pentru Sambata (lei): 4
Cheltuiala pentru Duminica (lei): 5

Elev adăugat cu succes.
```

### 2. Displaying the summary

After selecting option `2`, the application displays:

```text
=== REZUMAT ANDREI ===
Luni      : 5,00 lei
Marti     : 5,00 lei
Miercuri  : 5,00 lei
Joi       : 5,00 lei
Vineri    : 5,00 lei
Sambata   : 4,00 lei
Duminica  : 5,00 lei
Total săptămânal: 34,00 lei
Medie zilnică: 4,86 lei
Estimare lunară: 145,71 lei
Cost pentru 20 de zile: 97,14 lei
```

The calculations are:

- Weekly total: `5 + 5 + 5 + 5 + 5 + 4 + 5 = 34 lei`
- Daily average: `34 / 7 ≈ 4.86 lei`
- Monthly estimate: `(34 / 7) × 30 ≈ 145.71 lei`
- Cost for 20 days: `(34 / 7) × 20 ≈ 97.14 lei`

### 3. Comparing expenses

When multiple students have been entered, option `3` sorts them in ascending order by estimated monthly expenses.

Illustrative output for two students:

```text
=== COMPARAȚIA CHELTUIELILOR ===
1. Andrei | Săptămână: 34,00 lei | Lună: 145,71 lei
2. Maria | Săptămână: 42,00 lei | Lună: 180,00 lei

Costul lunar minim: Andrei - 145,71 lei
Costul lunar maxim: Maria - 180,00 lei
Diferența: 34,29 lei
```

In this example, Andrei spends 5 lei from Monday through Friday, 4 lei on Saturday, and 5 lei on Sunday. Maria spends 6 lei every day.

### 4. Saving data

After selecting option `4`, the application saves the data to a CSV file:

```text
Date salvate în: .../cheltuieli_elevi.csv
```

The full path depends on the application's working directory.

### 5. Loading data

If the file exists and contains valid data, option `5` loads the student records:

```text
Au fost încărcați 1 elevi.
Datele au fost încărcate cu succes.
```

Loading replaces the current in-memory student list with the records read from the file.

### 6. Exiting the application

```text
Alege opțiunea: 0
Programul s-a încheiat.
```

## 🧮 Calculation formulas

- **Weekly total** = sum of expenses across all 7 days.
- **Daily average** = weekly total / 7.
- **Monthly estimate** = daily average × 30.
- **Cost for a specified number of days** = daily average × number of days.

The monthly estimate is an approximation for 30 days, not a calculation based on the exact number of days in a calendar month.

## 💾 CSV data storage

Data is saved to `cheltuieli_elevi.csv`, created in the application's working directory.

Example:

```csv
Nume,Luni,Marti,Miercuri,Joi,Vineri,Sambata,Duminica
Andrei,5.0,5.0,5.0,5.0,5.0,4.0,5.0
Maria,6.0,6.0,6.0,6.0,6.0,6.0,6.0
```

The first line contains the column headers. Each subsequent line represents a student and their daily expenses.

Data is not loaded automatically when the application starts. Select option `5` to load previously saved records.

**Warning:** saving overwrites the existing CSV file. Make a backup if you need to preserve previous data.

## 📚 Concepts demonstrated

- Object-oriented programming (OOP).
- Classes, objects, constructors, and methods.
- Data encapsulation.
- Java lists and collections.
- Input validation and exception handling.
- Collection sorting.
- File input and output.
- CSV parsing and generation.

---

## 👤 Autor / Author

**IonutD**
