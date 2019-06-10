package black.kr.hs.mirim.sosimtapaxml;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    public void setTitle(int titleId) {
        super.setTitle("            소심타파");
    }

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView recyclerView;
    private List<Board> mBoardList;
    private MainAdapter mAdapter;

    Button main1_community;
    Button main2_audio;
    Button main3_script;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("              소심타파");

        setContentView(R.layout.activity_main1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //상위 버튼
        main1_community=findViewById(R.id.btn_top1);
        main2_audio=findViewById(R.id.btn_top2);
        main3_script=findViewById(R.id.btn_top3);

        Intent gi = getIntent();
        userID = gi.getStringExtra("userID");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_top1:
                        Intent intent1 = new Intent(getApplicationContext(),MainActivity1.class);
                        startActivity(intent1);
                        break;
                    case R.id.btn_top2:
                        Intent intent2= new Intent(getApplicationContext(),Main3Audio.class);
                        startActivity(intent2);
                        break;
                    case R.id.btn_top3:
                        Intent intent3= new Intent(getApplicationContext(),scriptFlipImage.class);
                        startActivity(intent3);
                        break;
                }
            }
        };

        main2_audio.setOnClickListener(listener);
        main3_script.setOnClickListener(listener);

        //우측하단 글쓰기 버튼
        findViewById(R.id.main_write_button).setOnClickListener(this);
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_write_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity1.this, Main1WriteActivity.class);
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //myNew code here
        recyclerView = findViewById(R.id.main_recycler_view);

        mBoardList = new ArrayList<>();
        mStore.collection("community")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()){
                    String id = (String)dc.getDocument().get("id");
                    String title = (String) dc.getDocument().get("title");
                    String contents = (String) dc.getDocument().get("contents");
                    String name= (String) dc.getDocument().getData().get("name");
                    Board data = new Board(name,title,contents,id);
                    mBoardList.add(data);
                }
                mAdapter = new MainAdapter(mBoardList);
                recyclerView.setAdapter(mAdapter);
            }
        });

    }   // onCreate

    @Override
    public void onClick(View v) {

    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

        private List<Board> mBoardList;

        public MainAdapter(List<Board> mBoardList) {
            this.mBoardList=mBoardList;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            Board data = mBoardList.get(position);
            holder.mTextTextView.setText(data.getContents());
            holder.mNameTextView.setText(data.getId());
            holder.mTitleTextView.setText(data.getTitle());

            // 이미지 holder 추가
           // Drawable drawable= context.getResources().getDrawable(data.getImg());
         //   holder.mImgTextView.setBackground(drawable);
         //   Glide.with(holder.itemView.getContext()).load(data.getImg()).into(holder.mImgTextView);

        }

        @Override
        public int getItemCount() {
            return mBoardList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder{

            private TextView mTitleTextView;
            private TextView mNameTextView;
            private TextView mTextTextView;
            private ImageView mImgTextView;

            public MainViewHolder(View itemView) {
                super(itemView);

                mNameTextView = itemView.findViewById(R.id.item_main_name);
                mTitleTextView = itemView.findViewById(R.id.item_main_title);
                mTextTextView = itemView.findViewById(R.id.item_main_text);
             //이미지
                //   mImgTextView = findViewById(R.id.item_main_img);
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_place) {
            Intent intent_m = new Intent(getApplicationContext(),MyPlace.class);
            intent_m.putExtra("userID",userID);
            startActivity(intent_m);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
