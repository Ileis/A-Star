package Structures.City;

public class City {
    private float oordX;
    private float oordY;
    private int id;
    private static int qtdInstance;

    public City(float x, float y){
        this.oordX = x;
        this.oordY = y;
        this.id = qtdInstance++;
    }

    public float getOordX(){
        return this.oordX;
    }

    public float getOordY(){
        return this.oordY;
    }

    public char getName(){
        return (char)(id + 'A');
    }

    public static float distance(City c1, City c2){
        // sqrt((x1 - x2)^2 + (y1 - y2)^2)
        return Double.valueOf(Math.sqrt(Math.pow(c1.getOordX() - c2.getOordX(), 2) + Math.pow(c1.getOordY() - c2.getOordY(), 2))).floatValue();
    }

    @Override
    public String toString() {
        return String.valueOf(this.getName());
    }
}
