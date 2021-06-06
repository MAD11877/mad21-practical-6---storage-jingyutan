package sg.edu.np.w6_practical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    ArrayList<User> list_users;
    private RecyclerViewClickListener listener;


    public UserAdapter(ArrayList<User> input,
                       RecyclerViewClickListener listener){
        this.list_users = input;
        this.listener = listener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        TextView name;
        TextView desc;
        ImageView image;

        public UserViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.DisplayUsername);
            desc = itemView.findViewById(R.id.DisplayDescription);
            image = itemView.findViewById(R.id.imageView);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }


    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_layout, parent,false
        );
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int pos) {
        User list_items = list_users.get(pos);
        holder.name.setText(list_items.getName());
        holder.desc.setText(list_items.getDescription());
    }

    public int getItemCount() {return list_users.size();}

    public interface RecyclerViewClickListener {
        void onClick(View v, int pos);
    }

}
