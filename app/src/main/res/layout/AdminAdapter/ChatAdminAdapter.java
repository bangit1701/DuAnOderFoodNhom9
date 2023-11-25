package ph29152.fptpoly.duanoderfoodnhom1.AdminAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph29152.fptpoly.duanoderfoodnhom1.Activity.Admin.ChatAdminActivity;
import ph29152.fptpoly.duanoderfoodnhom1.Adapter.ChatAdapter;
import ph29152.fptpoly.duanoderfoodnhom1.Model.Messenger;
import ph29152.fptpoly.duanoderfoodnhom1.Model.UserSession;
import ph29152.fptpoly.duanoderfoodnhom1.R;

public class ChatAdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Messenger> list = new ArrayList<>();
    Context context;

    public ChatAdminAdapter(List<Messenger> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (list.get(viewType).getIdUser() == 1) { // Nếu idUser là 1 (dao voi user)
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_user, parent, false);
            return new UserHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_admin, parent, false);
            return new AdminHolder(view);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messenger messages  =list.get(position);
        if (holder.getClass()== UserHolder.class && messages.getIdUser()==1){
            UserHolder viewHolder = (UserHolder) holder;
            viewHolder.tvMessUser.setText(messages.getNoiDung());
            viewHolder.tvTenUser.setText("Yummi Food");
            viewHolder.imgAvatarUser.setImageResource(R.drawable.sold_out);
        }else {
            AdminHolder viewHolder = (AdminHolder) holder;
            viewHolder.tvMessAdmin.setText(messages.getNoiDung());
            viewHolder.tvTenAdmin.setText(ChatAdminActivity.nameUser);
            if(ChatAdminActivity.avtUser==null){
                viewHolder.imgAvatarAdmin.setImageResource(R.drawable.avtuser);
            }else{
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPreferredConfig = Bitmap.Config.RGBA_F16;
                opts.inMutable = true;
                byte[] decodedString = Base64.decode(ChatAdminActivity.avtUser, Base64.DEFAULT);
                Bitmap myBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length, opts);
//            myBitmap.setHasAlpha(true);
                viewHolder.imgAvatarAdmin.setImageBitmap(myBitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UserHolder extends RecyclerView.ViewHolder{
        ImageView imgAvatarUser;
        TextView tvTenUser, tvMessUser;
        ConstraintLayout itemMessUser;
        CardView cardAvatarUser;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarUser =itemView.findViewById(R.id.imgAvatarAdmin);
            tvTenUser =itemView.findViewById(R.id.tvTenUserMessAdmin);
            tvMessUser =itemView.findViewById(R.id.tvMessAdmin);
            itemMessUser =itemView.findViewById(R.id.itemChatAdmin);
            cardAvatarUser =itemView.findViewById(R.id.cardAvatarAdmin);

        }
    }
    public class AdminHolder extends RecyclerView.ViewHolder{
        ImageView imgAvatarAdmin;
        TextView tvTenAdmin, tvMessAdmin;
        ConstraintLayout itemMessAdmin;
        CardView cardAvatarAdmin;
        public AdminHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatarAdmin =itemView.findViewById(R.id.imgAvatarU);
            tvTenAdmin =itemView.findViewById(R.id.tvTenUserMessU);
            tvMessAdmin =itemView.findViewById(R.id.tvMessU);
            itemMessAdmin = itemView.findViewById(R.id.itemChatU);
            cardAvatarAdmin = itemView.findViewById(R.id.cardAvatarU);
        }
    }
}
