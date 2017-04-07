package test.bwie.com.firstdaydemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private HttpURLConnection mHuc;
    private Bean mBean;

    private List<AddressBean> lab = new ArrayList<>();
   private  MyBaseAdapter myBaseAdapter = new MyBaseAdapter(lab,MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.lv);

        MyTask my = new MyTask();
        my.execute(MyUrls.MYURL);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,mBean.getList().get(position).getId()+"",Toast.LENGTH_LONG).show();
            }
        });
        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lab.remove(position);
                myBaseAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public class MyTask extends AsyncTask<String, Integer, Bean> {
        //进行耗时操作
        @Override
        protected Bean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                mHuc = (HttpURLConnection) url.openConnection();
                mHuc.setReadTimeout(5000);
                mHuc.setConnectTimeout(5000);
                mHuc.setRequestMethod("GET");
                if (mHuc.getResponseCode() == 200) {

                    InputStream inputStream = mHuc.getInputStream();
                    byte[] b = new byte[1024];
                    int len = 0;

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    while ((len = inputStream.read(b)) != -1) {

                        bos.write(b, 0, len);
                    }
                    String s = bos.toString("utf-8");
                    Gson gson = new Gson();

                    mBean = gson.fromJson(s, Bean.class);
                    return mBean;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bean bean) {
            for (int i = 0; i <mBean.getList().size() ; i++) {
                String address = bean.getList().get(i).getAddress();
                String site_name = bean.getList().get(i).getSite_name();

                String id = bean.getList().get(i).getId()+"";
                lab.add(new AddressBean(id,site_name,address));
            }
            mLv.setAdapter(myBaseAdapter);

        }
    }
}
