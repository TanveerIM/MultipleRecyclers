package com.tanveerm.recyclerviewhorizontalvertical.ativities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tanveerm.recyclerviewhorizontalvertical.R;
import com.tanveerm.recyclerviewhorizontalvertical.adapter.SubjectAdapter;
import com.tanveerm.recyclerviewhorizontalvertical.model.Chapter;
import com.tanveerm.recyclerviewhorizontalvertical.model.Subjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewSubjects;
    private SubjectAdapter mSubjectAdapter;
    private ArrayList<Subject> mSubjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewSubjects = findViewById(R.id.recyclerView_subjects);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerViewSubjects.setLayoutManager(manager);
        addItemFromJSON();
    }

    private void addItemFromJSON() {

        ArrayList<Subjects> subjectsArrayList = new ArrayList<>();
        ArrayList<Chapter> chapterArrayList = new ArrayList<>();

        String jsonDataString = readJSONDataFromFile();
        try {
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Subjects subjects = new Subjects();

                subjects.setId(jsonObject.getInt("subjectid"));
                subjects.setSubjectName(jsonObject.getString("subjectname"));

                JSONArray innerArray = jsonObject.getJSONArray("imageurl");

                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject innerObject = innerArray.getJSONObject(j);

                    Chapter chapter = new Chapter();
                    chapter.setId(jsonObject.getInt("subjectid"));
                    chapter.setChapterName(innerObject.getString("number"));
                    chapter.setImageUrl(innerObject.getString("chapters"));

                    chapterArrayList.add(chapter);
                    subjects.chapters.add(chapter);
                }

                subjectsArrayList.add(subjects);
            }

            SubjectAdapter adapter = new SubjectAdapter(this, subjectsArrayList);
            mRecyclerViewSubjects.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private String readJSONDataFromFile() {
        String jsonString = null;
        InputStream inputStream = null;

        try {
            inputStream = getResources().openRawResource(R.raw.subjects);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
