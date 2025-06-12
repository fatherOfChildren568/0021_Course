/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Student;

/**
 *
 * @author ADMIN
 */
public class StudentService {
    private List<Student> listStudent = new ArrayList<>();

    // Setter
    public List<Student> getListStudent() {
        return listStudent;
    }

    // Getter
    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    // report
    public Map<String, Integer> courseReport() {
        // create map to store list report of student
        Map<String, Integer> report = new LinkedHashMap<>();
        // loop in list student to get data
        for (Student course : listStudent) {
            String key = course.getId() + "-" + course.getName() + "-" + course.getCourse();
            report.put(key, report.getOrDefault(key, 0) + 1);
        }

        return report;
    }

}
