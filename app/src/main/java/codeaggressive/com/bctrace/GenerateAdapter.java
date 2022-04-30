package codeaggressive.com.bctrace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GenerateAdapter extends ArrayAdapter<Datas> {

    Context context;
    List<Datas> arrayDataList;

    public GenerateAdapter(@NonNull Context context, List<Datas> arrayDataList) {
        super(context, R.layout.data_selected_item, arrayDataList);

        this.context = context;
        this.arrayDataList = arrayDataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_selected_item, null, true);
        TextView dataText = view.findViewById(R.id.data_text);

        dataText.setText(arrayDataList.get(position).getBatch());
        return view;
    }
}
