package com.example.tejaskhorana.cronos;


/**
 * Created by tejaskhorana on 8/1/17.
 */

import org.joda.time.DateTime;
import java.util.ArrayList;

public class Constant extends Deliverable {

    public Constant(String deliverableName, String deliverableNotes, ArrayList<Deliverable> deliverableChildren) {
        super(deliverableName, deliverableNotes);
        this.deliverableChildren = deliverableChildren;
    }

    //Todos added in the beginning, that ways display is easier later
    public boolean addChild(Deliverable child) {
        if(child instanceof Goal) {
            deliverableChildren.add(child);
            return true;
        } else if (child instanceof Todo) {
            deliverableChildren.add(0, child);
            return true;
        }
        return false;
    }

    public boolean removeChild(Deliverable child) {
        return deliverableChildren.remove(child);
    }

    public boolean setCompleted() {
        return false;
    }

    public boolean setUncompleted() {
        return false;
    }

    public boolean changeCompletionStatus() {
        return false;
    }

    public boolean setDeliverableDueDate(DateTime dueDate) {
        return false;
    }

    public boolean setParent(Deliverable d) {
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
        Deliverable deliverable = (Constant) o;
        // field comparison
        return deliverableName.equals(deliverable.getDeliverableName()) && deliverableDueDate.equals(deliverable.getDeliverableDueDate()) && deliverableNotes.equals(deliverable.getDeliverableNotes());
    }
}
