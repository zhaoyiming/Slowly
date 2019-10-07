package fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ming.slowly.R;

import java.util.List;

/**
 * Created by Leixiansheng on 2017/3/21.
 */

public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> newsBeanList;
    private LayoutInflater inflater;


    public NewsAdapter(Context context, List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return newsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item, null);
            viewHolder.numberid=view.findViewById(R.id.number_id);
            viewHolder.title = (TextView) view.findViewById(R.id.title_tv);
            viewHolder.content = (TextView) view.findViewById(R.id.content_tv);
            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) view.getTag();
        }



        //*&*设置标签，避免快速滑动listview出现位置误差
        viewHolder.title.setText(newsBeanList.get(i).title);
        viewHolder.content.setText(newsBeanList.get(i).content);
        viewHolder.numberid.setText(String.valueOf(i+1));
        return view;
    }
    //*&*优化
    class ViewHolder {
        public TextView title;
        public TextView content;
        public TextView numberid;
    }
}