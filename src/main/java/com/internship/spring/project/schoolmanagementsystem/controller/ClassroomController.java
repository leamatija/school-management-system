package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomSessionRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomStudentsDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.TimetableDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping()
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO){
        return ResponseEntity.ok(classroomService.createClassroom(classroomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable Integer id, @RequestBody ClassroomDTO classroomDTO){
        return ResponseEntity.ok(classroomService.updateClassroom(id,classroomDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms(){
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById (@PathVariable Integer id){
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom (@PathVariable Integer id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{classroomId}/students")
        public ResponseEntity<String> addStudentsToClassroom(@PathVariable Integer classroomId, @RequestBody ClassroomStudentsDTO classroomStudentsDTO){
        return ResponseEntity.ok(classroomService.addStudentsToClassroom(classroomId,classroomStudentsDTO.getStudentsId()));
    }

    @PostMapping("/{classroomId}/sessions")
    public ResponseEntity<Void> addClassSessionToClassroom (@PathVariable Integer classroomId, @RequestBody ClassroomSessionRequestDTO req){
        classroomService.addClassSessionsToClass(classroomId,req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<TimetableDTO>> getWeeklySchedule(@RequestParam String start, @RequestParam String finish){
        return ResponseEntity.ok(classroomService.getWeeklyTimetable(LocalDateTime.parse(start),LocalDateTime.parse(finish)));
    }

    @GetMapping("/teacher/schedule")
    public ResponseEntity<List<TimetableDTO>> getTeachersWeeklySchedule(@RequestParam String start, @RequestParam String finish){
        return ResponseEntity.ok(classroomService.getTeachersWeeklyTimetable(LocalDateTime.parse(start),LocalDateTime.parse(finish)));
    }

    @DeleteMapping("/{classroomId}/remove")
    public ResponseEntity<Void> removeStudent(@PathVariable Integer classroomId, @RequestParam Integer studentId){
        classroomService.removeStudentFromClassroom(classroomId,studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
