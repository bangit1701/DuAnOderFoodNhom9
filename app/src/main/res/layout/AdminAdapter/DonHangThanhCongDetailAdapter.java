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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph29152.fptpoly.duanoderfoodnhom1.Model.DanhSachSanPhamBiHuy;
import ph29152.fptpoly.duanoderfoodnhom1.Model.DanhSachSanPhamThanhCong;
import ph29152.fptpoly.duanoderfoodnhom1.R;

public class DonHangThanhCongDetailAdapter extends RecyclerView.Adapter<DonHangThanhCongDetailAdapter.MyViewHolder>{

    List<DanhSachSanPhamThanhCong> list = new ArrayList<>();
    Context context;

    public DonHangThanhCongDetailAdapter(List<DanhSachSanPhamThanhCong> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay,parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DanhSachSanPhamThanhCong danhSachSanPhamThanhCong= list.get(position);
        holder.tvTenSanPhamPay.setText(""+danhSachSanPhamThanhCong.getTenSp());
        holder.tvGiaTienPay.setText(""+danhSachSanPhamThanhCong.getDonGia());
        holder.tvSoLuongPay.setText(""+danhSachSanPhamThanhCong.getSoLuong());
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGBA_F16;
        opts.inMutable = true;
        byte[] decodedString = Base64.decode(danhSachSanPhamThanhCong.getAnhSp(),Base64.DEFAULT);
        Bitmap myBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,opts);
        myBitmap.setHasAlpha(true);
        holder.imgAnhSanPhamPay.setImageBitmap(myBitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAnhSanPhamPay;
        private TextView tvTenSanPhamPay,tvSoLuongPay, tvGiaTienPay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhSanPhamPay=itemView.findViewById(R.id.imgAnhSanPhamPay);
            tvTenSanPhamPay=itemView.findViewById(R.id.tvTenSanPhamPay);
            tvSoLuongPay=itemView.findViewById(R.id.tvSoLuongPayed);
            tvGiaTienPay=itemView.findViewById(R.id.tvGiaTienPayed);
        }
    }
}
