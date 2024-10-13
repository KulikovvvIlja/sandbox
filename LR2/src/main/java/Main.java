import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main{
    //Task 7//
    public static void main(String[] args){
        // Задаем значения для функции f(x) = x^2 в диапазоне от 0 до 4
        double[] values = {0, 1, 4, 9, 16};  // Значения y для точек x = {0, 1, 2, 3, 4}
        TabulatedFunction func = new TabulatedFunction(0, 4, values);

        // Выводим начальное состояние функции
        System.out.println("Initial tabulated function (f(x) = x^2):");
        System.out.println(func);

        // Проверим значения функции в ряде точек включая точки вне области определения
        double[] testPoints = {-1, 0, 0.5, 1.5, 2, 3.5, 4, 5};

        System.out.println("\nFunction values at test points:");
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f\n", x, func.getFunctionValue(x));
        }

        // Добавим новую точку и проверим как изменится функция
        System.out.println("\nAdding new point (2.5, 6.25)...");
        func.addPoint(new FunctionPoint(2.5, 6.25));
        System.out.println("Tabulated function after adding the point:");
        System.out.println(func);

        // Удалим точку с индексом 1 (точку (1, 1)) и снова проверим
        System.out.println("\nDeleting point at index 1 (1, 1)...");
        func.deletePoint(1);
        System.out.println("Tabulated function after deleting the point:");
        System.out.println(func);

        // Проверим снова значения функции в ряде точек после изменений
        System.out.println("\nFunction values at test points after modifications:");
        for (double x : testPoints) {
            System.out.printf("f(%.1f) = %.2f\n", x, func.getFunctionValue(x));
        }
    }
}

