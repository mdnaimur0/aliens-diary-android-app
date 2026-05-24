package com.naimur.aliensdiary.util;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.ListFilesResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DropboxUtils {

    private DbxClientV2 client;

    private static final String ACCESS_TOKEN = "sl.BAT3mWheOhT5uhaaCoc1V_XPrO34jw3pfW5iZyWnfdhvJg8uyDELvkloRfdaH3Z_jxQ_ItfVKP1XhGLjk9jzcn8x3k9NvQk85eJPlDip_CmI7mkOCOWICZrRRIEkVIWmYFpeVm4";

    public DropboxUtils(DbxClientV2 client) {
        this.client = client;
    }

    public static DbxClientV2 getClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("Alien's Diary/1.0").build();
        return new DbxClientV2(config, ACCESS_TOKEN);
    }

    public ListFolderResult getListFolder(String path) throws DbxException {
        ListFolderResult result = client.files().listFolder(path);
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        return result;
    }

    public void uploadFile(File file) throws Exception {
        try (InputStream in = new FileInputStream(file)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + Utils.getBasePath() + "/" + file.getName())
                    .uploadAndFinish(in);
        }
    }
}
