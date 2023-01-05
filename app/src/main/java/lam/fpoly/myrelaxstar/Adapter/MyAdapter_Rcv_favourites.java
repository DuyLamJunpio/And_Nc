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
import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.R;

public class MyAdapter_Rcv_favourites extends RecyclerView.Adapter<MyAdapter_Rcv_favourites.RecyclerViewHolder>{
    private List<TinTucRoom> list;
    private InterClickItemData interClickItemData;

    public void setData(List<TinTucRoom> data){
        this.list = data;
        notifyDataSetChanged();
    }

    public MyAdapter_Rcv_favourites(InterClickItemData interClickItemData) {
        this.interClickItemData = interClickItemData;
    }

    public interface InterClickItemData {
        void clickItemData(TinTucRoom tinTucRoom);
        void delete(TinTucRoom tinTucRoom);
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
        final TinTucRoom tinTucRoom = list.get(position);
        if(tinTucRoom == null){
            return;
        }
        holder.tvItemTitle.setText(tinTucRoom.getTitle());
        holder.tvItemContent.setText(tinTucRoom.getDescription());
        Picasso.get().load(tinTucRoom.getImg()).fit().into(holder.imgItem);
        holder.idLayoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interClickItemData.clickItemData(tinTucRoom);
            }
        });
        holder.idLayoutitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                interClickItemData.delete(tinTucRoom);
                return false;
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
