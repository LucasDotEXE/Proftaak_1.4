package com.example.estelinglayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Play2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Attractie gwb = (Attractie) getIntent().getSerializableExtra("GWB_OBJECT");

        TextView pj = findViewById(R.id.title);
        pj.setText(gwb.getName());

        int resid = getResources().getIdentifier(
                gwb.getImageUrl(),
                "drawable",
                getPackageName()
        );

//        ImageButton back = findViewById(R.id.backProject);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Play2Activity.this, PlayFragment.class));
//            }
//        });



    }
}
