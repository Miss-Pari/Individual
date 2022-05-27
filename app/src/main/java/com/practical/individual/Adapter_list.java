package com.practical.individual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.practical.individual.Minerals;
import com.practical.individual.R;

import java.util.List;

public class Adapter_list extends ArrayAdapter<Minerals> {

    private Activity context;
    private List<Minerals> mineralsList;

    public Adapter_list(Activity context, List<Minerals> mineralsList) {
        super(context, R.layout.view_layout, mineralsList);
        this.context = context;
        this.mineralsList = mineralsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.view_layout, null, true);
        TextView english2 = (TextView) listViewItem.findViewById(R.id.english1);
        TextView konkani2 = (TextView) listViewItem.findViewById(R.id.konkani1);
        TextView pro2 = (TextView) listViewItem.findViewById(R.id.pro1);

        Minerals minerals = mineralsList.get(position);
        english2.setText(minerals.getEnglish());
        konkani2.setText(minerals.getKonkani());
        pro2.setText(minerals.getPro());

        return listViewItem;
    }
}
