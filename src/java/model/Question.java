/*
 * Copyright 2022 Fangl
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Fangl
 * which accompanies this distribution, and is available at
 * https://github.com/fanglong-it
 *
 * Contributors:
 *    Fangl - initial API and implementation and/or initial documentation
 */
package model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Fangl
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Question implements Serializable {

    private int questionId;
    private String content;
    private int subjectId;
    private int lessonId;
    private int topicId;
    private String level;
    private boolean status;
    private int quizId;
    private String dimension;
    private boolean isMultipleChoice;

    public boolean isIsMultipleChoice() {
        return isMultipleChoice;
    }

    public void setIsMultipleChoice(boolean isMultipleChoice) {
        this.isMultipleChoice = isMultipleChoice;
    }
    

}
