package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/4/23
 */
public class ADCursorManageUtils {

    private static final String TAG = "Mac_Liu";

    public static final String[] FILE_PROJECTION = new String[]{
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    public static final String[] IMAGE_PROJECTION = new String[]{
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE
    };

    public static final String[] VIDEO_PROJECTION = new String[]{
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

    public static final String TXT = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String[] TXT_SELECTION = new String[]{"text/plain"};

    public static final String PDF = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String[] PDF_SELECTION = new String[]{"application/pdf"};

    public static final String DOC = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

    public static final String[] DOC_SELECTION = new String[]{"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};

    public static final String PPT = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

    public static final String[] PPT_SELECTION = new String[]{"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};

    public static final String XLS = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

    public static final String[] XLS_SELECTION = new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};

    public static final String APK = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String[] APK_SELECTION = new String[]{"application/vnd.android.package-archive"};

    public static final String ZIP = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String[] ZIP_SELECTION = new String[]{"application/zip"};

    public static final String IMAGE = MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "= ?";

    public static final String[] IMAGE_SELECTION = new String[]{"image/jpeg", "image/png"};

    public static final String VIDEO = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String[] VIDEO_SELECTION = new String[]{"video/mp4"};

    public static final String IMAGE_OR_VIDEO = MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Video.Media.MIME_TYPE + "= ?";

    public static final String[] IMAGE_OR_VIDEO_SELECTION = new String[]{"image/jpeg", "image/png", "video/mp4"};

    private static volatile ADCursorManageUtils instance = null;
    private static ContentResolver mContentResolver;

    private ADCursorManageUtils() {
    }

    public static ADCursorManageUtils getInstance(Context context) {
        //single checkout
        if (null == instance) {
            synchronized (ADCursorManageUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADCursorManageUtils();
                    mContentResolver = context.getContentResolver();
                }
            }
        }
        return instance;
    }

    /**
     * 获取Cursor引用
     *
     * @param uri           uri文件流
     * @param projection    查询字段
     * @param selection     查询条件
     * @param selectionArgs 扩展条件
     * @param orderBy       排序条件
     * @param sort          升降序
     * @return Cursor
     */
    public Cursor getCursor(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy, String sort) {
        return mContentResolver.query(uri, projection, selection, selectionArgs, orderBy + " " + sort);
    }

    /**
     * 获取视频列表
     *
     * @return List<VideoModel>
     */
    @SuppressLint("Range")
    public List<ImageFolderModel.MediaModel> getVideoFile() {
        List<ImageFolderModel.MediaModel> videoModelList = new ArrayList<>();
        try (Cursor cursor = getCursor(VIDEO_URI, null, VIDEO, VIDEO_SELECTION, MediaStore.Video.Media.DATE_MODIFIED, DESC)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    if (ADFileUtils.getInstance().scannerFile(filePath)) {
                        ImageFolderModel.MediaModel mediaModel = new ImageFolderModel.MediaModel(
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                                filePath,
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                                ContentType.video,
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.WIDTH)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.HEIGHT)),
                                "",
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)));
                        videoModelList.add(mediaModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoModelList;
    }

