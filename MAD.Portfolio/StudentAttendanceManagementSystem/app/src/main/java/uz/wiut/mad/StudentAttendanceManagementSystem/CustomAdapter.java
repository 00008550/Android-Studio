package uz.wiut.mad.StudentAttendanceManagementSystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList user_id, user_date, user_module, user_id_id;

    CustomAdapter(Activity activity, Context context, ArrayList user_id, ArrayList user_date, ArrayList user_module,
                  ArrayList user_id_id){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_date = user_date;
        this.user_module = user_module;
        this.user_id_id = user_id_id;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_date_txt.setText(String.valueOf(user_date.get(position)));
        holder.user_module_txt.setText(String.valueOf(user_module.get(position)));
        holder.user_id_id_txt.setText(String.valueOf(user_id_id.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id_id", String.valueOf(user_id_id.get(position)));
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("date", String.valueOf(user_date.get(position)));
                intent.putExtra("module", String.valueOf(user_module.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return user_id_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_date_txt, user_module_txt, user_id_id_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            user_date_txt = itemView.findViewById(R.id.user_date_txt);
            user_module_txt = itemView.findViewById(R.id.user_module_txt);
            user_id_id_txt = itemView.findViewById(R.id.user_id_id_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
