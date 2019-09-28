package com.example.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Chat =modelClassList.get(position).getName();
        String Message = modelClassList.get(position).getMessage();
        String Time = modelClassList.get(position).getTime();
        holder.setData(Chat,Message,Time);
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView chat;
        private TextView message;
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chat= itemView.findViewById(R.id.tv_Chat);
            message=itemView.findViewById(R.id.tv_Message);
            time=itemView.findViewById(R.id.tv_Time);
        }
        public void setData(String Chat1,String Message1,String Time1)
        {
            chat.setText(Chat1);
            message.setText(Message1);
            time.setText(Time1);
        }
    }
    public void AddData(List<ModelClass> chats)
    {
        modelClassList.addAll(chats);
        notifyDataSetChanged();
    }

}
