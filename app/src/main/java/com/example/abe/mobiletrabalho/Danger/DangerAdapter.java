package com.example.abe.mobiletrabalho.danger;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.R;

import java.util.List;
import java.util.Random;

public class DangerAdapter extends RecyclerView.Adapter<DangerAdapter.DangerViewHolder> {
    private int numberItens;
    private List<ImageClass> imageList;
    private Context appContext;
    private Resources resource;

    public DangerAdapter(int numberItens, List<ImageClass> imageList, Context appContext, Resources resource){
        this.numberItens = numberItens;
        this.imageList = imageList;
        this.appContext = appContext;
        this.resource = resource;
    }

    @Override
    public DangerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.list_itens;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachImmediately = false;

        View view = inflater.inflate(layoutId, parent, attachImmediately);
        DangerViewHolder viewHolder = new DangerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DangerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.numberItens;
    }

    class DangerViewHolder extends RecyclerView.ViewHolder{
        TextView nameItem, secureText, dangerText;
        ImageView imageDanger;

        public DangerViewHolder(final View itemView) {
            super(itemView);
            imageDanger = itemView.findViewById(R.id.imageDanger);
            nameItem = itemView.findViewById(R.id.textViewObjctName);
            dangerText = itemView.findViewById(R.id.textViewPerigoso);
            secureText = itemView.findViewById(R.id.textViewSeguro);
        }

        public void bind(final int position){
            String str = "danger_image" + String.valueOf(position);
            imageDanger.setImageDrawable(
                    resource.getDrawable(getResourceID(str, "drawable", appContext)));

            nameItem.setText(imageList.get(position).getDescription());

            dangerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position%2 == 0){
                        Log.d("Apertou", "Errou D");
                        imageDanger.setImageResource(R.drawable.errou_image);
                    } else {
                        Log.d("Apertou", "Acertou D");
                        imageDanger.setImageResource(R.drawable.acertou_image);
                    }
                }
            });

            secureText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position%2 == 0){
                        Log.d("Apertou", "Acertou S");
                        imageDanger.setImageResource(R.drawable.acertou_image);
                    } else {
                        Log.d("Apertou", "Errou S");
                        imageDanger.setImageResource(R.drawable.errou_image);
                    }
                }
            });

        }

        /*Gero o ID das imagens a partir do número randômico.*/
        protected final int getResourceID (final String resName, final String resType, final Context ctx)
        {
            final int ResourceID =
                    ctx.getResources().getIdentifier(resName, resType,
                            ctx.getApplicationInfo().packageName);
            if (ResourceID == 0)
            {
                throw new IllegalArgumentException
                        (
                                "No resource string found with name " + resName
                        );
            }
            else
            {
                return ResourceID;
            }
        }
    }
}
