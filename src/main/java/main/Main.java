/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.StudentController;
import dto.DTO;
import java.util.Scanner;
import utility.Validator;
import constant.Message;

/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {
        // declear
        Scanner sc = new Scanner(System.in);
        StudentController studentController = new StudentController();
        DTO dto = new DTO();
        // add data test
        studentController.mock();

        // loop until quit program
        while (true) {
            try {

                // display menu
                studentController.displayMenu();
                // input option
                int choice = Integer.parseInt(sc.nextLine());
                // check validate choice
                if (!Validator.checkInputIntegerLimit(choice, 1, 5)) {
                    continue;
                }
                // handle
                switch (choice) {
                    // create new course
                    case 1:
                        // check size of list
                        // if list has 3 student
                        if (studentController.sizeOfList() >= 3) {
                            // => ask want to add more student in list
                            System.out.println(Message.MSG_ADD_MORE);
                            // input choice
                            String choiceYesNo = sc.nextLine();
                            // if choice "n" (no)
                            if (Validator.checkYN(choiceYesNo) == false) {
                                continue;
                            }
                            // else choice "y" (yes) => add more student
                        }
                        // input data
                        System.out.print(Message.MSG_ID);
                        // input id
                        int id = Integer.parseInt(sc.nextLine());
                        // check limit valid id
                        if (!Validator.checkInputIntegerLimit(id, 1, 100)) {
                            continue;
                        }
                        // if id exist in list
                        if (studentController.isExist(id)) {
                            // => display name of student have this id
                            String name = studentController.getNameStudentById(id);
                            System.out.format("Name: %s\n", name);
                            dto.setName(name);
                        } else {
                            System.out.print(Message.MSG_NAME);
                            // input name
                            String name = sc.nextLine();
                            dto.setName(name);
                        }

                        System.out.print(Message.MSG_SEMESTER);
                        // input semester
                        int semester = Integer.parseInt(sc.nextLine());
                        // check valid input semester
                        if (!Validator.checkInputIntegerLimit(semester, 1, 9)) {
                            continue;
                        }
                        System.out.print(Message.MSG_COURSE);
                        // input course
                        String course = sc.nextLine();

                        // dto receive data
                        dto.setId(id);
                        dto.setSemester(semester);
                        dto.setCourse(course);
                        // controller receive data
                        studentController.setInputData(dto);
                        // check duplicate in list
                        if (studentController.isDupicate()) {
                            System.out.println(Message.MSG_DUPLICATE_STUDENT);
                            continue;
                        }
                        // add student in list
                        studentController.addStudent();
                        break;
                    // find and sort
                    case 2:
                        // input a part or full name of student
                        System.out.print(Message.MSG_NAME);
                        // input data
                        String name = sc.nextLine();
                        // dto receive data
                        dto.setName(name);
                        // controller receive data
                        studentController.setInputData(dto);
                        // sort list student
                        studentController.sortListStudent();
                        break;
                    // update or delete
                    case 3:
                        // input id
                        System.out.print(Message.MSG_ID);
                        int Id = Integer.parseInt(sc.nextLine());
                        // dto receive Id
                        dto.setId(Id);
                        // controller receive id
                        studentController.setInputData(dto);
                        // display list student have this id
                        studentController.displayListStudentById();
                        int sizeOflist = studentController.sizeOfListStudentById();
                        // display choice update or delete
                        System.out.println(Message.MSG_UPDATE_OR_DELETE);
                        String choiceUpdateOrDelete = Validator.getUD(sc.nextLine());
                        //if choice not valid => break
                        if(choiceUpdateOrDelete == null){
                            System.out.println(Message.MSG_UD);
                        }
                        // handle
                        switch (choiceUpdateOrDelete) {

                            case "u":
                                System.out.print(Message.MSG_CHOICE_RECORD);
                                // input choice
                                int choiceRecordUpdate = Integer.parseInt(sc.nextLine());
                                // handle if choice valid
                                if (Validator.checkInputIntegerLimit(choiceRecordUpdate, 1, sizeOflist)) {
                                    //input update name
                                    System.out.print(Message.MSG_NAME);
                                    String inputUpdataName = sc.nextLine();
                                    //input update semester
                                    System.out.print(Message.MSG_SEMESTER);
                                    int inputUpdateSemester = Integer.parseInt(sc.nextLine());
                                    if(Validator.checkInputIntegerLimit(inputUpdateSemester, 1, 9) == false){
                                        break;
                                    }
                                    //input update course
                                    System.out.print(Message.MSG_COURSE);
                                    String inputUpdateCourse = sc.nextLine();
                                    //dto receive data
                                    dto.setName(inputUpdataName);
                                    dto.setSemester(inputUpdateSemester);
                                    dto.setCourse(inputUpdateCourse);
                                    //dto receive data
                                    studentController.setInputData(dto);
                                    //update student
                                    studentController.update(choiceRecordUpdate);
                                }
                                break;
                            case "d":
                                System.out.print(Message.MSG_CHOICE_RECORD);
                                // input choice
                                int choiceRecordDelete = Integer.parseInt(sc.nextLine());
                                // handle if choice valid
                                if (Validator.checkInputIntegerLimit(choiceRecordDelete, 1, sizeOflist)) {
                                    // delete this record
                                    studentController.delete(choiceRecordDelete);
                                }
                                break;
                            default:

                        }
                        break;
                    // report
                    case 4:
                        studentController.report();
                        break;
                    // exit
                    case 5:
                        sc.close();
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be integer number");
            }
        }
    }
}
