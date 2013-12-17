package com.johnathangilday.autograder;

import com.google.common.collect.Lists;
import com.johnathangilday.autograder.domain.Answer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;

@RunWith(JUnit4.class)
public class ProcessSheetJobTest {

    @Test
    public void processSheet1() throws IOException {
        // GIVEN happy path testSheet1.jpg in test resources
        InputStream in = getClass().getResource("/testSheet1.jpg").openStream();
        ProcessSheetJob job = new ProcessSheetJob(in);

        // WHEN process testSheet1.jpg
        List<Answer> answers = job.process();

        // THEN answers are C, D, B, C, E
        List<Answer> expectedAnswers = Lists.newArrayList(Answer.make('C'), Answer.make('D'), Answer.make('B'), Answer.make('C'), Answer.make('E'));
        assertThat(answers).isSameAs(expectedAnswers);
    }
}
