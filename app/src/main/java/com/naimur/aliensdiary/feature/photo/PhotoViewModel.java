package com.naimur.aliensdiary.feature.photo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.naimur.aliensdiary.core.BaseViewModel;
import com.naimur.aliensdiary.core.data.repository.DropboxRepository;
import com.naimur.aliensdiary.core.listener.RequestListener;

import java.util.List;

public class PhotoViewModel extends BaseViewModel {

    private MutableLiveData<List<Metadata>> photosMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Metadata>> getPhotosMutableLiveData() {
        return photosMutableLiveData;
    }

    public PhotoViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchPhotos() {
        getLoader().postValue(Boolean.TRUE);
        DropboxRepository.fetchPhotos(new RequestListener<List<Metadata>>() {

            @Override
            public void onSuccess(List<Metadata> result) {
                getLoader().postValue(Boolean.FALSE);
                photosMutableLiveData.postValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                getLoader().postValue(Boolean.FALSE);
            }
        });
    }
}
