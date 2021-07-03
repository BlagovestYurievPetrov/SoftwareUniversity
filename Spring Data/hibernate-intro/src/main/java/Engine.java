import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable{
    private final EntityManager entityManager;
    private BufferedReader bufferedReader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.println("Enter ex number:");
        try {
           int exNum = Integer.parseInt(bufferedReader.readLine());
           switch (exNum) {
               case 2: exTwo();
               case 3: exThree();


           }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    private void exThree() {
        System.out.println("Enter name for search:");
        try {
            String[] search = bufferedReader.readLine().split("\\s+");
            String fName = search[0];
            String lName = search[1];

            Long count = entityManager.createQuery("select count(e) from Employee e where e.firstName = :fName AND e.lastName = :lName",
                    Long.class).setParameter("fName", fName).setParameter("lName", lName).getSingleResult();
            if (count != 0){
                System.out.println("YES");
                return;
            }
            System.out.println("NO");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exTwo() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("update Town t set t.name = upper(t.name) where length(t.name) <= 5 ");
        System.out.println(query.executeUpdate());
        entityManager.getTransaction().commit();
    }
}
