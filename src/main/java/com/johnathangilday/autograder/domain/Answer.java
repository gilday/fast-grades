package com.johnathangilday.autograder.domain;

import com.google.common.base.Objects;
import com.johnathangilday.autograder.exception.AppException;

/**
 * Represents the answer to a multiple choice question
 */
public class Answer {

    private static Answer blank;

    public static Answer blank() {
        if (blank == null) {
            blank = new Answer();
        }
        return blank;
    }

    public static Answer make(char value) {
        return new Answer(value);
    }

    private final boolean isAnswered;
    private final char value;

    private Answer(char value) {
        this.isAnswered = true;
        this.value = Character.toUpperCase(value);
    }

    private Answer() {
        this.isAnswered = false;
        this.value = 0;
    }

    public boolean isAnswered() {
        return this.isAnswered;
    }

    public char getValue() {
        if (isAnswered) {
            return this.value;
        } else {
            throw new AppException("This Answer has no value - it is blank");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Answer answer = (Answer) other;
        return Objects.equal(getValue(), answer.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
