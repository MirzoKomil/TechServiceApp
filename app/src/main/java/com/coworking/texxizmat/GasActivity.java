package com.coworking.texxizmat;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.coworking.tehhizmat.R;
import com.coworking.texxizmat.adapter.CustomAdapter;
import com.coworking.texxizmat.model.Chapter;
import com.coworking.texxizmat.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class GasActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    CustomAdapter custAdapter;
    List<Chapter> chapterList;
    List<Topic> topicsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

               expandableListView = findViewById(R.id.expandableView_gaz);
        ImageView back_btn = findViewById(R.id.left_icon);
        addData();
        sendData();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


    }

    private void sendData() {
        custAdapter = new CustomAdapter(chapterList, GasActivity.this);
        expandableListView.setAdapter(custAdapter);
    }
    private void addData() {
        chapterList = new ArrayList<>();
        topicsList = new ArrayList<>();

        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        chapterList.add(new Chapter(1,"YANGI", topicsList));

        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        chapterList.add(new Chapter(1,"YANGI", topicsList));

        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        topicsList.add(new Topic("Yangi",".pdf"));
        chapterList.add(new Chapter(1,"YANGI", topicsList));
    }


}