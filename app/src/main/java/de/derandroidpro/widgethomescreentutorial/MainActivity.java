package de.derandroidpro.widgethomescreentutorial;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity{

    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSendTextToWidget = new Intent(MainActivity.this, WidgetProvider1.class);
                intentSendTextToWidget.setAction(AppWidgetManager.EXTRA_CUSTOM_EXTRAS);
                intentSendTextToWidget.putExtra("ETTEXTEXTRAKEY", et.getText().toString());
                sendBroadcast(intentSendTextToWidget);
            }
        });
    }

}
