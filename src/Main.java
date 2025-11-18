import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PlantList list = new PlantList();
        
        try {
            // načtení rostlin ze souboru
            list.loadFromFile("resources/kvetiny.txt");
            System.out.println("Načteno ze souboru resources/kvetiny.txt\n");
            
            // výpis informací o zálivce
            System.out.println("--- Informace o zálivce ---");
            for (Plant p : list.getAllPlants()) {
                System.out.println(p.getWateringInfo());
            }
            
            // přidání nové rostliny
            Plant nova = new Plant("Fíkus domací", "na parapetu", LocalDate.now(), LocalDate.now(), 10);
            list.addPlant(nova);
            
            // přidání 10 tulipánů
            for (int i = 1; i <= 10; i++) {
                Plant t = new Plant("Tulipán na prodej " + i, "", LocalDate.now(), LocalDate.now(), 14);
                list.addPlant(t);
            }
            
            // odstranění rostliny na 3. pozici (index 2)
            list.removePlant(2);
            
            // uložení do nového souboru
            list.saveToFile("kvetiny-nove.txt");
            System.out.println("\nSeznam uložen do souboru kvetiny-nove.txt");
            
            // načtení z uloženého souboru pro ověření
            PlantList list2 = new PlantList();
            list2.loadFromFile("kvetiny-nove.txt");
            System.out.println("\n--- Ověření načtení z nového souboru ---");
            for (Plant p : list2.getAllPlants()) {
                System.out.println(p.getWateringInfo());
            }
            
            // vyzkoušení řazení
            System.out.println("\n--- Seřazeno podle názvu ---");
            list2.sortByName();
            for (Plant p : list2.getAllPlants()) {
                System.out.println(p.getName());
            }
            
            System.out.println("\n--- Seřazeno podle data poslední zálivky ---");
            list2.sortByWateringDate();
            for (Plant p : list2.getAllPlants()) {
                System.out.println(p.getName() + " | " + p.getWatering());
            }
            
            System.out.println("\nProgram dokončen úspěšně.");
            
        } catch (PlantException e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }
}


