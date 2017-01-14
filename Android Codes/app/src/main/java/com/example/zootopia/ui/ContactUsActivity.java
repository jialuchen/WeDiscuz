package com.example.zootopia.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.zootopia.wediscuz.R;

public class ContactUsActivity extends Activity {
    private Button btnBack;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contactus);

        this.btnBack = (Button)findViewById(R.id.contactus_btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
