package com.example.zendi_application.dropFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zendi_application.R;

public class DropFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drops, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_type)
        {
            Toast.makeText(this.getContext(),"CAI CC BA M",Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.btn_type1)
        {
            Toast.makeText(this.getContext(),"CAI CC BA M",Toast.LENGTH_SHORT).show();
        }
    }
}
