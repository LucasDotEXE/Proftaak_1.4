/*package com.example.estelinglayouttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectDetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detailed);

        Attractie gwb = (Attractie) getIntent().getSerializableExtra("GWB_OBJECT");

        ImageView iv = findViewById(R.id.gbw_big_image);
        TextView tx = findViewById(R.id.Detail);
        tx.setText(getString(gwb.getDetails()));
        TextView pj = findViewById(R.id.title);
        pj.setText(gwb.getName());

        int resid = getResources().getIdentifier(
                gwb.getImageUrl(),
                "drawable",
                getPackageName()
        );

        ImageButton back = findViewById(R.id.backProject);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectDetailedActivity.this, ProjectList.class));
            }
        });


        iv.setImageResource(resid);

    }
}*/
