package black.kr.hs.mirim.sosimtapaxml;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class scriptListAdapter extends RecyclerView.Adapter<scriptListAdapter.ViewHolder> {

    public List<Scripts> scriptsList;

    public scriptListAdapter(List<Scripts> scriptsList) {
        this.scriptsList = scriptsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.writerText.setText(scriptsList.get(position).getWriter());
        holder.contentText.setText(scriptsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {

        return scriptsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public TextView writerText;
        public TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            writerText = (TextView) mView.findViewById(R.id.script_writer);
            contentText = (TextView) mView.findViewById(R.id.script_content);
        }

    }

    public void updateList(List<Scripts> newList) {
        scriptsList = new ArrayList<>();
        scriptsList.addAll(newList);
        notifyDataSetChanged();

    }


}
