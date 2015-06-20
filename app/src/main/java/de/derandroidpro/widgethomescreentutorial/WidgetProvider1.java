package de.derandroidpro.widgethomescreentutorial;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import java.util.Random;

public class WidgetProvider1 extends AppWidgetProvider {

    String widgettextviewtext;
    final String BACKGROUNDCOLORACTION = "sdfjkldjldkf";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent appopenIntent = new Intent(context, MainActivity.class);

        PendingIntent appopenPendingIntent = PendingIntent.getActivity(context,0,appopenIntent,0);

        remoteViews.setOnClickPendingIntent(R.id.button2, appopenPendingIntent);

        remoteViews.setOnClickPendingIntent(R.id.button3,onClickPendingIntent(context,BACKGROUNDCOLORACTION) );

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);


        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if(intent.getAction().toString() == BACKGROUNDCOLORACTION){
            int color = Color.argb(255, new Random().nextInt(255),  new Random().nextInt(255), new Random().nextInt(255));
            remoteViews.setInt(R.id.widget_layout, "setBackgroundColor", color);
            updateWidgetNow(context, remoteViews);
        }


        if(intent.hasExtra("ETTEXTEXTRAKEY")){
            widgettextviewtext = intent.getStringExtra("ETTEXTEXTRAKEY");
            remoteViews.setTextViewText(R.id.textView, widgettextviewtext);
            updateWidgetNow(context, remoteViews);
        }


        super.onReceive(context, intent);
    }

  public void updateWidgetNow (Context context, RemoteViews remoteViews){
      ComponentName widgetComponent = new ComponentName(context, WidgetProvider1.class);
      AppWidgetManager.getInstance(context).updateAppWidget(widgetComponent, remoteViews);
  }


    public PendingIntent onClickPendingIntent (Context context, String stringAction){

        Intent onClickIntent = new Intent(context, WidgetProvider1.class);
        onClickIntent.setAction(stringAction);

        return PendingIntent.getBroadcast(context,0,onClickIntent,0);
    }


}