    /**
     * 获取视频列表
     *
     * @return List<VideoModel>
     */
    @SuppressLint("Range")
    public List<ImageFolderModel> getVideoFolder() {
        List<ImageFolderModel> videoModelList = new ArrayList<>();
        try (Cursor cursor = getCursor(VIDEO_URI, null, VIDEO, VIDEO_SELECTION, MediaStore.Video.Media.DATE_MODIFIED, DESC)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    ImageFolderModel.MediaModel mediaModel = new ImageFolderModel.MediaModel(
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                            filePath,
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                            ContentType.video,
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.WIDTH)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.HEIGHT)),
                            "",
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)));
                    getFolder(videoModelList, filePath, mediaModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoModelList;
    }


    /**
     * 获取图片列表（ 全部 ）
     *
     * @return List<ImageFolderModel.ImageModel>
     */
    public List<ImageFolderModel.MediaModel> getImageFile() {
        List<ImageFolderModel.MediaModel> imageModelList = new ArrayList<>();
        try (Cursor cursor = getCursor(IMAGE_URI, null, IMAGE, IMAGE_SELECTION, MediaStore.Images.Media.DATE_MODIFIED, DESC)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    if (ADFileUtils.getInstance().scannerFile(filePath)) {
                        ImageFolderModel.MediaModel mediaModel = new ImageFolderModel.MediaModel(
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                                filePath,
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                                ContentType.image,
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.WIDTH)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.HEIGHT)),
                                "",
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)));
                        imageModelList.add(mediaModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageModelList;
    }

    /**
     * 获取图片文件夹列表
     *
     * @return List<ImageFolderModel>
     */
    @SuppressLint("Range")
    public List<ImageFolderModel> getImageFolder() {
        List<ImageFolderModel> imageFolderModelList = new ArrayList<>();
        try (Cursor cursor = getCursor(IMAGE_URI, null, IMAGE, IMAGE_SELECTION, MediaStore.Images.Media.DATE_MODIFIED, DESC)) {
            // 用于保存已经添加过的文件夹目录
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    ImageFolderModel.MediaModel mediaModel = new ImageFolderModel.MediaModel(
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                            filePath,
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                            ContentType.image,
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.WIDTH)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.HEIGHT)),
                            "",
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DURATION)));
                    getFolder(imageFolderModelList, filePath, mediaModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFolderModelList;
    }

    /**
     * 获取内容提供者文件列表
     *
     * @param contentType 文件类型
     * @return List<ADFileModel>
     */
    @SuppressLint("Range")
    public List<ADFileModel> getFileModel(ContentType contentType) {
        List<ADFileModel> adFileModelList = new ArrayList<>();
        Cursor cursor;
        switch (contentType) {
            case txt:
                cursor = getCursor(FILE_URI, null, TXT, TXT_SELECTION, FILE_ORDER_BY, DESC);
                break;
            case pdf:
                cursor = getCursor(FILE_URI, null, PDF, PDF_SELECTION, FILE_ORDER_BY, DESC);
                break;
            case doc:
                cursor = getCursor(FILE_URI, null, DOC, DOC_SELECTION, FILE_ORDER_BY, DESC);
                break;
            case ppt:
                cursor = getCursor(FILE_URI, null, PPT, PPT_SELECTION, FILE_ORDER_BY, DESC);
                break;
            case xls:
                cursor = getCursor(FILE_URI, null, XLS, XLS_SELECTION, FILE_ORDER_BY, DESC);
                break;
            default:
                cursor = getCursor(FILE_URI, null, null, null, FILE_ORDER_BY, DESC);
                break;
        }
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 获取文件路径
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    // 扫描是否是文件
                    if (ADFileUtils.getInstance().scannerFile(filePath)) {
                        adFileModelList.add(new ADFileModel(
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                                filePath,
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)),
                                contentType
                        ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return adFileModelList;
    }

    /**
     * 获取图片与视频文件
     *
     * @return List<ADFileModel>
     */
    public List<ImageFolderModel> getImageOrVideoFile() {
        List<ImageFolderModel> videoFolder = getVideoFolder();
        List<ImageFolderModel> imageFolderModelList = new ArrayList<>(getImageFolder());
        for (int i = 0; i < imageFolderModelList.size(); i++) {
            for (int j = 0; j < videoFolder.size(); j++) {
                if (imageFolderModelList.get(i).getDirName().equals(videoFolder.get(j).getDirName())) {
                    for (int k = 0; k < videoFolder.get(j).getMediaPath().size(); k++) {
                        imageFolderModelList.get(i).setFileCount(imageFolderModelList.get(i).getFileCount() + 1);
                        imageFolderModelList.get(i).getMediaPath().add(videoFolder.get(j).getMediaPath().get(k));
                    }
                }
            }
        }

        return imageFolderModelList;
    }

    /**
     * 获取视频时长
     *
     * @param videoFilePath 视频文件路径
     * @return long
     */
    public long getMediaDuration(String videoFilePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(videoFilePath);
            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            return Long.parseLong(Objects.requireNonNull(duration));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return 0;
    }

    /**
     * 获取媒体类型
     *
     * @param mediaFilePath 媒体文件路径
     * @return String
     */
    public String getMediaMimeType(String mediaFilePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(mediaFilePath);
            return retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return "";
    }

    private ContentType getContentType(String mimeType) {
        switch (mimeType) {
            case "image/jpeg":
            case "image/png":
                return ContentType.image;
            case "video/mp4":
                return ContentType.video;
        }
        return null;
    }

    /**
     * 获取文件父级文件夹
     *
     * @param dirs       文件夹列表
     * @param filePath   文件路径
     * @param mediaModel 媒体文件数据
     */
    public void getFolder(List<ImageFolderModel> dirs, String filePath, ImageFolderModel.MediaModel mediaModel) {
        // 获取图片父文件
        File parentFile = new File(filePath).getParentFile();
        if (null == parentFile) {
            return;
        }
        if (parentFile.getAbsolutePath().contains(".")) {
            return;
        }
        // 获取父文件绝对路径
        String dir = parentFile.getAbsolutePath();
        boolean isContains = false;
        int position = 0;
        for (int i = 0; i < dirs.size(); i++) {
            if (dirs.get(i).getDirPath().contains(dir)) {
                position = i;
                isContains = true;
            }
        }

        if (isContains) {
            dirs.get(position).getMediaPath().add(mediaModel);
            dirs.get(position).setFileCount(dirs.get(position).getMediaPath().size());
        } else {
//            Log.i(TAG, "是否隐藏文件：" + parentFile.getAbsolutePath().contains(".") + "  ------ " + parentFile.getAbsolutePath());
            ImageFolderModel imageFolderModel = new ImageFolderModel();
            List<ImageFolderModel.MediaModel> mediaModelList = new ArrayList<>();
            imageFolderModel.setDirName(parentFile.getName());
            imageFolderModel.setCoverPath(filePath);
            imageFolderModel.setDirPath(parentFile.getAbsolutePath());
            mediaModelList.add(mediaModel);
            imageFolderModel.setMediaPath(mediaModelList);
            imageFolderModel.setFileCount(1);
            dirs.add(imageFolderModel);
        }
    }

    /**
     * 是否当前类型文件
     *
     * @param contentType 文件类型
     * @param filePath    文件路径
     * @return boolean
     */
    private boolean isContainFileType(ContentType contentType, String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        String path = filePath.toLowerCase();
        switch (contentType) {
            case file:
                return true;
            case txt:
                return path.endsWith(".txt");
            case doc:
                return path.endsWith(".doc") || path.endsWith(".docx");
            case pdf:
                return path.endsWith(".pdf");
            case ppt:
                return path.endsWith(".ppt") || path.endsWith(".pptx");
            case xls:
                return path.endsWith(".xls") || path.endsWith(".xlsx");
            case apk:
                return path.endsWith(".apk");
            case zip:
                return path.endsWith(".zip") || path.endsWith(".rar") || path.endsWith(".tar") || path.endsWith(".gz");
        }
        return true;
    }


    public static class ImageFolderModel implements Serializable {
        /**
         * 当前文件夹的路径
         */
        private String dirPath;
        /**
         * 第一张图片的路径，用于做文件夹的封面图片
         */
        private String coverPath;
        /**
         * 文件夹名
         */
        private String dirName;
        /**
         * 文件夹中图片的数量
         */
        private int fileCount;

        /**
         * 是否选中
         */
        private boolean isSelect = false;

        private List<MediaModel> mediaPath;

        public String getDirPath() {
            return dirPath;
        }

        public void setDirPath(String dirPath) {
            this.dirPath = dirPath;
        }

        public String getCoverPath() {
            return coverPath;
        }

        public void setCoverPath(String coverPath) {
            this.coverPath = coverPath;
        }

        public String getDirName() {
            return dirName;
        }

        public void setDirName(String dirName) {
            this.dirName = dirName;
        }

        public int getFileCount() {
            return fileCount;
        }

        public void setFileCount(int fileCount) {
            this.fileCount = fileCount;
        }

        public List<MediaModel> getMediaPath() {
            return mediaPath;
        }

        public void setMediaPath(List<MediaModel> mediaPath) {
            this.mediaPath = mediaPath;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public String toString() {
            return "ImageFolderModel{" +
                    "dirPath='" + dirPath + '\'' +
                    ", coverPath='" + coverPath + '\'' +
                    ", dirName='" + dirName + '\'' +
                    ", fileCount=" + fileCount +
                    '}';
        }

        public static class MediaModel extends ADFileModel implements Serializable {
            private int width;
            private int height;
            private String resolution;// 分辨率
            private long duration = 0;

            public MediaModel(int id, String mFilePath, String mFileName, long fileSize, long fileDate, ContentType mContentType, int width, int height, String resolution, long duration) {
                super(id, mFilePath, mFileName, fileSize, fileDate, mContentType);
                this.width = width;
                this.height = height;
                this.resolution = resolution;
                this.duration = duration;
            }

            public MediaModel() {

            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getResolution() {
                return resolution;
            }

            public void setResolution(String resolution) {
                this.resolution = resolution;
            }

            public long getDuration() {
                return duration;
            }

            public void setDuration(long duration) {
                this.duration = duration;
            }
        }
    }

    public static class ADFileModel implements Serializable {
        private int id;
        private String filePath;
        private String fileName;
        private long fileSize;
        private long fileDate;
        private ContentType contentType;
        private boolean isSelector = false;

        public ADFileModel() {

        }

        public ADFileModel(int id, String mFilePath, String mFileName, long fileSize, long fileDate, ContentType mContentType) {
            this.id = id;
            this.filePath = mFilePath;
            this.fileName = mFileName;
            this.fileSize = fileSize;
            this.fileDate = fileDate;
            this.contentType = mContentType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public long getFileDate() {
            return fileDate;
        }

        public void setFileDate(long fileDate) {
            this.fileDate = fileDate;
        }

        public ContentType getContentType() {
            return contentType;
        }


        public void setContentType(ContentType contentType) {
            this.contentType = contentType;
        }

        public boolean isSelector() {
            return isSelector;
        }

        public void setSelector(boolean selector) {
            isSelector = selector;
        }

        @Override
        public String toString() {
            return "ADFileModel{" +
                    "id=" + id +
                    ", filePath='" + filePath + '\'' +
                    ", fileName='" + fileName + '\'' +
                    ", fileSize=" + fileSize +
                    ", fileDate=" + fileDate +
                    ", contentType=" + contentType +
                    '}';
        }
    }

    public enum ContentType {
        video,
        file,
        image,
        audio,
        doc,
        txt,
        ppt,
        xls,
        pdf,
        zip,
        apk,
        unknown
    }
}
