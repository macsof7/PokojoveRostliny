import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlantList {
    private List<Plant> plants;
    
    public PlantList() {
        plants = new ArrayList<>();
    }
    
    public void addPlant(Plant plant) {
        plants.add(plant);
    }
    
    public Plant getPlant(int index) {
        return plants.get(index);
    }
    
    public void removePlant(int index) {
        plants.remove(index);
    }
    
    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }
    
    public List<Plant> getPlantsToWater() {
        List<Plant> toWater = new ArrayList<>();
        
        for (Plant p : plants) {
            LocalDate nextWatering = p.getWatering().plusDays(p.getFrequencyOfWatering());
            if (nextWatering.isBefore(LocalDate.now())) {
                toWater.add(p);
            }
        }
        
        return toWater;
    }
    
    public void sortByName() {
        Collections.sort(plants, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
    }
    
    public void sortByWateringDate() {
        plants.sort(Comparator.comparing(Plant::getWatering));
    }
    
    public void loadFromFile(String fileName) throws PlantException {
        plants.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 5) {
                    throw new PlantException("Špatný formát řádku: " + line);
                }
                
                String name = parts[0];
                String notes = parts[1];
                LocalDate planted = LocalDate.parse(parts[2]);
                LocalDate watering = LocalDate.parse(parts[3]);
                int freq = Integer.parseInt(parts[4]);
                
                Plant p = new Plant(name, notes, planted, watering, freq);
                plants.add(p);
            }
            
        } catch (IOException e) {
            throw new PlantException("Chyba při čtení souboru: " + e.getMessage());
        } catch (Exception e) {
            throw new PlantException("Chyba v datech souboru: " + e.getMessage());
        }
    }
    
    public void saveToFile(String fileName) throws PlantException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Plant p : plants) {
                bw.write(p.getName() + "\t" +
                         p.getNotes() + "\t" +
                         p.getPlanted() + "\t" +
                         p.getWatering() + "\t" +
                         p.getFrequencyOfWatering());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při ukládání souboru: " + e.getMessage());
        }
    }
}
