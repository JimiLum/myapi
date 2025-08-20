package com.example.demo;
	import org.springframework.web.bind.annotation.*;
	import java.util.*;

	@RestController
	@RequestMapping("/students")
	public class StudentController {

	    private List<Student> students = new ArrayList<>(
	        Arrays.asList(
	            new Student(1, "John", 20),
	            new Student(2, "Alice", 22)
	        )
	    );

	    // GET all students
	    @GetMapping
	    public List<Student> getAllStudents() {
	        return students;
	    }

	    // GET student by ID
	    @GetMapping("/{id}")
	    public Student getStudentById(@PathVariable int id) {
	        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
	    }

	    // POST add student
	    @PostMapping
	    public Student addStudent(@RequestBody Student student) {
	        students.add(student);
	        return student;
	    }

	    // PUT update student
	    @PutMapping("/{id}")
	    public Student updateStudent(@PathVariable int id, @RequestBody Student newStudent) {
	        for (Student s : students) {
	            if (s.getId() == id) {
	                s.setName(newStudent.getName());
	                s.setAge(newStudent.getAge());
	                return s;
	            }
	        }
	        return null;
	    }

	    // DELETE student
	    @DeleteMapping("/{id}")
	    public String deleteStudent(@PathVariable int id) {
	        students.removeIf(s -> s.getId() == id);
	        return "Student deleted successfully!";
	    }
	}
