package com.coworking.texxizmat.adapter;

import android.adservices.topics.Topic;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.model.Chapter;

import java.util.List;

public class CustomAdapter extends BaseExpandableListAdapter {

    List<Chapter> chapterList;

    Context context;

    public CustomAdapter(List<Chapter> chapterList, Context context) {

        this.chapterList = chapterList;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return chapterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chapterList.get(groupPosition).getTopicsList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return chapterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chapterList.get(groupPosition).getTopicsList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return chapterList.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        }

        TextView chapterName = convertView.findViewById(R.id.chapterTitle);
        chapterName.setText(chapterList.get(groupPosition).getChapterName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.topic_spinner, parent, false);
        }
        TextView topicName = (TextView) convertView.findViewById(R.id.topicTitle);
        topicName.setText(chapterList.get(groupPosition).getTopicsList().get(childPosition).getTopicName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
