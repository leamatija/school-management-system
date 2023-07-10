package com.internship.spring.project.schoolmanagementsystem.service.impl;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.QuestionResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ExamResult;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.PotentialAnswer;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ExamMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ExamRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ExamResultRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.PotentialAnswerRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.CLASS_SESSION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ClassSessionRepository classSessionRepository;
    private final ExamResultRepository resultRepository;
    private final PotentialAnswerRepository potentialAnswerRepository;

    @Override
    public ExamDTO createExam(Integer sessionId , ExamDTO examDTO) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException(String.format(CLASS_SESSION_NOT_FOUND,sessionId)));
        var exam = ExamMapper.toEntity(examDTO);
        var questions = examDTO.getExamQuestionDTOList().stream()
                .map(q-> Pair.of(q,ExamMapper.toQuestionEntity(q)))
                .map(pair -> {
                   var qEntity = pair.getSecond();
                   var potentialAnswer = pair.getFirst().getPotentialAnswerDTOS().stream()
                            .map(p->{
                                var pEntity = ExamMapper.potentialAnswerEntity(p);
                                pEntity.setExamQuestion(qEntity);
                                return pEntity;
                            }).collect(Collectors.toList());
                   qEntity.setPotentialAnswers(potentialAnswer);
                   qEntity.setExam(exam);
                   return qEntity;
                }).collect(Collectors.toList());
        exam.setExamQuestions(questions);
        exam.setClassSession(session);
        return ExamMapper.toDto(examRepository.save(exam));
    }

    @Override
    public ExamDTO takeExam(Integer sessionId) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException(String.format(CLASS_SESSION_NOT_FOUND,sessionId)));
      return ExamMapper.toExamRequest(session.getExam());

    }

    @Override
    public ExamDTO findById(Integer id) {
        return null;
    }

    @Override
    public ExamResultDTO evaluateExam(List<Integer> answersList) {
        var potentialAnswer = potentialAnswerRepository.findAllById(answersList);
        var result = new ExamResult();
        result.setPotentialAnswers(potentialAnswer);
        result = resultRepository.save(result);

        var groupedAnswers = result.getPotentialAnswers().stream()
                .collect(Collectors.groupingBy(PotentialAnswer::getExamQuestion));

        var questionsResult = groupedAnswers.entrySet().stream().map(entry ->{
            var res = new QuestionResultDTO();
            res.setQuestion(entry.getKey().getValue());
            var correctAnswers = entry.getKey().getPotentialAnswers().stream()
                    .filter(p->p.getValid()).map(ExamMapper::potentialAnswerDto).collect(Collectors.toList());
            var selectedAnswers = entry.getValue().stream().map(ExamMapper::potentialAnswerDto).collect(Collectors.toList());
            res.setValidAnswer(correctAnswers);
            res.setSelectedAnswer(selectedAnswers);
            if(correctAnswers.size()>selectedAnswers.size()){
                res.setPassed(true);
            }else {
                res.setPassed(false);
            }
            return res;
        }).collect(Collectors.toList());


        var examResultDto = new ExamResultDTO();
        examResultDto.setId(result.getId());
        examResultDto.setResults(questionsResult);
        var validAnswerSum = questionsResult.stream().map(q->q.getValidAnswer().size())
                 .collect(Collectors.toList()).stream().reduce(0, Integer::sum);
        var selectedAnswerSum = questionsResult.stream().flatMap(q->q.getSelectedAnswer().stream())
                .filter(a ->a.getValid())
                .collect(Collectors.toList());

        var toCalc = (Double.valueOf(selectedAnswerSum.size())/Double.valueOf(validAnswerSum));
        Double totalScored = toCalc * 100.0;
        Boolean passed = totalScored>=50.0;

        examResultDto.setScore(totalScored);
        examResultDto.setPassed(passed);

        result.setScore(totalScored);
        result.setPassed(passed);
        resultRepository.save(result);

        return examResultDto;

    }

    @Override
    public void deleteExam(Integer id) {

    }
}
