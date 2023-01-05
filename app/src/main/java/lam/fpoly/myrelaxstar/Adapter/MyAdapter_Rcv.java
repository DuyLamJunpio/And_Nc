package lam.fpoly.myrelaxstar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.myrelaxstar.Object.TinTuc;
import lam.fpoly.myrelaxstar.R;

public class MyAdapter_Rcv extends RecyclerView.Adapter<MyAdapter_Rcv.RecyclerViewHolder>{
    private List<TinTuc> list;
    private InterClickItemData interClickItemData;

    public void setData(List<TinTuc> data){
        this.list = data;
        notifyDataSetChanged();
    }

    public MyAdapter_Rcv(InterClickItemData interClickItemData) {
        this.interClickItemData = interClickItemData;
    }

    public interface InterClickItemData {
        void clickItemData(TinTuc tinTuc);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layoutitem_news,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final TinTuc tinTuc = list.get(position);
        if(tinTuc == null){
            return;
        }
        holder.tvItemTitle.setText(tinTuc.getTitle());
        holder.tvItemContent.setText(tinTuc.getDescription());
        Picasso.get().load(tinTuc.getImg()).fit().into(holder.imgItem);
        holder.idLayoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interClickItemData.clickItemData(tinTuc);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvItemTitle,tvItemContent;
        private ImageView imgItem;
        private LinearLayout idLayoutitem;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
            tvItemContent = itemView.findViewById(R.id.tvItemContent);
            imgItem = itemView.findViewById(R.id.imgItem);
            idLayoutitem = itemView.findViewById(R.id.idLayoutitem);
        }
    }
}
