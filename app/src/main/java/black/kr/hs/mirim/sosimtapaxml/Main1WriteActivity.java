package black.kr.hs.mirim.sosimtapaxml;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Main1WriteActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore;
    private EditText mWriteTitleText;
    private EditText mWriteContentText;
    private EditText mWriteNameText;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("글 올리기");
        setContentView(R.layout.activity_main1_write);

        mStore = FirebaseFirestore.getInstance();

        mWriteTitleText = findViewById(R.id.write_title_text);
        mWriteContentText = findViewById(R.id.write_contents_text);
        mWriteNameText = findViewById(R.id.write_name_text);

        findViewById(R.id.write_upload).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        id = mStore.collection("community").document().getId();
        Map<String,Object> post = new HashMap<>();
        post.put("id",id);
        post.put("title",mWriteTitleText.getText().toString());
        post.put("contents",mWriteContentText.getText().toString());
        post.put("name",mWriteNameText.getText().toString());

        mStore.collection("community").document(id)
                .set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Main1WriteActivity.this,"업로드 성공!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
              .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Main1WriteActivity.this,"업로드 실패",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
