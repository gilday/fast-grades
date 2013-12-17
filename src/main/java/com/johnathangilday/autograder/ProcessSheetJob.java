package com.johnathangilday.autograder;

import com.johnathangilday.autograder.domain.Answer;
import com.johnathangilday.autograder.exception.AppException;

import java.io.InputStream;
import java.util.List;

public class ProcessSheetJob {

    private final InputStream in;

    public ProcessSheetJob(InputStream in) {
        this.in = in;
    }

    public List<Answer> process() {
        throw new AppException("Not yet implemented");
    }
}
