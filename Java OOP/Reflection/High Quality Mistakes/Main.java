import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static class FieldComparator implements Comparator<Field>{

        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reflection = Reflection.class;
        Method[] declaredMethods = reflection.getDeclaredMethods();
        Field[] declaredFields = reflection.getDeclaredFields();
        Set<Method> getters = new TreeSet<>(new MethodComparator());
        Set<Method> setters = new TreeSet<>(new MethodComparator());
        Set<Field> fields = new TreeSet<>(new FieldComparator());
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith("set")){
                setters.add(declaredMethod);
            } else if(declaredMethod.getName().startsWith("get")){
                getters.add(declaredMethod);
            }
        }
        for (Field declaredField : declaredFields) {
            fields.add(declaredField);
        }


        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPrivate(modifiers)){
                System.out.println(field.getName()+" must be private!");

            }
        }

        for (Method getter : getters) {
            int modifiers = getter.getModifiers();
            if (!Modifier.isPublic(modifiers)){
                System.out.println(getter.getName()+" have to be public!");
            }
        }
        for (Method setter : setters) {
            int modifiers = setter.getModifiers();
            if (!Modifier.isPrivate(modifiers)){
                System.out.println(setter.getName()+" have to be private!");
            }
        }

    }
}
