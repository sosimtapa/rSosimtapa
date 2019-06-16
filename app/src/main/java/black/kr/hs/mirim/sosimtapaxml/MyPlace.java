package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MyPlace extends AppCompatActivity {

    private FirebaseFirestore pStore = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private List<pBoard> pBoardList;
    private PlaceAdapter mAdapter;

    private View view;
    int eixitplace = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_place);
        Toolbar toolbar = findViewById(R.id.toolbar);
        view = findViewById(R.id.view);

        if (Build.VERSION.SDK_INT >= 21) {      // 21 버전 이상일 경우
            getWindow().setStatusBarColor(Color.rgb(255,109,112));
        }

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.edit_place);
        Intent it = getIntent();
        final String userID = it.getStringExtra("userID");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPlace.this, MyPlaceWrite.class);
                intent.putExtra("userID",userID);
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.place_recycler_view);
        pBoardList = new ArrayList<>();

        pStore.collection("myplace")                //userID가 같은 것만 찾아서 보여줌
                .whereEqualTo("userID", userID)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    eixitplace = 1;
                    for(QueryDocumentSnapshot dc: task.getResult()){
                        String id = (String) dc.getData().get("userID");
                        String editAddress = (String)dc.getData().get("userEditAddress");
                        String address = (String) dc.getData().get("address");
                        String content = (String) dc.getData().get("content");
                        pBoard data = new pBoard(id,editAddress,address,content);
                        pBoardList.add(data);
                    }
                    mAdapter = new MyPlace.PlaceAdapter(pBoardList);
                    recyclerView.setAdapter(mAdapter);

                } else {
                    Log.w("음..","이건 task자체 실패시");
                }
            }

        });

        if(eixitplace == 0){
            Log.w ("데이터가없음", Integer.toString(eixitplace));
            final Snackbar snackbar = Snackbar.make(view,"저장된 장소가 없습니다. 등록버튼을 눌러 추가해 보세요",Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("등록",new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyPlace.this, MyPlaceWrite.class);
                    intent.putExtra("userID",userID);
                    startActivity(intent);
                }
            });
            snackbar.show();

            /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*/
        }else{
            Log.w("데이터가있음", Integer.toString(eixitplace));
        }

    }

    private class PlaceAdapter extends RecyclerView.Adapter<MyPlace.PlaceAdapter.PlaceViewHolder>{

        private List<pBoard> pBoardList;

        public PlaceAdapter(List<pBoard> pBoardList) {
            this.pBoardList=pBoardList;
        }

        @NonNull
        @Override
        public MyPlace.PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyPlace.PlaceAdapter.PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyPlace.PlaceAdapter.PlaceViewHolder holder, int position) {
            pBoard data = pBoardList.get(position);

            holder.eadTextView.setText(data.getUserEditAddress());
            holder.adTextView.setText(data.getAddress());
            holder.coTextView.setText(data.getContent());

            // 이미지 holder 추가
            // Drawable drawable= context.getResources().getDrawable(data.getImg());
            //   holder.mImgTextView.setBackground(drawable);
            //   Glide.with(holder.itemView.getContext()).load(data.getImg()).into(holder.mImgTextView);

        }

        @Override
        public int getItemCount() {
            return pBoardList.size();
        }

        class PlaceViewHolder extends RecyclerView.ViewHolder{

            private  TextView eadTextView;
            private TextView adTextView;
            private TextView coTextView;
           // private ImageView mImgTextView;

            public PlaceViewHolder(View itemView) {
                super(itemView);

                eadTextView = itemView.findViewById(R.id.item_place_eloc);
                adTextView = itemView.findViewById(R.id.item_place_loc);
                coTextView = itemView.findViewById(R.id.item_place_text);
                //이미지
                //   mImgTextView = findViewById(R.id.item_main_img);
            }
        }
    }

}
