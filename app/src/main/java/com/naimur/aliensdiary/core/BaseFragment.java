package com.naimur.aliensdiary.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<ViewModel extends BaseViewModel> extends Fragment {

    private ViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = (ViewModel) new ViewModelProvider(requireActivity()).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container);
    }

    public abstract int getLayoutId();

    public ViewModel getViewModel() {
        return viewModel;
    }

    private Class<ViewModel> getViewModelClass() {
        return (Class<ViewModel>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public <T extends View> T findViewById(int id){
        if(getView() == null) return null;
        return getView().findViewById(id);
    }

    public void showToast(CharSequence charSequence){
        Toast.makeText(requireActivity(), charSequence, Toast.LENGTH_SHORT).show();
    }
}
