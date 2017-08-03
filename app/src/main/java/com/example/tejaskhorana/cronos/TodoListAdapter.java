package com.example.tejaskhorana.cronos;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tejaskhorana on 6/26/17.
 */

public class TodoListAdapter extends BaseAdapter {

    private SparseBooleanArray allChecked;

    public static final int CONSTANT_ITEM = 0;
    public static final int GOAL_ITEM = 1;
    public static final int TODO_ITEM = 2;
    private static final int MAX_COUNT = 3; //how many types of items there are
    private int numChecked;

    Context context;

    ArrayList<Deliverable> deliverables;

    View rootView;

    LayoutInflater inflater;

    public TodoListAdapter(Context applicationContext, ArrayList<Deliverable> deliverables, View rootView) {
        this.context = applicationContext;
        this.deliverables = deliverables;
        inflater = (LayoutInflater.from(applicationContext));
        allChecked = new SparseBooleanArray(deliverables.size());
        this.rootView = rootView;
        numChecked = 0;
    }

    @Override
    public int getCount() {
        return deliverables.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return deliverables.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        Deliverable d = deliverables.get(position);
        if (d instanceof Constant) {
            return CONSTANT_ITEM;
        } else if (d instanceof Goal) {
            return GOAL_ITEM;
        }
        return TODO_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return MAX_COUNT;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewGroup par = parent;
        Deliverable d = deliverables.get(position);
        final int pos = position;

        //if(view == null) {
        if (d instanceof Constant) {
            view = inflater.inflate(R.layout.deliverable_constant_cell, null);

            TextView constantName = (TextView) view.findViewById(R.id.constantName);
            constantName.setText(d.getDeliverableName());
        } else if (d instanceof Goal) {
            view = inflater.inflate(R.layout.deliverable_goal_cell, null);


            final CheckBox goalName = (CheckBox) view.findViewById(R.id.goalName);
            TextView goalDueDate = (TextView) view.findViewById(R.id.goalDueDate);
            goalName.setText(d.getDeliverableName());
            goalDueDate.setText(d.getDeliverableDueDate().toString());

            goalName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b == true) {
                        changeFabVisibility(true);
                        numChecked++;
                        allChecked.append(pos, true);
                        for(int i = pos+1; i < deliverables.size(); i++) {
                            if(deliverables.get(i) instanceof Todo) {
                                allChecked.append(i, true);
                                checkView(i, par);
                            } else {
                                break;
                            }
                        }
                    }
                    else {
                        allChecked.append(pos, false);
                        numChecked--;
                        if(noneChecked()) {
                            changeFabVisibility(false);
                        }
                    }
                }
            });

        } else {
            view = inflater.inflate(R.layout.deliverable_todo_cell, null);

            CheckBox todoName = (CheckBox) view.findViewById(R.id.todoName);
            TextView todoDueDate = (TextView) view.findViewById(R.id.todoDueDate);
            todoName.setText(d.getDeliverableName());
            if(allChecked.get(position)==true) {
                todoName.setChecked(true);
            }
            todoDueDate.setText(d.getDeliverableDueDate().toString());

            todoName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b == true) {
                        changeFabVisibility(true);
                        numChecked++;
                        allChecked.append(pos, true);
                    }
                    else {
                        allChecked.append(pos, false);
                        numChecked--;
                        if(noneChecked()) {
                            changeFabVisibility(false);
                        }
                    }

                }
            });


        }

        return view;
    }

    private void changeFabVisibility(boolean value) {
        FloatingActionButton completeFAB = (FloatingActionButton) rootView.findViewById(R.id.completefab);
        if(value)
            completeFAB.setVisibility(View.VISIBLE);
        else
            completeFAB.setVisibility(View.INVISIBLE);

    }

    private boolean noneChecked() {
        if(numChecked == 0) {
            return true;
        }
        return false;
    }


    private void checkView(int index, ViewGroup parent) {
        View child = parent.getChildAt(index);
        if(!(child == null)) {
            CheckBox todoName = (CheckBox) child.findViewById(R.id.todoName);
            todoName.setChecked(true);
        }
    }


    public ArrayList<Deliverable> getCheckedItems() {
        ArrayList<Deliverable> allCheckedItems = new ArrayList<Deliverable>();
        for(int i = 0 ; i < deliverables.size() ; i++) {
            if (allChecked.get(i) == true) {
                allCheckedItems.add(deliverables.get(i));
            }
        }
        return allCheckedItems;
    }

    public void removeCheckedItems() {
        ArrayList<Deliverable> allCheckedItems = getCheckedItems();
        allChecked.clear();
        for(Deliverable finishedDeliverable : allCheckedItems) {
            deliverables.remove(finishedDeliverable);
        }
        notifyDataSetChanged();
    }


}
