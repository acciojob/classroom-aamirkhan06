package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student)
    {
        // your code goes here
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            List<String> students=teacherStudentMapping.getOrDefault(teacher,new ArrayList<>());
            students.add(student);
            teacherStudentMapping.put(teacher,students);
        }
    }

    public Student findStudent(String student)
    {
        // your code goes here
        Student objStudent=new Student();
        return studentMap.getOrDefault(student,objStudent);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        Teacher objTeacher=new Teacher();
        return teacherMap.getOrDefault(teacher,objTeacher);
    }

    public List<String> findStudentsFromTeacher(String teacher)
    {
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> findAllStudents(){
        // your code goes here
        List<String> list=new ArrayList<>();
        for(String student:studentMap.keySet())
        {
            list.add(student);
        }
        return list;
    }

    public void deleteTeacher(String teacher)
    {
        // your code goes here
        if(!teacherStudentMapping.isEmpty() && teacherStudentMapping.containsKey(teacher))
        {
            List<String> students=teacherStudentMapping.get(teacher);
            for(String student:students)
            {
                studentMap.remove(student);
            }
            teacherStudentMapping.remove(teacher);
        }
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers(){
        // your code goes here
        if(!teacherMap.isEmpty())
        {
            List<String> teachers=new ArrayList<>();
            for(String teacher:teacherMap.keySet())
            {
                teachers.add(teacher);
            }
            for(String teacher:teachers)
            {
                deleteTeacher(teacher);
            }
        }
    }
}