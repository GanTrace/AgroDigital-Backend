package com.acme.agrodigitalbackend.learning.domain.model.commands;

public record AddTutorialToCourseLearningPathCommand(Long tutorialId, Long courseId) {
}