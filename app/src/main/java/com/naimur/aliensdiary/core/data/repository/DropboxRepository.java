package com.naimur.aliensdiary.core.data.repository;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetThumbnailBatchResult;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.PathOrLink;
import com.dropbox.core.v2.files.ThumbnailArg;
import com.naimur.aliensdiary.core.listener.RequestListener;
import com.naimur.aliensdiary.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class DropboxRepository {

    private static DbxClientV2 client;

    private static final String ACCESS_TOKEN = "";

    public static DbxClientV2 getClient() {
        if (client != null) return client;

        DbxRequestConfig config = DbxRequestConfig.newBuilder("Alien's Diary/1.0").build();
        return new DbxClientV2(config, ACCESS_TOKEN);
    }

    public static void fetchPhotos(RequestListener<List<Metadata>> listener) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ListFolderResult result = getClient().files().listFolder("/" + Utils.getBasePath() + "/" + "photos");
                    while (true) {
                        if (!result.getHasMore()) {
                            break;
                        }

                        result = getClient().files().listFolderContinue(result.getCursor());
                    }
                    listener.onSuccess(result.getEntries());
                } catch (DbxException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                }
            }
        });
    }
}
