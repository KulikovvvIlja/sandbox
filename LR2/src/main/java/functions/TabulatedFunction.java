package functions;

public class TabulatedFunction {
    //arr for keep points
    private FunctionPoint[] points;
    private int pointsCount;         // Текущее количество точек

    //1й конструктор
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        if(pointsCount < 2){
            throw new IllegalArgumentException("pointsCount должно быть >= 2");
        }
        //шаг между точками
        this.points = new FunctionPoint[pointsCount];
        this.pointsCount = pointsCount;

        double step = (rightX - leftX) / (pointsCount - 1);

        for (int i = 0; i < pointsCount; i++) {
            double x = leftX + i * step;
            points[i]= new FunctionPoint(x,0);
        }
    }

    //2й конструктор
    public TabulatedFunction(double leftX, double rightX, double[] values){
        if(values.length < 2){
            throw new IllegalArgumentException("pointsCount должно быть >= 2");
        }
        //шаг
        double step = (rightX - leftX)/(values.length -1);

        this.points = new FunctionPoint[values.length];
        this.pointsCount = values.length;

        for(int i = 0; i < values.length; i++){
            double x  = leftX+i*step;
            points[i]= new FunctionPoint(x,values[i]);
        }
    }

    //------//
    //Task 4

    //метод возвращающий значение левой и правой границы
    public double getLeftDomainBorder(){
        return points[0].getX();
    }
    public double getRightDomainBorder(){
        return points[pointsCount-1].getX();
    }

    //метод возврата значений ф. в т. х
    public double getFunctionValue(double x) {
        for (FunctionPoint point : points) {
            if (point != null && Double.compare(point.getX(), x) == 0) {
                return point.getY();
            }
        }

        // Линейная интерполяция, если x не совпало с табулированными точками
        if (x < points[0].getX() || x > points[pointsCount - 1].getX()) {
            return Double.NaN;  // Если точка вне области определения
        }

        // Находим интервал для интерполяции
        for (int i = 0; i < pointsCount - 1; i++) {
            if (points[i] != null && points[i + 1] != null && x > points[i].getX() && x < points[i + 1].getX()) {
                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();

                // Линейная интерполяция: y = y1 + (y2 - y1) * (x - x1) / (x2 - x1)
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN;  // Если что-то пошло не так
    }

    //-----//
    //Task 5

    //метод возвр. кол-во точек
    public int getPointsCount(){
        return pointsCount;
    }

    // Метод для получения точки по индексу
    public FunctionPoint getPoint(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return points[index];
    }

    // Метод для замены точки по индексу
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        // Проверяем, лежит ли новая координата x внутри допустимого диапазона
        if (index > 0 && point.getX() <= points[index - 1].getX()) {
            throw new IllegalArgumentException("Значение x меньше или равно предыдущей точке");
        }
        if (index < pointsCount - 1 && point.getX() >= points[index + 1].getX()) {
            throw new IllegalArgumentException("Значение x больше или равно следующей точке");
        }

        points[index] = point;  // Заменяем точку
    }

    // Метод для получения координаты X точки по индексу
    public double getPointX(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return points[index].getX();
    }

    // Метод для изменения координаты X точки по индексу
    public void setPointX(int index, double x){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        // Проверяем, лежит ли новая координата x внутри допустимого диапазона
        if (index > 0 && x <= points[index - 1].getX()) {
            throw new IllegalArgumentException("Значение x меньше или равно предыдущей точке");
        }
        if (index < pointsCount- 1 && x>= points[index + 1].getX()) {
            throw new IllegalArgumentException("Значение x больше или равно следующей точке");
        }
        points[index].setX(x); // Устанавливаем новое значение x
    }

    // Метод для получения координаты Y точки по индексу
    public double getPointY(int index){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return points[index].getY();
    }

    // Метод для изменения координаты Y точки по индексу
    public void setPointY(int index, double y){
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        points[index].setY(y); // Устанавливаем новое значение y
    }

    //-----//
    //Task 6

    // Метод для удаления точки по индексу
    public void deletePoint(int index) {
        if (pointsCount <= 2) {
            throw new IllegalStateException("Нельзя удалить точку: количество точек меньше 2");
        }
        if (index < 0 || index >= pointsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }

        // Сдвигаем массив влево для удаления элемента
        System.arraycopy(points, index + 1, points, index, pointsCount - index - 1);
        pointsCount--;  // Уменьшаем количество точек
    }

    // Метод для добавления новой точки
    public void addPoint(FunctionPoint point) {
        // Проверяем, что новая точка не нарушает порядок по x
        for (int i = 0; i < pointsCount; i++) {
            if (point.getX() == points[i].getX()) {
                throw new IllegalArgumentException("Точка с таким значением X уже существует");
            }
        }

        // Увеличиваем массив, если он заполнен
        if (pointsCount == points.length) {
            FunctionPoint[] newPoints = new FunctionPoint[points.length * 2]; // Увеличиваем массив в 2 раза
            System.arraycopy(points, 0, newPoints, 0, points.length);
            points = newPoints;
        }

        // Вставляем новую точку в правильную позицию, сохраняя упорядоченность
        int insertIndex = 0;
        while (insertIndex < pointsCount && points[insertIndex].getX() < point.getX()) {
            insertIndex++;
        }

        // Сдвигаем правую часть массива вправо
        System.arraycopy(points, insertIndex, points, insertIndex + 1, pointsCount - insertIndex);

        // Вставляем новую точку
        points[insertIndex] = point;
        pointsCount++;  // Увеличиваем количество точек
    }

    // Метод для отображения табулированной функции
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TabulatedFunction:\n");
        for (int i = 0; i < pointsCount; i++) {
            sb.append(points[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
