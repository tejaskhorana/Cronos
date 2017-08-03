package com.example.tejaskhorana.cronos;

/**
 * Created by tejaskhorana on 8/1/17.
 */

import org.joda.time.DateTime;
import java.util.ArrayList;

public class Goal extends Deliverable {


    public Goal(String deliverableName, String deliverableNotes, DateTime deliverableDueDate, ArrayList<Deliverable> deliverableChildren, Deliverable deliverableParent, boolean isCompleted) {
        super(deliverableName, deliverableNotes);
        this.deliverableDueDate = deliverableDueDate;
        this.deliverableChildren = deliverableChildren;
        this.deliverableParent = deliverableParent;
        this.isCompleted = isCompleted;
    }

    public boolean addChild(Deliverable child) {
        if(child instanceof  Todo) {
            deliverableChildren.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(Deliverable child) {
        return deliverableChildren.remove(child);
    }

    public boolean setCompleted() {
        isCompleted = true;
        for(Deliverable child : deliverableChildren) {
            child.setCompleted();
        }
        return true;
    }

    public boolean setUncompleted() {
        isCompleted = false;
        for(Deliverable child : deliverableChildren) {
            child.setUncompleted();
        }
        return true;
    }

    public boolean changeCompletionStatus() {
        isCompleted = !isCompleted;
        if(isCompleted)
            setCompleted();
        else
            setUncompleted();
        return true;
    }

    public boolean setDeliverableDueDate(DateTime dueDate) {
        this.deliverableDueDate = dueDate;
        return true;
    }

    public boolean setParent(Deliverable d) {
        if(d instanceof Constant) {
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
        Deliverable deliverable = (Goal) o;
        // field comparison
        return deliverableName.equals(deliverable.getDeliverableName()) && deliverableDueDate.equals(deliverable.getDeliverableDueDate()) && deliverableNotes.equals(deliverable.getDeliverableNotes());
    }
}
