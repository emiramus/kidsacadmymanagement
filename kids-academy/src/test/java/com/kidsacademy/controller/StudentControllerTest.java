package com.kidsacademy.controller;

import com.kidsacademy.dto.StudentDTO;
import com.kidsacademy.entity.Student;
import com.kidsacademy.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    public StudentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Valbona");
        dto.setLastName("Krasniqi");
        dto.setEmail("valbona.krasniqi@hotmail.com");
        dto.setBirthDate(LocalDate.of(2010, 1, 1));
        dto.setAge(12);

        Student student = new Student();
        student.setId(1);
        student.setFirstName("@SpringBootTest\n" +
                "@AutoConfigureMockMvc\n" +
                "class TeacherControllerIntegrationTest {\n" +
                "\n" +
                "    @Autowired\n" +
                "    private MockMvc mockMvc;\n" +
                "\n" +
                "    @Test\n" +
                "    void testCreateTeacherIntegration() throws Exception {\n" +
                "        String teacherJson = \"\"\"\n" +
                "            {\n" +
                "                \"firstName\": \"Valbona\",\n" +
                "                \"lastName\": \"Krasniqi\",\n" +
                "                \"email\": \"valbona.krasniqi@hotmail.com\",\n" +
                "                \"phoneNumber\": \"1234567890\"\n" +
                "            }\n" +
                "        \"\"\";\n" +
                "\n" +
                "        mockMvc.perform(post(\"/api/teachers\")\n" +
                "                .contentType(MediaType.APPLICATION_JSON)\n" +
                "                .content(teacherJson))\n" +
                "                .andExpect(status().isCreated())\n" +
                "                .andExpect(jsonPath(\"$.firstName\").value(\"Alice\"));\n" +
                "    }\n" +
                "}\n");

        when(studentService.createStudent(dto)).thenReturn(student);

        Student result = studentController.create(dto);
        assertNotNull(result);
        assertEquals("Valbona", result.getFirstName());
        verify(studentService, times(1)).createStudent(dto);
    }
}
