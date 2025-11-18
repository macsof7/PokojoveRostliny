import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;
    
    // konstruktor pro všechny atributy
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo.");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nemůže být před datem zasazení.");
        }
        
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }
    
    // konstruktor s prázdnými poznámkami a dnešním datem
    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }
    
    // konstruktor jen s názvem a výchozí frekvencí 7 dní
    public Plant(String name) throws PlantException {
        this(name, 7);
    }
    
    // gettery a settery
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDate getPlanted() {
        return planted;
    }
    
    public LocalDate getWatering() {
        return watering;
    }
    
    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zálivky nesmí být před zasazením.");
        }
        this.watering = watering;
    }
    
    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }
    
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }
    
    // metoda pro výpis informací o zálivce
    public String getWateringInfo() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return name + " | Poslední zálivka: " + watering + " | Další zálivka: " + nextWatering;
    }
    
    // metoda pro zalití teď
    public void doWateringNow() {
        this.watering = LocalDate.now();
    }
}
