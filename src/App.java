import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Structures.City.City;

public class App {
    public static void main(String[] args) throws Exception {

        String fileName;

        try{
            fileName = args[0];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error: O nome do arquivo precisa ser fornecido.");
            return;
        }

        ArrayList<City> cities;

        try{
            cities = readFileCities(fileName);
        }catch(FileNotFoundException e){
            System.out.println("Error: Nome do arquivo inválido.");
            return;
        }

        System.out.println(cities);

    }

    public static ArrayList<City> readFileCities(String fileName) throws FileNotFoundException{

        Scanner in = new Scanner(new FileReader(fileName));

        ArrayList<City> map = new ArrayList<City>();

        ArrayList<Float> oordX = new ArrayList<Float>();
        ArrayList<Float> oordY = new ArrayList<Float>();

        String input[];
        
        input = in.nextLine().split(" ");

        for(String element : input){
            oordX.add(Float.parseFloat(element));
        }

        input = in.nextLine().split(" ");

        for(String element : input){
            oordY.add(Float.parseFloat(element));
        }

        for(int i = 0; i < oordX.size(); i++){
            map.add(new City(oordX.get(i), oordY.get(i)));
        }

        return map;
    }

    public static void writeOutput(String out){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            writer.write(out);
                
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
