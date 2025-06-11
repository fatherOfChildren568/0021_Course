/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ADMIN
 */
public class StudentView {

    private String header;
    private String body;

    // menu
    public void displayMenu() {
        System.out.println("--------------------------------");
        System.out.println("WELCOME TO STUDENT MANAGEMENT\n"
                + "1.Create\n"
                + "2.Find and Sort\n"
                + "3.Update/Delete\n"
                + "4.Report\n"
                + "5.Exit");
        System.out.println("--------------------------------");
        System.out.print("Choice: ");
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // display info of student
    public void display() {
        System.out.print(header);
        System.out.print(body);
    }

}
