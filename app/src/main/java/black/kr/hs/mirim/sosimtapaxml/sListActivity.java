package black.kr.hs.mirim.sosimtapaxml;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class sListActivity extends AppCompatActivity {

    private static final String TAG = "FireLog";
    private RecyclerView sList;
    private FirebaseFirestore userDB;
    private scriptListAdapter scriptListAdapter;

    private List<Scripts> scriptsList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_list);

        scriptsList = new ArrayList<>();
        scriptListAdapter = new scriptListAdapter(scriptsList);

        sList = (RecyclerView) findViewById(R.id.sRecyclerList);
        sList.setHasFixedSize(true);
        sList.setLayoutManager(new LinearLayoutManager(this));
        sList.setAdapter(scriptListAdapter);

        userDB = FirebaseFirestore.getInstance();

        userDB.collection("script").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@NonNull QuerySnapshot documentSnapshots, @NonNull FirebaseFirestoreException e) {
                if(e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }

                for(DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if(doc.getType() == DocumentChange.Type.ADDED) {


                        Scripts scripts = doc.getDocument().toObject(Scripts.class);
                        scriptsList.add(scripts);

                        scriptListAdapter.notifyDataSetChanged();
                    }

                }
            }
        });


    }
}
