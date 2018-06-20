package com.example.abe.mobiletrabalho.danger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.R;

public class DangerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DangerAdapter dangerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_danger);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dangerAdapter = new DangerAdapter(100);
        recyclerView.setAdapter(dangerAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            // COMPLETED (4) Override onMove and simply return false inside
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //do nothing, we only care about swiping
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                /*Acho a posição do rapaz que está "onSwip"*/
                int id = (viewHolder.getAdapterPosition());
                /*Com a posição em mãos, acho as views correspondentes, assim como os seus respectivos textos*/
                final TextView v = layoutManager.findViewByPosition(id).findViewById(R.id.itens_list);

                    if(swipeDir == ItemTouchHelper.LEFT ){
                        v.setTextColor(0xFF000000);
                    }

                Log.d("Msg", v.getText().toString());
                /*Faz o RecyclerView retornar ao estado inicial!*/
              //  dangerAdapter.notifyItemRemoved(id);
               // dangerAdapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(recyclerView);
    }
}
