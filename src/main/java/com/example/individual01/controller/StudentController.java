package com.example.individual01.controller;

import com.example.individual01.model.Student;
import com.example.individual01.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 📋 Hiển thị danh sách sinh viên
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list"; // trỏ tới file templates/students/list.html
    }

    // ➕ Hiển thị form thêm sinh viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    // 💾 Xử lý thêm sinh viên
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // ✏️ Hiển thị form sửa sinh viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên có ID: " + id));
        model.addAttribute("student", student);
        return "students/edit";
    }

    // 🔁 Cập nhật sinh viên
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {
        student.setStudentId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // ❌ Xoá sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
