package functions;

public class FunctionPoint {
    //Приват. поля для хранения координат
    private double x;
    private double y;

    //Конструктор с параметрами для задания координат
    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Конструктор копирования
    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    //Конструктор (0;0)
    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);  // вывод в формате (x, y) с двумя знаками после запятой
    }

}
