package test.bwie.com.firstdaydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
* date: 2017/4/7.
        * author: 王艺凯 (lenovo )
        * function:
        */

public class MyBaseAdapter extends BaseAdapter {
    private Bean mBean;
    private List<AddressBean> lab;
    private Context mContext;

    public MyBaseAdapter(List<AddressBean> lab, Context context) {
        this.lab = lab;
        mContext = context;
    }

    @Override
    public int getCount() {
        return lab.size();
    }

    @Override
    public Object getItem(int position) {
        return lab.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_neirong, null);
            vh = new ViewHolder();
            vh.address = (TextView) convertView.findViewById(R.id.address);
            vh.addressname = (TextView) convertView.findViewById(R.id.addressname);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.address.setText(lab.get(position).getAddress());
        vh.addressname.setText(lab.get(position).getAddressname());
        return convertView;
    }

    class ViewHolder {
        TextView addressname, address;

    }
}
