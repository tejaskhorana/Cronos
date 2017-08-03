package com.example.tejaskhorana.cronos;

import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import org.joda.time.DateTime;

/**
 * Created by tejaskhorana on 7/31/17.
 */

public class TodoFragment extends Fragment {

    static ArrayList<Deliverable> allDeliverables = new ArrayList<Deliverable>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.todo_fragment_layout, container, false);

        //tejastest
        Constant a = new Constant("Family", "Take care of them", null);
        Constant b = new Constant("Google", "Complete all deliverables", null);
        Goal c = new Goal("Finish the ARX Bug", "It's hard", new DateTime(), null, null, false);
        Todo d = new Todo("Look into the printer issue in File A", null, new DateTime(), null, false);
        Todo j = new Todo("Look intwerwasdesdfo the printer issue in File A", null, new DateTime(), null, false);
        Todo i = new Todo("Look intwerwsdfsesdfo the printer issue in File A", null, new DateTime(), null, false);
        Constant e = new Constant("Famasdfily", "Take care of them", null);
        Constant f = new Constant("Goofdsdfgle", "Complete all deliverables", null);
        Goal g = new Goal("Finish asdfthe ARX Bug", "It's hard", new DateTime(), null, null, false);
        Todo h = new Todo("Look intwerwesdfo the printer issue in File A", null, new DateTime(), null, false);
        allDeliverables.add(a);
        allDeliverables.add(b);
        allDeliverables.add(c);
        allDeliverables.add(d);
        allDeliverables.add(j);
        allDeliverables.add(i);
        allDeliverables.add(e);
        allDeliverables.add(f);
        allDeliverables.add(g);
        allDeliverables.add(h);

        final ListView lv = (ListView) rootView.findViewById(R.id.todolisttest);
        final TodoListAdapter myAdapter = new TodoListAdapter(this.getContext(), allDeliverables, rootView);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Deliverable d = (Deliverable) myAdapter.getItem(pos);
                String message = d.getDeliverableNotes();
                if (message == null) {
                    message = "No Details";
                }
                Snackbar mySnackbar = Snackbar.make(rootView, message, BaseTransientBottomBar.LENGTH_LONG);
                mySnackbar.show();
            }
        });

        FloatingActionButton completionFab = (FloatingActionButton) rootView.findViewById(R.id.completefab);
        completionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Deliverable> allCheckedItems = myAdapter.getCheckedItems();

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Delete these items?")
                        .setMessage("U SURE, delete " + allCheckedItems.size() + " items?!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                myAdapter.removeCheckedItems();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


        return rootView;
    }

    public static void addToDeliverables(Deliverable d) {
        allDeliverables.add(d);
        //it call update will be immediate
    }


}
