package dungeon.dr.company_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class INfo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);


       Bundle info_bundle = getIntent().getExtras();
       if(info_bundle==null)
        {
           return;
        }
       String name = info_bundle.getString("car_name");
       final TextView info_name = (TextView)findViewById(R.id.info_name);
       info_name.setText(name);
    }
}




