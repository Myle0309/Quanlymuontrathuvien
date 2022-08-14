package com.example.dell.QuanLyMuonTraThuVien.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.dao.MuonSachDao;
import com.example.dell.QuanLyMuonTraThuVien.model.MuonSach;

import java.util.List;

public class MuonSachAdapter extends BaseAdapter {
    List<MuonSach> muonSachList;
    public Activity context;
    public LayoutInflater inflater;
    MuonSachDao muonSachDao;
    private View convertView;
    private int position;

    public MuonSachAdapter(List<MuonSach> muonSachList, Activity context) {
        super();
        this.muonSachList = muonSachList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        muonSachDao = new MuonSachDao(context);
    }

    @Override
    public int getCount() { return 0; }

    @Override
    public Object getItem(int i) { return null; }

    @Override
    public long getItemId(int i) { return 0; }

    public static class ViewHolder{
        ImageView img, imgdelete;
        TextView tvmahoadon,tvngayhoadon;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MuonSachAdapter.ViewHolder holder;
        if (convertView==null){
            holder= new MuonSachAdapter.ViewHolder();
            convertView=inflater.inflate(R.layout.custommuonsach,null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgavatartheloai);
            holder.tvmahoadon = (TextView) convertView.findViewById(R.id.tvmahoadon);
            holder.tvngayhoadon = (TextView) convertView.findViewById(R.id.tvngayhoadon);
            holder.imgdelete = (ImageView) convertView.findViewById(R.id.imgdeletetheloai);
            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    muonSachDao.deleteMuonSach(muonSachList.get(position).getMaMuonSach());
                    muonSachList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }else

            holder=(MuonSachAdapter.ViewHolder)convertView.getTag();
        MuonSach entry =(MuonSach)muonSachList.get(position);
        holder.img.setImageResource(R.drawable.cateicon);
        holder.tvmahoadon.setText(entry.getMaMuonSach());
        holder.tvngayhoadon.setText(entry.getNgayMuon());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<MuonSach> items){
        this.muonSachList=items;
        notifyDataSetChanged();
    }
}
