/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myuniversity.newpackage2.domain;

/**
 *
 * @author Admin
 */
public class Subject {
    private String subjectCode;
    private String subjectDescription;
    
    public Subject(){
        
    }
    public Subject(String subjectCode, String subjectDescription){
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }
    
    
}
