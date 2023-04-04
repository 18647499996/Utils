package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/4/23
 */
public class ADContentProviderUtils {

    public static final String[] PROJECTION_FILE = new String[]{
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    public static final String[] PROJECTION_IMAGE = new String[]{
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    public static final String[] PROJECTION_VIDEO = new String[]{
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    public static final String VIDEO_ORDER_BY = MediaStore.Video.Media.DISPLAY_NAME;

    public static final String AUDIO_ORDER_BY = MediaStore.Audio.Media.DISPLAY_NAME;

    public static final String IMAGE_ORDER_BY = MediaStore.Images.Media.DISPLAY_NAME;

    public static final String FILE_ORDER_BY = MediaStore.Files.FileColumns.DATE_MODIFIED;

    public static final Uri FILE_URI = MediaStore.Files.getContentUri("external");

    public static final Uri VIDEO_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    public static final Uri AUDIO_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    public static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    public static final String DESC = "desc";

    public static final String AES = "aes";

    public static final String SELECTION_ONE = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String SELECTION_TWO = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

    public static final String[] SELECTION_IMAGE = new String[]{"image/jpeg", "image/png"};

    public static final String[] SELECTION_VIDEO = new String[]{"video/mp4"};

    public static final String[] SELECTION_TXT = new String[]{"text/plain"};

    public static final String[] SELECTION_DOC = new String[]{"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};

    public static final String[] SELECTION_PDF = new String[]{"application/pdf"};

    public static final String[] SELECTION_PPT = new String[]{"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};

    public static final String[] SELECTION_XLS = new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};


    private static volatile ADContentProviderUtils instance = null;

    private ADContentProviderUtils() {
    }

    public static ADContentProviderUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADContentProviderUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADContentProviderUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取内容提供者文件列表
     *
     * @param context       上下文
     * @param contentType   文件类型
     * @param uri           查询uri
     * @param projections   查询字段
     * @param orderBy       排序
     * @param selection     查询条件
     * @param selectionArgs 查询类型
     * @param sort          升降序
     * @return List<ADFileModel>
     */
    @SuppressLint("Range")
    public List<ADFileModel> getContentProviderList(Context context, ContentType contentType, Uri uri, String[] projections, String orderBy, String selection, String[] selectionArgs, String sort) {
        List<ADFileModel> adFileModelList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(uri, projections, selection, selectionArgs, orderBy + " " + sort);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (ADFileUtils.getInstance().scannerFile(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)))) {
                    adFileModelList.add(new ADFileModel(
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                            contentType));
                }
            }
            cursor.close();
            return adFileModelList;
        }
        return adFileModelList;
    }

    public static class ADFileModel {
        private String id;
        private String filePath;
        private String fileName;
        private String size;
        private String date;
        private String thumbPath;
        private ContentType contentType;

        public ADFileModel(String id, String mFilePath, String mFileName, String mSize, String mDate, ContentType mContentType) {
            this.id = id;
            this.filePath = mFilePath;
            this.fileName = mFileName;
            this.size = mSize;
            this.date = mDate;
            this.contentType = mContentType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getThumbPath() {
            return thumbPath;
        }

        public void setThumbPath(String thumbPath) {
            this.thumbPath = thumbPath;
        }

        public ContentType getContentType() {
            return contentType;
        }

        public void setContentType(ContentType contentType) {
            this.contentType = contentType;
        }

        @Override
        public String toString() {
            return "ADFileModel{" +
                    "id='" + id + '\'' +
                    ", filePath='" + filePath + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", size='" + size + '\'' +
                    ", date='" + date + '\'' +
                    ", thumbPath='" + thumbPath + '\'' +
                    ", contentType=" + contentType +
                    '}';
        }
    }

    public enum ContentType {
        video,
        file,
        image,
        audio
    }
}
