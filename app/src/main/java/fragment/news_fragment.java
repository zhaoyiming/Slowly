package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.ming.slowly.R;

public class news_fragment extends Fragment {
    private ListView listView;
    CardView cardView;
    private static String URL = "https://api.jisuapi.com/news/get?channel=健康&num=40&appkey=f1c9527594aa7b1e";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragment_layout,container,false);
        listView = (ListView) view.findViewById(R.id.list_view);
        new NewsAsyncTask().execute(URL);


        cardView=view.findViewById(R.id.cardview_news);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),webview.class);
                intent.putExtra("url","https://www.sohu.com/a/312541766_464410?scm=0.0.0.0&spm=smpc.ch24.r-3pic-footer.2.1558346948689ICb4YxH");
                startActivity(intent);
            }
        });
        return view;


    }

    //*&*异步加载，处理耗时任务,UI更新
    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(String... strings) {
            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(final List<NewsBean> newsBeen) {
            super.onPostExecute(newsBeen);
            NewsAdapter adapter = new NewsAdapter(getContext(), newsBeen);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent=new Intent(getContext(),webview.class);
                    intent.putExtra("url",newsBeen.get(position).url);
                    intent.putExtra("content",newsBeen.get(position).neirong);
                    intent.putExtra("title",newsBeen.get(position).maintitle);
//                    Toast.makeText(getApplicationContext(),newsBeen.get(position).url,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
        }

    }

    //*&*JSON解析网页获取数据
    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<>();
        try {
            String jsonString = readSteam(new URL(url).openStream());
            Log.i("DATA", jsonString);
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONObject resultarr = (JSONObject) jsonObject.opt("result");

                JSONArray jsonArray = resultarr.optJSONArray("list");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.id=i;
                    newsBean.content = jsonObject.getString("time");
                    newsBean.neirong=jsonObject.getString("content");
                    newsBean.maintitle=jsonObject.getString("title");
                    newsBean.title = jsonObject.getString("title");
                    newsBean.url=jsonObject.getString("weburl");
                    newsBeanList.add(newsBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBeanList;
    }

    //*&*读取数据流
    private String readSteam(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            try {
                while ((line = br.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}