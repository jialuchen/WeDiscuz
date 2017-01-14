package com.example.zootopia.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.zootopia.wediscuz.R;


public class FeedbackActivity extends Activity {
    private EditText etFeedback;
    private Button btnSubmit;
    private Button btnBack;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feedback);

        this.etFeedback = (EditText)findViewById(R.id.feedback_editText);
        this.btnSubmit = (Button) findViewById(R.id.feedback_btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = etFeedback.getText().toString().trim();
                //check feedback empty or not
                if(comment.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter some feedback before submission." , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Thanks for your feedback!" , Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        this.btnBack = (Button) findViewById(R.id.feedback_btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
