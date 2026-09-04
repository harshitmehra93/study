package study.ocp.streams.collectors.salaryreport;

/*
17) “Report generator”
Given:

record Emp(String dept, String name, int salary) {}

Write:

Map<String, Double> avgSalaryByDept(List<Emp> emps)

Ignore employees with salary ≤ 0. Department key should be lowercase.

Constraints: filter, groupingBy, averagingInt.

*/
import java.util.*;
import java.util.stream.*;

public class EmployeeSalaryReport {
    public static void main(String... args) {
        List<Emp> employees = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            String dept = randomStringGenerator(4);
            for (int j = 0; j < 10; j++) {
                double salary = Math.random() * 100000;
                String name = randomStringGenerator(8);
                employees.add(new Emp(dept, name, salary));
            }
        }
        // group by dept, only include names
        System.out.println(
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Emp::dept,
                                        Collectors.mapping(Emp::name, Collectors.toSet()))));

        // group by dept, sum of salary
        System.out.println(
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Emp::dept, Collectors.summingDouble(Emp::salary))));

        System.out.println();
        // group by salary range
        System.out.println(
                employees.stream().collect(Collectors.partitioningBy(e -> e.salary() > 50000)));

        // System.out.println(avgSalaryByDept(employees));
        // mapOfAvgSalaryByDept

    }

    static Map<String, Double> avgSalaryByDept(List<Emp> emps) {
        return emps.stream()
                .collect(Collectors.groupingBy(Emp::dept, Collectors.averagingDouble(Emp::salary)));
    }

    static String randomStringGenerator(int length) {
        StringBuilder sb = new StringBuilder();
        String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * 10000) % key.length();
            randomIndex = randomIndex < 0 ? -randomIndex : randomIndex;
            sb.append(key.charAt(randomIndex));
        }
        return sb.toString();
    }
}

record Emp(String dept, String name, double salary) {}
