/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Student;
import services.StudentService;
import view.StudentView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import dto.DTO;

/**
 *
 * @author ADMIN
 */
public class StudentController {
    // declear
    private StudentView studentView = new StudentView();
    private DTO input = new DTO();
    private List<Student> listStudent = new ArrayList<>();
    private StudentService studentService = new StudentService();

    // data test
    public void mock() {
        listStudent.add(new Student(1, "Nam", 1, "java"));
        listStudent.add(new Student(1, "nam", 1, "c++"));
        listStudent.add(new Student(1, "Nam", 2, "java"));
        listStudent.add(new Student(2, "tien", 2, "c#"));
        listStudent.add(new Student(2, "tien", 2, "java"));
        listStudent.add(new Student(3, "hoang", 3, "fe"));
        listStudent.add(new Student(4, "minh", 3, "web"));
    }

    // display menu
    public void displayMenu() {
        studentView.displayMenu();
    }

    // controller get data
    public void setInputData(DTO dto) {
        this.input = dto;
    }

    // get size of ListStudent
    public int sizeOfList() {
        // get size of list
        return listStudent.size();
    }

    // check list contains student have this id?
    public boolean isExist(int id) {
        // loop in list to find
        for (Student student : listStudent) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // get name of student by id
    public String getNameStudentById(int id) {
        // create default student
        Student studentByID = null;
        // loop in list to find
        for (Student student : listStudent) {
            if (student.getId() == id) {
                studentByID = student;
            }
        }
        String nameStudenById = studentByID.getName();
        // return name of student
        return nameStudenById;
    }

    // check list contain exactly 1 student have the same id, semester, course in
    // list
    public boolean isDupicate() {
        // get data in input
        int id = input.getId();
        int semester = input.getSemester();
        String course = input.getCourse();

        // loop for check
        for (Student student : listStudent) {
            if (student.getId() == id && student.getSemester() == semester
                    && student.getCourse().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    // add new 1 student
    public void addStudent() {
        // get data in input
        int id = input.getId();
        String name = input.getName();
        int semester = input.getSemester();
        String course = input.getCourse();

        listStudent.add(new Student(id, name, semester, course));
    }

    // find list student by name
    public List<Student> listStudentByName() {
        // create list to store list student by name
        List<Student> listStudentByName = new ArrayList<>();
        // get intput name
        String name = input.getName();
        // loop in listStudent to get valid student contains this name
        for (Student student : listStudent) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                listStudentByName.add(student);
            }
        }
        return listStudentByName;
    }

    // sort List student by name
    public void sortListStudent() {
        // create list to get listStudentByName
        List<Student> list = listStudentByName();
        // sort
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        // set header
        String header = String.format("%-7s%-10s%-10s%-10s\n", "ID", "Name", "Semester", "Course");
        studentView.setHeader(header);
        // create sb to store data in list
        StringBuilder sb = new StringBuilder();
        // loop list to get data
        for (Student student : list) {
            sb.append(student);
        }
        studentView.setBody(sb.toString());
        // display
        studentView.display();
    }

    // get list student by id
    public List<Student> listStudentById() {
        // get input id
        int id = input.getId();
        // crete list to store list student by id
        List<Student> listStudentById = new ArrayList<>();
        // loop in listStudent to get valid student have this id
        for (Student student : listStudent) {
            if (student.getId() == id) {
                listStudentById.add(student);
            }
        }
        return listStudentById;
    }

    // display list student by id
    public void displayListStudentById() {
        // create list to store list student by id
        List<Student> list = listStudentById();
        // header
        String header = String.format("%-7s%-10s%-10s%-10s\n", "STT", "Name", "Semester", "Course");
        studentView.setHeader(header);
        // create string to store data
        StringBuilder sb = new StringBuilder();
        // loop in list to get data
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            String data = String.format("%-7s%-10s%-10s%-10s\n", (i + 1), student.getName(), student.getSemester(),
                    student.getCourse());
            sb.append(data);
        }
        studentView.setBody(sb.toString());
        studentView.display();
    }

    // get size of list student by id
    public int sizeOfListStudentById() {
        return listStudentById().size();
    }

    // delete 1 record
    public void delete(int choiceRecordDelete) {
        /*
         * find infor of this record
         * get list student by id
         */
        List<Student> list = listStudentById();
        // init position = 1
        int position = 1;
        // init deleteStudent
        Student deleteStudent = null;
        // loop in list to find position = choiceRecordDelete
        for (Student student : list) {
            if (position == choiceRecordDelete) {
                deleteStudent = student;
            }
            position++;
        }
        // delete in database
        for (Student student : listStudent) {
            int id = deleteStudent.getId();
            int semester = deleteStudent.getSemester();
            String course = deleteStudent.getCourse();
            if (student.getId() == id && student.getSemester() == semester
                    && student.getCourse().equalsIgnoreCase(course)) {
                listStudent.remove(student);
                break;
            }
        }
    }

    // update student
    public void update(int choiceRecordUpdate) {

        /*
         * find infor of this record
         * get list student by id
         */
        List<Student> list = listStudentById();
        // init position = 1
        int position = 1;
        // init deleteStudent
        Student updateStudent = null;
        // loop in list to find position = choiceRecordDelete
        for (Student student : list) {
            if (position == choiceRecordUpdate) {
                updateStudent = student;
            }
            position++;
        }

        // find this updateStudent in database
        Student studentInData = findInListStudent(input.getId(), updateStudent.getName(), updateStudent.getSemester(),
                updateStudent.getCourse());

        // if update name difference name in data base => change all name of this id
        if (studentInData.getName().equalsIgnoreCase(input.getName()) == false) {
            changeNameStudentInDataBase(input.getId(), input.getName());
        }

        // update semester and course
        studentInData.setSemester(input.getSemester());
        studentInData.setCourse(input.getCourse());

        // check duplicate student => not change
        if (checkDuplicate(input.getId(), input.getName(), input.getSemester(), input.getCourse())) {
            System.out.println("Not change");
            return;
        }

    }

    // change name of student
    private void changeNameStudentInDataBase(int id, String newName) {
        for (Student student : listStudent) {
            if (student.getId() == id) {
                student.setName(newName);
            }
        }
    }

    // check 1 student input that the same 1 other student in the list student
    public boolean checkDuplicate(int id, String name, int semester, String course) {
        for (Student student : listStudent) {
            if (student.getId() == id && student.getName().equalsIgnoreCase(name)
                    && student.getSemester() == semester && student.getCourse().equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    // find student in list student
    public Student findInListStudent(int id, String name, int semester, String course) {
        // loop in list student to find
        for (Student student : listStudent) {
            if (student.getId() == id && student.getName().equalsIgnoreCase(name)
                    && student.getSemester() == semester && student.getCourse().equalsIgnoreCase(course)) {
                return student;
            }
        }
        return null;
    }

    //report of list student
    public void report() {
        //tranfer list student into service
        studentService.setListStudent(listStudent);
        //report
        Map<String, Integer> map = studentService.courseReport();
        //header
        String header = String.format("%-5s%-7s%-10s%-10s\n", "ID", "Name", "Course", "Time of course");
        studentView.setHeader(header);
        //init sb to store data
        StringBuilder sb = new StringBuilder();
        //display result course
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] student = entry.getKey().split("-");
            String id = student[0];
            String name = student[1];
            String course = student[2];
            int timeOfCourse = entry.getValue();

            String report = String.format("%-5s%-7s%-10s%-10s\n", id, name, course, timeOfCourse);
            sb.append(report);
        }
        //set body
        studentView.setBody(sb.toString());
        studentView.display();
    }

}
