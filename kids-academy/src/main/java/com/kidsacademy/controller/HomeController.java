package com.kidsacademy.controller;

import com.kidsacademy.entity.*;
import com.kidsacademy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/")
    @ResponseBody
    public String home(@RequestParam(value = "q", required = false) String query) {
        List<Student> students = studentRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();
        List<Course> courses = courseRepository.findAll();
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        if (query != null && !query.isEmpty()) {
            String q = query.toLowerCase();
            students = students.stream()
                    .filter(s -> s.getFirstName().toLowerCase().contains(q) ||
                            s.getLastName().toLowerCase().contains(q) ||
                            s.getEmail().toLowerCase().contains(q))
                    .collect(Collectors.toList());
            teachers = teachers.stream()
                    .filter(t -> t.getFirstName().toLowerCase().contains(q) ||
                            t.getLastName().toLowerCase().contains(q) ||
                            t.getEmail().toLowerCase().contains(q))
                    .collect(Collectors.toList());
            classrooms = classrooms.stream()
                    .filter(c -> c.getName().toLowerCase().contains(q))
                    .collect(Collectors.toList());
            courses = courses.stream()
                    .filter(c -> c.getCourseName().toLowerCase().contains(q) ||
                            c.getDescription().toLowerCase().contains(q))
                    .collect(Collectors.toList());
            enrollments = enrollments.stream()
                    .filter(e -> (e.getStudent() != null &&
                            (e.getStudent().getFirstName().toLowerCase().contains(q) ||
                                    e.getStudent().getLastName().toLowerCase().contains(q))) ||
                            (e.getCourse() != null &&
                                    e.getCourse().getCourseName().toLowerCase().contains(q)))
                    .collect(Collectors.toList());
        }

        return String.format("""
            <div style='font-family: Arial, sans-serif; padding: 20px; background: #f0f8ff;'>
                <h1 style='color: #2E8B57;'>Kids Academy Management System</h1>
                <p><strong>Application is running successfully!</strong></p>

                <form method='get' action='/' style='margin-bottom: 15px;'>
                    <input type='text' name='q' placeholder='Search...' style='padding:3px;width:200px;'/>
                    <button type='submit' style='padding:3px 8px;'>Search</button>
                </form>

                <h2 style='margin-top:20px;'>Overview Tables</h2>

                <!-- Students -->
                <h3>Students (%d)</h3>
                <form method='post' action='/students/create' style='margin-bottom:5px;'>
                    <input type='text' name='firstName' placeholder='First Name' required style='width:100px;'/>
                    <input type='text' name='lastName' placeholder='Last Name' required style='width:100px;'/>
                    <input type='email' name='email' placeholder='Email' required style='width:150px;'/>
                    <input type='number' name='age' placeholder='Age' required style='width:50px;'/>
                    <button type='submit' style='padding:2px 5px;'>Add</button>
                </form>
                <table border='1' cellpadding='3' cellspacing='0' style='font-size:12px;'>
                    <tr><th>ID</th><th>First</th><th>Last</th><th>Email</th><th>Age</th><th>Actions</th></tr>
                    %s
                </table>

                <!-- Teachers -->
                <h3>Teachers (%d)</h3>
                <form method='post' action='/teachers/create' style='margin-bottom:5px;'>
                    <input type='text' name='firstName' placeholder='First' required style='width:100px;'/>
                    <input type='text' name='lastName' placeholder='Last' required style='width:100px;'/>
                    <input type='email' name='email' placeholder='Email' required style='width:150px;'/>
                    <input type='text' name='phoneNumber' placeholder='Phone' style='width:100px;'/>
                    <button type='submit' style='padding:2px 5px;'>Add</button>
                </form>
                <table border='1' cellpadding='3' cellspacing='0' style='font-size:12px;'>
                    <tr><th>ID</th><th>First</th><th>Last</th><th>Email</th><th>Phone</th><th>Actions</th></tr>
                    %s
                </table>

                <!-- Classrooms -->
                <h3>Classrooms (%d)</h3>
                <form method='post' action='/classrooms/create' style='margin-bottom:5px;'>
                    <input type='text' name='name' placeholder='Name' style='width:100px;' required/>
                    <input type='number' name='capacity' placeholder='Cap' style='width:50px;' required/>
                    <button type='submit' style='padding:2px 5px;'>Add</button>
                </form>
                <table border='1' cellpadding='3' cellspacing='0' style='font-size:12px;'>
                    <tr><th>ID</th><th>Name</th><th>Cap</th><th>Actions</th></tr>
                    %s
                </table>

                <!-- Courses -->
                <h3>Courses (%d)</h3>
                <form method='post' action='/courses/create' style='margin-bottom:5px;'>
                    <input type='text' name='courseName' placeholder='Name' style='width:120px;' required/>
                    <input type='text' name='description' placeholder='Desc' style='width:150px;'/>
                    <input type='number' name='teacherId' placeholder='Teacher ID' style='width:50px;'/>
                    <input type='number' name='classroomId' placeholder='Class ID' style='width:50px;'/>
                    <button type='submit' style='padding:2px 5px;'>Add</button>
                </form>
                <table border='1' cellpadding='3' cellspacing='0' style='font-size:12px;'>
                    <tr><th>ID</th><th>Name</th><th>Desc</th><th>Teacher</th><th>Class</th><th>Actions</th></tr>
                    %s
                </table>

                <!-- Enrollments -->
                <h3>Enrollments (%d)</h3>
                <form method='post' action='/enrollments/create' style='margin-bottom:5px;'>
                    <input type='number' name='studentId' placeholder='Student ID' style='width:50px;' required/>
                    <input type='number' name='courseId' placeholder='Course ID' style='width:50px;' required/>
                    <input type='text' name='status' placeholder='Status' style='width:80px;' required/>
                    <input type='date' name='startDate' required style='width:110px;'/>
                    <button type='submit' style='padding:2px 5px;'>Add</button>
                </form>
                <table border='1' cellpadding='3' cellspacing='0' style='font-size:12px;'>
                    <tr><th>ID</th><th>Student</th><th>Course</th><th>Status</th><th>Start</th><th>Actions</th></tr>
                    %s
                </table>
            </div>
            """,
                students.size(), generateStudentRows(students),
                teachers.size(), generateTeacherRows(teachers),
                classrooms.size(), generateClassroomRows(classrooms),
                courses.size(), generateCourseRows(courses),
                enrollments.size(), generateEnrollmentRows(enrollments)
        );
    }

    // --- Row generators remain the same as before, links included ---
    private String generateStudentRows(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%d</td>" +
                            "<td><a href='/students/edit/%d'>Edit</a> | <a href='/students/delete/%d'>Del</a></td></tr>",
                    s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getAge(), s.getId(), s.getId()));
        }
        return sb.toString();
    }

    private String generateTeacherRows(List<Teacher> teachers) {
        StringBuilder sb = new StringBuilder();
        for (Teacher t : teachers) {
            sb.append(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>" +
                            "<td><a href='/teachers/edit/%d'>Edit</a> | <a href='/teachers/delete/%d'>Del</a></td></tr>",
                    t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(),
                    t.getPhoneNumber() != null ? t.getPhoneNumber() : "N/A", t.getId(), t.getId()));
        }
        return sb.toString();
    }

    private String generateClassroomRows(List<Classroom> classrooms) {
        StringBuilder sb = new StringBuilder();
        for (Classroom c : classrooms) {
            sb.append(String.format("<tr><td>%d</td><td>%s</td><td>%d</td>" +
                            "<td><a href='/classrooms/edit/%d'>Edit</a> | <a href='/classrooms/delete/%d'>Del</a></td></tr>",
                    c.getId(), c.getName(), c.getCapacity(), c.getId(), c.getId()));
        }
        return sb.toString();
    }

    private String generateCourseRows(List<Course> courses) {
        StringBuilder sb = new StringBuilder();
        for (Course c : courses) {
            String teacherName = c.getTeacher() != null ? c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName() : "N/A";
            String classroomName = c.getClassroom() != null ? c.getClassroom().getName() : "N/A";
            sb.append(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>" +
                            "<td><a href='/courses/edit/%d'>Edit</a> | <a href='/courses/delete/%d'>Del</a></td></tr>",
                    c.getId(), c.getCourseName(), c.getDescription(), teacherName, classroomName, c.getId(), c.getId()));
        }
        return sb.toString();
    }

    private String generateEnrollmentRows(List<Enrollment> enrollments) {
        StringBuilder sb = new StringBuilder();
        for (Enrollment e : enrollments) {
            String studentName = e.getStudent() != null ? e.getStudent().getFirstName() + " " + e.getStudent().getLastName() : "N/A";
            String courseName = e.getCourse() != null ? e.getCourse().getCourseName() : "N/A";
            sb.append(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>" +
                            "<td><a href='/enrollments/edit/%d'>Edit</a> | <a href='/enrollments/delete/%d'>Del</a></td></tr>",
                    e.getId(), studentName, courseName, e.getStatus(), e.getStartDate(), e.getId(), e.getId()));
        }
        return sb.toString();
    }
}
