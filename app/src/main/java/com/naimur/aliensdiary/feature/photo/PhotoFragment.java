package com.naimur.aliensdiary.feature.photo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.naimur.aliensdiary.R;
import com.naimur.aliensdiary.core.BaseFragment;
import com.naimur.aliensdiary.core.data.repository.DropboxRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Executors;

public class PhotoFragment extends BaseFragment<PhotoViewModel> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViewModel().getLoader().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                //findViewById(R.id.progressIndicator).setVisibility(bool.booleanValue() ? View.VISIBLE : View.GONE);
            }
        });

        getViewModel().getPhotosMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Metadata>>() {
            @Override
            public void onChanged(List<Metadata> metadata) {
                if (metadata != null) {
                    ex(metadata);
                } else Log.e(PhotoFragment.class.getName(), "Metadata list is null");
            }
        });
        getViewModel().fetchPhotos();
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_photoview;
    }

    private void ex(List<Metadata> metadata) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                DbxDownloader<FileMetadata> downloader = null;
                try {
                    downloader = DropboxRepository.getClient().files().download(metadata.get(0).getPathLower());
                } catch (DbxException e) {
                    e.printStackTrace();
                }

                OutputStream outputStream = null;

                try {
                    outputStream = new ByteArrayOutputStream();
                    downloader.download(outputStream);
                } catch (DbxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (outputStream == null) {
                    Log.e(PhotoFragment.class.getName(), "outputStream == null");
                    return;
                }

                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.writeTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (byteArrayOutputStream == null) {
                    Log.e(PhotoFragment.class.getName(), "byteArrayOutputStream == null");
                    return;
                }

                ByteArrayOutputStream finalByteArrayOutputStream = byteArrayOutputStream;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(requireActivity())
                                .load(finalByteArrayOutputStream.toByteArray())
                                .into((AppCompatImageView) findViewById(R.id.imageView))
                        ;
                    }
                });

            }
        });
    }

    private void show(String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(requireActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
