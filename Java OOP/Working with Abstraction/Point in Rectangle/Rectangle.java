public class Rectangle {
    private Point a;
    private Point c;

    public Rectangle(Point a, Point c) {
        this.a = a;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getC() {
        return c;
    }

    public boolean Contains(Point point, Rectangle rectangle){
     if (point.getX()>=rectangle.getA().getX()&&point.getX()<=rectangle.getC().getX()&&point.getY()>=rectangle.getA().getY()
     &&point.getY()<=rectangle.getC().getY()){
         return true;
     }
     return false;
    }
}
