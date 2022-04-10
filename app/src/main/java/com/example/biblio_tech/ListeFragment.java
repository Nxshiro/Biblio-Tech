package com.example.biblio_tech;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ListeFragment extends Fragment implements View.OnClickListener{

    private Callback m_callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_detail_liste,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    public interface Callback {
        public void goTo(View v);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        m_callback = (Callback) context;
    }


    @Override
    public void onClick(View v) {
        m_callback.goTo(v);
    }


}
