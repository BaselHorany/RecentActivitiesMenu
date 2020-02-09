package basel.com.ActivitiesMenuSample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

    }

    public void goToChat(View mView){
        Intent intent = new Intent(this, Activity2Child1.class);
        intent.putExtra("dummyStingExtra","user "+mView.getY()+" chat");
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
