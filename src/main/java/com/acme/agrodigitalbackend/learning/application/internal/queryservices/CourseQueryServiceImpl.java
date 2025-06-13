package com.acme.agrodigitalbackend.learning.application.internal.queryservices;

import com.acme.agrodigitalbackend.learning.domain.model.aggregates.Course;
import com.acme.agrodigitalbackend.learning.domain.model.entities.LearningPathItem;
import com.acme.agrodigitalbackend.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.agrodigitalbackend.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.agrodigitalbackend.learning.domain.model.queries.GetLearningPathItemByCourseIdAndTutorialIdQuery;
import com.acme.agrodigitalbackend.learning.domain.services.CourseQueryService;
import com.acme.agrodigitalbackend.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    private final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.courseId());
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return courseRepository.findAll();
    }

    @Override
    public Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query) {
        return courseRepository.findById(query.courseId()).map(course -> course.getLearningPath().getLearningPathItemWithTutorialId(query.tutorialId()));
    }
}

