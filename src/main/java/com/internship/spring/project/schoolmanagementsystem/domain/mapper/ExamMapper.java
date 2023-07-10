package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamQuestionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.PotentialAnswerDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Exam;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ExamQuestion;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ExamResult;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.PotentialAnswer;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ExamMapper {

    public static ExamDTO toDto (Exam e){
        return ExamDTO.builder()
                .id(e.getId())
                .duration(e.getDuration())
                .name(e.getName())
                .build();
    }

    public static Exam toEntity (ExamDTO e){
        return Exam.builder()
                .duration(e.getDuration())
                .name(e.getName())
                .build();
    }

    public static ExamResultDTO toResultDto (ExamResult e){
        return ExamResultDTO.builder()
                .passed(e.getPassed())
                .score(e.getScore())
                .id(e.getId())
                .build();
    }

    public static ExamResult toResultEntity (ExamResultDTO e){
        return ExamResult.builder()
                .passed(e.getPassed())
                .score(e.getScore())
                .build();
    }

    public static ExamQuestionDTO toQuestionDto (ExamQuestion e){
        return ExamQuestionDTO.builder()
                .value(e.getValue())
                .id(e.getId())
                .build();
    }

    public static ExamQuestion toQuestionEntity (ExamQuestionDTO e){
        return ExamQuestion.builder()
                .value(e.getValue())
                .build();
    }

    public static PotentialAnswerDTO potentialAnswerDto (PotentialAnswer p){
        return PotentialAnswerDTO.builder()
                .id(p.getId())
                .valid(p.getValid())
                .value(p.getValue())
                .build();
    }

    public static PotentialAnswer potentialAnswerEntity (PotentialAnswerDTO p){
        return PotentialAnswer.builder()
                .valid(p.getValid())
                .value(p.getValue())
                .build();
    }

    public static ExamDTO toExamRequest (Exam e){
        var dto = toDto(e);
        var questions = e.getExamQuestions().stream().map(q->{
            var questionEntity = toQuestionDto(q);
            var potentialList = q.getPotentialAnswers().stream().map(ExamMapper::potentialAnswerDto).collect(Collectors.toList());
            Collections.shuffle(potentialList);
            questionEntity.setPotentialAnswerDTOS(potentialList);
            return  questionEntity;
        }).collect(Collectors.toList());
        dto.setExamQuestionDTOList(questions);
        return dto;
    }


}
