package com.epam.brest.client_rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

/**
 * Rest client console application demo.
 */
public class DemoApp {
    private DepartmentService departmentService;
    private Scanner scanner= new Scanner(System.in);

    public DemoApp(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    private void menu()
    {
        System.out.println("-----------------------------------------");
        System.out.println("-Menu selection--------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("----------------options-------------------");
        System.out.println("- 1 - Get all departments                -");
        System.out.println("- 2 - Get department by id               -");
        System.out.println("- 3 - Add department                     -");
        System.out.println("- 4 - Exit                               -");
        System.out.println("-----------------------------------------");
        int switch_value=0;
        while (switch_value!=4)
        {
            System.out.println("Enter your choice:");
            if(scanner.hasNextInt()) {
                switch_value=scanner.nextInt();
                try {
                    chech_value(switch_value);
                }catch (ServerDataAccessException e)
                {
                    System.out.println("Response Error:"+e.getMessage());
                }
            }
            else
                System.out.println("Wrong value! "+ scanner.next());
        }
    }
    private void chech_value(int item)
    {
        switch (item)
        {
            case 1: getAllDepartments();
                break;
            case 2: getdepartmentById();
                break;
            case 3: addDepartment();
                break;
            case 4:
                System.out.println("GoodBye!");
                break;
            default:System.out.println("Wrong menu item! "+ item);
            break;
        }
    }
    private void getAllDepartments()
    {
        Collection<DepartmentAVGsalary> departmentAVGsalaries=
                departmentService.getDepartmentsAVGSalary();
        System.out.println("departments: "+departmentAVGsalaries);

    }
    private void getdepartmentById()
    {
        System.out.println("Enter Department ID:");
        int id=0;
        if(scanner.hasNextInt()&& (id=scanner.nextInt() )>0) {
            Department department=departmentService.getDepartmentById(id);
            System.out.println("Department:"+department);
        }
        else
            System.out.println("Wrong value! "+ scanner.next());
    }

    private void addDepartment()
    {
        System.out.println("Enter Department Name:");
        String name = scanner.next();

        System.out.println("Enter Department description:");
        String description = scanner.next();
        Department department;
        department=departmentService.addDepartment(new Department(name,description));
        System.out.println("Department added: "+department);
    }

    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-context.xml");
        DepartmentService departmentService= context.getBean(DepartmentService.class);
        DemoApp demoApp= new DemoApp(departmentService);
        demoApp.menu();
    }

}
