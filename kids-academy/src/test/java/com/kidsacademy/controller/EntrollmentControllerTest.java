package com.kidsacademy.controller;

import com.kidsacademy.entity.Enrollment;
import com.kidsacademy.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    public EnrollmentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnrollStudent() {

        Enrollment enrollment = new Enrollment();
        enrollment.setId(1);
        enrollment.setStatus("ACTIVE");

        when(enrollmentService.enrollStudent(1, 1)).thenReturn(enrollment);

        Enrollment result = enrollmentController.enroll(1, 1);

        assertNotNull(result);
        assertEquals("ACTIVE", result.getStatus());
        verify(enrollmentService, times(1)).enrollStudent(1, 1);
    }
}
