package com.example.jaykishan.mroads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    final Context context = this;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                final Dialog dialog = new Dialog(context,R.style.Dialog);
//                dialog.setContentView(R.layout.custom_dialog);
//
//                dialog.setTitle("Compose Email:");

                 dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle( Html.fromHtml("<font color='#37474F'>Compose Email to:</font>"))
                        .setView(R.layout.custom_dialog)
                        .create();

                dialog.show();
                //dialog.getWindow().setLayout(1000, 500);

                FloatingActionButton innerFab =  (FloatingActionButton)dialog.findViewById(R.id.innerfab);
                innerFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        dialog.cancel();
//
//                        Toast toast = Toast.makeText(context, "Mail is Sending", Toast.LENGTH_SHORT);
//                        toast.show();
                        final EditText composeTo = (EditText)dialog.findViewById(R.id.editText);

                        String email = composeTo.getText().toString();

                        if(isValidEmail(email))
                        {
                            new SendMailTask(MainActivity.this).execute(composeTo.getText().toString());


                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Invalid EMail",Toast.LENGTH_SHORT).show();
                        }



                        //toast.setText("Mail Sent");

                        dialog.cancel();

                    }
                });

                dialog.show();

            }
        });
    }

    public boolean isValidEmail(String string)
    {

        if(string.contains("@") && string.endsWith(".com"))
        {
            return true;
        }


        return false;


    }


    public AlertDialog getDialog()
    {
        return dialog;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!MainActivity.hasActiveInternetConnection(getApplicationContext()))
        {

            Toast.makeText(MainActivity.this, R.string.no_network_connection,Toast.LENGTH_LONG).show();
        }

    }

    public static boolean hasActiveInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class SendMailTask extends AsyncTask<String,String,Integer> {

        private Activity sendMailActivity;
        Toast toast=new Toast(MainActivity.this);

        private String LOG_CAT = SendMailTask.class.getName();

        public SendMailTask(Activity activity) {
            sendMailActivity = activity;

        }

        @Override
        protected Integer doInBackground(String... params) {
            int result = 0;
            try {
                Log.i(LOG_CAT , "About to instantiate GMail...");

                publishProgress(getString(R.string.processing));
                GMail androidEmail = new GMail(params[0].toString());

                //publishProgress("Preparing mail message....");
                androidEmail.createEmailMessage();

                //publishProgress("Sending email....");
                result=androidEmail.sendEmail();

                //publishProgress("Email Sent.");
            } catch (Exception e) {
                Log.e("SendMailTask", e.getMessage(), e);
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            toast.makeText(MainActivity.this,values[0],Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            toast.cancel();
            if(integer.intValue()==0)
            {
                Toast.makeText(MainActivity.this, R.string.mail_not_sent, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, R.string.mail_sent,Toast.LENGTH_SHORT).show();
            }
        }
    }



}
