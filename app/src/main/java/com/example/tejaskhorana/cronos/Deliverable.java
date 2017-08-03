package com.example.tejaskhorana.cronos;

/**
 * Created by tejaskhorana on 6/25/17.
 */


/*
Deliverable work = new Deliverable(DeliverableType.CONSTANT, "Google", "My Occupation", ____ );
Deliverable danceteam = new Deliverable(DeliverableType.CONSTANT, "GT Insaafi", "My Dance Team. Additional Managerial Role.", ____ );
Deliverable practiceOne = new Deliverable(DeliverableType.GOAL, "Bhangra Tryout Piece", "One of 3 pieces", ____ , danceteam);
Deliverable bugFixing = new Goal(DeliverableType.GOAL, "Fix 10 bugs on Android Security OS", "Due this week!", ____ , work);
 */

import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Deliverable {

    String deliverableName;
    String deliverableNotes;
    DateTime deliverableDueDate;
    ArrayList<Deliverable> deliverableChildren;
    Deliverable deliverableParent;
    boolean isCompleted;

    public Deliverable(String deliverableName, String deliverableNotes) {
        this.deliverableName = deliverableName;
        this.deliverableNotes = deliverableNotes;
    }

    public String getDeliverableName() {
        return deliverableName;
    }

    public String getDeliverableNotes() {
        return deliverableNotes;
    }

    public Deliverable getParent() { return deliverableParent; }

    public DateTime getDeliverableDueDate() {
        return deliverableDueDate;
    }

    public boolean isComplete() { return isCompleted; }

    public void setDeliverableName(String dName) {
        deliverableName = dName;
    }

    public void setDeliverableNotes(String dNotes) {
        deliverableNotes = dNotes;
    }

    public abstract boolean addChild(Deliverable child);

    public abstract boolean removeChild(Deliverable child);

    /*{
        if(capableChildren) {
            deliverableChildren.add(child);
            return true;
        }
        return false;
    }
*/
    public abstract boolean setCompleted();

    /*
    {
        if(capableDueDate) {
            isCompleted = true;
            for (Deliverable child : deliverableChildren) {
                child.setCompleted();
            }
        }
    }
    */

    public abstract boolean setUncompleted();

    /*
    {
        if(capableDueDate) {
            isCompleted = false;
            for(Deliverable child : deliverableChildren) {
                child.setUncompleted();
            }
        }
    }
    */

    public abstract boolean changeCompletionStatus();

    /*
    {
        if(capableDueDate) {
            isCompleted = !isCompleted;
            if (isCompleted) {
                setCompleted();
            } else {
                setUncompleted();
            }
            return true;
        }
        return false;
    }
    */

    public abstract boolean setDeliverableDueDate(DateTime dDueDate);

    public abstract boolean setParent(Deliverable d);

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
        Deliverable deliverable = (Deliverable) o;
        // field comparison
        return deliverableName.equals(deliverable.getDeliverableName()) && deliverableNotes.equals(deliverable.getDeliverableNotes());
    }


}
