public abstract class Shape {
    private Double perimeter;
    private Double area;


    public abstract double calculatePerimeter();
    public abstract double calculateArea();

    public Double getPerimeter() {
        return perimeter;
    }

    public Double getArea() {
        return area;
    }
}
