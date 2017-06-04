package in.thegeekybaniya.yoump3;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    EditText etURL;
    Button btDL;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btDL= (Button) findViewById(R.id.button);
        etURL= (EditText) findViewById(R.id.editText);
        tv= (TextView) findViewById(R.id.textView);


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();


        if (Intent.ACTION_SEND.equals(action) && type != null) {

            Bundle extras= intent.getExtras();
            String strUrl= extras.getString(Intent.EXTRA_TEXT);

            etURL.setText(strUrl);
            downloadFile(strUrl);



        }




        btDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUrl= etURL.getText().toString();

                downloadFile(strUrl);






            }
        });





    }


    public void downloadFile(String strUrl)
    {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://www.youtubeinmp3.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetFileUrl service= retrofit.create(GetFileUrl.class);

        Call<Link> call = service.linkYou(strUrl);


        call.enqueue(new Callback<Link>() {
            @Override
            public void onResponse(Call<Link> call, Response<Link> response) {
                Link link= response.body();

                if (link==null){
                    Toast.makeText(MainActivity.this, "Invalid Link", Toast.LENGTH_SHORT).show();

                }else{
                    Log.d("Letsee", "onClick: "+link.getTitle());

                    tv.setText(link.getTitle());

                    DownloadManager.Request request= new DownloadManager.Request(Uri.parse(link.getLink()));

                    request.setDescription("Downloading Song");
                    request.setTitle(link.getTitle());
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, link.getTitle()+".mp3");
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);








                }




            }

            @Override
            public void onFailure(Call<Link> call, Throwable t) {

            }
        });
    }








}
