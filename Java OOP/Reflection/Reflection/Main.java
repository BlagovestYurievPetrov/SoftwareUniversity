import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reflection = Reflection.class;
        System.out.println(reflection);
        Class superclass = reflection.getSuperclass();
        System.out.println(superclass);
        Class[] interfaces = reflection.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }
        Object o = reflection.getDeclaredConstructor().newInstance();
        System.out.println(o);

    }
}
