package basel.com.ActivitiesMenuSample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity2Child1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_child1);

        String dummyStingExtra = getIntent().getStringExtra("dummyStingExtra");
        Toast.makeText(this, "dummyStingExtra : "+dummyStingExtra, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
