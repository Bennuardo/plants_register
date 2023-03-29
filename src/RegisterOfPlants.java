import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPlants {
    public List<Plant> plantList = new ArrayList<>();

    public void addPlant (Plant newPlant) { plantList.add(newPlant);}

    public void removePlant(Plant plant) { plantList.remove(plant);}

    public List<Plant> getPlants(){
        return new ArrayList<>(plantList);
    }

    public Plant getPlantsIndex(int index) {
        return plantList.get(index);
    }

    // soubor čtení
    public void readPlantsFromFile (String fileName) throws PlantException {
        String nextLine = null;
        String [] items = new String[0];
        String name = null;
        String notes = null;
        int frequencyOfWatering = 0;
        LocalDate planted = null;
        LocalDate watering = null;
        int lineNumber = 0;
        Plant newPlant = null;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()){
                lineNumber ++;
                nextLine = scanner.nextLine();
                items = nextLine.split("\t");
                name = items [0];
                notes = items [1];
                frequencyOfWatering = Integer.parseInt(items[2]);
                planted = LocalDate.parse(items[4]);
                watering = LocalDate.parse(items[3]);
                newPlant = new Plant(name, notes, frequencyOfWatering, planted, watering);
                addPlant(newPlant);
            }
        }catch (FileNotFoundException e) {
            throw new PlantException("Nepodařilo se najít soubor "+fileName+": "+e.getLocalizedMessage());
        }catch (NumberFormatException e) {
            throw new PlantException("Nesprávný formát frekvence zálivky na řádku: "+lineNumber+" Zadejte celé číslo ve dnech."+e.getLocalizedMessage() );
        }catch (DateTimeException e){
            throw new PlantException("Nesprávný formát data na řádku: "+lineNumber+e.getLocalizedMessage());
        }

        }



}

