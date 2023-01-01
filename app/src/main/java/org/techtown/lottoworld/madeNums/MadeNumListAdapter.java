package org.techtown.lottoworld.madeNums;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.techtown.lottoworld.MadeNumQuery;
import org.techtown.lottoworld.NumberQuery;
import org.techtown.lottoworld.R;

import java.util.ArrayList;

public class MadeNumListAdapter extends RecyclerView.Adapter<MadeNumListAdapter.ViewHolder> {
    ArrayList<MadeNumQuery> items = new ArrayList<MadeNumQuery>();
    // view type
    private int TYPE_STICKER = 201;
    private int TYPE_LIST = 202;


    @NonNull
    @Override
    public MadeNumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(getViewScr(viewType), parent,false);

        return new MadeNumListAdapter.ViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MadeNumListAdapter.ViewHolder holder, int position) {
        MadeNumQuery item = items.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MadeNumQuery item) {
        items.add(item);
    }

    public void setItems(ArrayList<MadeNumQuery> items) {
        this.items = items;
    }

    public MadeNumQuery getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, MadeNumQuery item) {
        items.set(position, item);
    }

    private int getViewScr(int viewType){
        if(viewType == TYPE_STICKER){
            return R.layout.made_num_date;
        }else{
            return R.layout.made_num_item;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getId() == -1){
            return TYPE_STICKER;
        } else {
            return TYPE_LIST;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private int viewType;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
        }

        public void bind(MadeNumQuery item){
            if (viewType==TYPE_STICKER){
                Log.d("MadeNum ,TYPE_STICKER",item.toString());
                bindSticker(item);
            } else if(viewType==TYPE_LIST) {
                Log.d("MadeNum, TYPE_LIST",item.toString());
                bindItem(item);
            }
        }
        private void bindSticker(MadeNumQuery item){
            TextView madeDate = itemView.findViewById(R.id.madeDate);
            madeDate.setText(item.getDate());
        }
        private void bindItem(MadeNumQuery item){
            String staticT = "총합:" + item.getTotal() + " 짝홀:" + item.getEven() + "/" +  (6 - item.getEven());

            TextView nums = itemView.findViewById(R.id.nums);
            nums.setText(item.numberString());
            TextView statics = itemView.findViewById(R.id.staticsMade);
            statics.setText(staticT);

        }
    }
}
