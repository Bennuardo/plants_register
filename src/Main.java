import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static final String FILENAME = "kvetiny.txt";
    public static void main(String[] args) throws PlantException {

        RegisterOfPlants register = new RegisterOfPlants();
        try {
            register.readPlantsFromFile(FILENAME);
        } catch (PlantException e){
            System.err.println("Chyba při čtení souboru: "+e.getLocalizedMessage());
        }

        System.out.println("Informace o zálivce: ");
        for (Plant plant: register.plantList){
            System.out.println(plant.getWateringInfo());
        }

        register.plantList.remove(2);
        register.addPlant(new Plant("Bazalka v kuchyni","", 3, LocalDate.of(2021,9, 4), LocalDate.of(2021, 9, 4)));
        System.out.println("Výpis květin: ");
        List<Plant>plants = register.getPlants();
        System.out.println(plants);


        System.out.println("Řazení rostlin:");
        // plants.sort(Comparator.reverseOrder());
        // plants.sort(Plant::compareTo);
        Collections.sort(plants);
        plants.forEach(c -> System.out.println(c.getName()));
        plants.sort(new PlantComparator());
        plants.forEach(c -> System.out.println(c.getWatering()+" "+c.getName()));

        System.out.println("Analýza:");
        Set<Plant> plantSet = new HashSet<>(plants);
        System.out.println("* Kdy byly vysazeny nějaké květiny:");
        plantSet.forEach(plant -> System.out.println(plant.getPlanted()+" "+plant.getName()));

        System.out.println("Byla nějaká rostlina vysazena v posledním měsíci?");
        for (Plant plant: register.plantList){
            System.out.println(plant.datePlantedInThisMonth());
        }


    }

}