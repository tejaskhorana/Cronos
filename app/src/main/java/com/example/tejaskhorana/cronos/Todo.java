package com.example.tejaskhorana.cronos;

/**
 * Created by tejaskhorana on 8/1/17.
 */

import org.joda.time.DateTime;

public class Todo extends Deliverable {

    public Todo(String deliverableName, String deliverableNotes, DateTime deliverableDueDate, Deliverable deliverableParent, boolean isCompleted) {
        super(deliverableName, deliverableNotes);
        this.deliverableDueDate = deliverableDueDate;
        this.deliverableParent = deliverableParent;
        this.isCompleted = isCompleted;
    }

    public boolean addChild(Deliverable child) {
        return false;
    }

    public boolean removeChild(Deliverable child) {
        return false;
    }

    public boolean setCompleted() {
        isCompleted = true;
        return true;
    }

    public boolean setUncompleted() {
        isCompleted = false;
        return true;
    }

    public boolean changeCompletionStatus() {
        isCompleted = !isCompleted;
        return true;
    }


    public boolean setDeliverableDueDate(DateTime dueDate) {
        if(deliverableParent != null && deliverableParent.deliverableDueDate != null) {
            this.deliverableDueDate = dueDate;
            return true;
        } else {
            DateTime parentDueDateTime = deliverableParent.deliverableDueDate;
            if(parentDueDateTime.isAfter(dueDate)) {
                this.deliverableDueDate = dueDate;
                return true;
            }
            return false;
        }
    }

    public boolean setParent(Deliverable d) {
        if(d instanceof Constant || d instanceof Goal) {
            deliverableParent = d;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Deliverable deliverable = (Todo) o;
        // field comparison
        return deliverableName.equals(deliverable.getDeliverableName()) && deliverableNotes.equals(deliverable.getDeliverableNotes());
    }




}
