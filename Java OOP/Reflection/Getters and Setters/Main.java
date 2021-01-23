import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reflection = Reflection.class;
        Method[] declaredMethods = reflection.getDeclaredMethods();
        Set<Method> getters = new TreeSet<>(new MethodComparator());
        Set<Method> setters = new TreeSet<>(new MethodComparator());
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith("set")){
                setters.add(declaredMethod);
            } else if(declaredMethod.getName().startsWith("get")){
                getters.add(declaredMethod);
            }
        }
        for (Method getter : getters) {
            System.out.println(getter.getName()+" will return class "+getter.getReturnType().getName().replace("class",""));
        }
        for (Method setter : setters) {
            System.out.println(setter.getName()+" and will set field of class "+setter.getParameterTypes()[0].getName().replace("class",""));
        }

    }
}
