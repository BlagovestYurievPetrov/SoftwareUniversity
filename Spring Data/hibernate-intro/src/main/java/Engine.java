import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

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
               case 4: exFour();
               case 5: exFive();
               case 6: exSix();
               case 7: exSeven();
               case 8: exEight();


           }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    private void exEight() throws IOException {
        System.out.println("You will receive info about the employee and his projects.");
        System.out.println("Please enter employee ID:");
        int id = Integer.parseInt(bufferedReader.readLine());
        Employee employee = entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :inputId", Employee.class).setParameter("inputId", id).getSingleResult();
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        Set<Project> projects = employee.getProjects();
        projects.forEach(x -> System.out.println(x.getName()));
    }

    private void exSeven() {
        List<Address> resultList = entityManager.createQuery("SELECT a From Address a GROUP BY a.id ORDER BY a.employees.size DESC ", Address.class).getResultList();

        for (int i = 0; i < 10; i++) {
            Address currAddress = resultList.get(i);
            System.out.printf("%s %s - %d employees%n", currAddress.getText(), currAddress.getTown().getName(), currAddress.getEmployees().size());
        }
    }

    private void exSix() throws IOException {
        System.out.println("Please enter employee last name for whom the address will be changed:");
        String eName = bufferedReader.readLine();
        Employee emp = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = :lname ", Employee.class)
                .setParameter("lname", eName).getSingleResult();

        Address address = createAddress("Vitoshka 15");
        entityManager.getTransaction().begin();
        emp.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressName) {
        Address address = new Address();
        address.setText(addressName);
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        return address;
    }

    private void exFive() {
        entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.salary ASC, e.id ASC ", Employee.class).getResultStream()
        .filter((x) -> x.getDepartment().getName().equals("Research and Development"))
        .map((x) -> String.format("%s %s from %s - $%2f",x.getFirstName(), x.getLastName(), x.getDepartment().getName(), x.getSalary())).forEach(System.out::println);
    }

    private void exFour() {
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > 50000 ", Employee.class).getResultList();
        for (Employee employee : employees) {
            System.out.printf("%s%n",employee.getFirstName());

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
