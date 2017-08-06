package com.example.namtnps06077.assigment_android_final;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by namtn on 06-Aug-17.
 */

public class CustomAdapterSinhVien extends BaseExpandableListAdapter {

    Context context;
    List<String> listTitle;
    List<String> listTitle2;
    HashMap<String, List<String>> listStudent;

    public CustomAdapterSinhVien(Context context, List<String> listTitle, HashMap<String, List<String>> listStudent) {
        this.context = context;
        this.listTitle = listTitle;
        this.listStudent = listStudent;
    }

    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listStudent.get(listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int studentPosition) {
        return listStudent.get(listTitle.get(groupPosition)).get(studentPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int studentPosition) {
        return studentPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_class_name, null);

        TextView txtClass_name = (TextView) view.findViewById(R.id.textViewClassName);

        String class_name = (String) getGroup(groupPosition);

        txtClass_name.setText(class_name);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int studentPosition, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_student_name, null);

        TextView student_name = (TextView) view.findViewById(R.id.textViewStudentName);

        try {
            String studentName = (String) getChild(groupPosition, studentPosition);

            student_name.setText(studentName);
        } catch (Exception e){
            Toast.makeText(context, "Null student", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int studentPosition) {
        return true;
    }

    public void filterData(ArrayList listClass){

        listTitle = new ArrayList<>();
        listTitle.addAll(listClass);
        notifyDataSetChanged();
    }
}
