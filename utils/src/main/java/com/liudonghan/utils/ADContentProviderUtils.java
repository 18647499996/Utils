package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/4/23
 */
public class ADContentProviderUtils {

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

    public static final String ONE_SELECTION = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";

    public static final String TWO_SELECTION = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

    public static final String IMAGE = MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "= ?";

    public static final String[] IMAGE_SELECTION = new String[]{"image/jpeg", "image/png"};

    public static final String[] VIDEO_SELECTION = new String[]{"video/mp4"};

    public static final String[] TXT_SELECTION = new String[]{"text/plain"};

    public static final String[] DOC_SELECTION = new String[]{"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};

    public static final String[] PDF_SELECTION = new String[]{"application/pdf"};

    public static final String[] PPT_SELECTION = new String[]{"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};

    public static final String[] XLS_SELECTION = new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};


    private static volatile ADContentProviderUtils instance = null;
    private static ContentResolver mContentResolver;

    private ADContentProviderUtils() {
    }

    public static ADContentProviderUtils getInstance(Context context) {
        //single checkout
        if (null == instance) {
            synchronized (ADContentProviderUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADContentProviderUtils();
                    mContentResolver = context.getContentResolver();
                }
            }
        }
        return instance;
    }

    /**
     * 获取视频列表
     *
     * @return
     */
    @SuppressLint("Range")
    public List<VideoModel> getVideoFile() {
        List<VideoModel> videoModelList = new ArrayList<>();
        try (Cursor cursor = mContentResolver.query(VIDEO_URI, null, null, null, MediaStore.Video.Media.DATE_MODIFIED + " " + DESC)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    if (ADFileUtils.getInstance().scannerFile(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)))) {
                        videoModelList.add(new VideoModel(
                                cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)),
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)),
                                cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))));
                    }
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
    public List<ImageFolderModel.ImageModel> getImageFile() {
        List<ImageFolderModel.ImageModel> imageModelList = new ArrayList<>();
        try (Cursor cursor = mContentResolver.query(IMAGE_URI, null, IMAGE, IMAGE_SELECTION, MediaStore.Images.Media.DATE_MODIFIED + " " + DESC)) {
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    imageModelList.add(new ImageFolderModel.ImageModel(
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)),
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)),
                            ContentType.image,
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)))
                    );
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
        try (Cursor cursor = mContentResolver.query(IMAGE_URI, null, IMAGE, IMAGE_SELECTION, MediaStore.Images.Media.DATE_MODIFIED + " " + DESC)) {
            // 用于保存已经添加过的文件夹目录
            List<String> mDirs = new ArrayList<>();
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    // 路径
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    // 获取图片父文件
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }
                    // 获取父文件绝对路径
                    String dir = parentFile.getAbsolutePath();
                    // 如果已经添加过
                    if (mDirs.contains(dir)) {
                        continue;
                    }
                    mDirs.add(dir);
                    //添加到保存目录的集合中
                    ImageFolderModel imageFolderModel = new ImageFolderModel();
                    imageFolderModel.setDirPath(dir);
                    imageFolderModel.setCoverPath(path);
                    imageFolderModel.setDirName(parentFile.getName());
                    if (parentFile.list() == null)
                        continue;
                    List<ImageFolderModel.ImageModel> imageModelList = new ArrayList<>();
                    int count = Objects.requireNonNull(parentFile.list((dir1, filename) -> {
                        if (filename.endsWith(".jpeg") || filename.endsWith(".jpg") || filename.endsWith(".png")) {
                            imageModelList.add(new ImageFolderModel.ImageModel(
                                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)),
                                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
                                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)),
                                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)),
                                    ContentType.image,
                                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)),
                                    cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH))));
                            return true;
                        }
                        return false;
                    })).length;
                    imageFolderModel.setImagePath(imageModelList);
                    imageFolderModel.setFileCount(count);
                    imageFolderModelList.add(imageFolderModel);
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
    public List<ADFileModel> getContentProviderList(ContentType contentType) {
        List<ADFileModel> adFileModelList = new ArrayList<>();
        try (Cursor cursor = mContentResolver.query(FILE_URI, null, null, null, MediaStore.Files.FileColumns.DATE_MODIFIED + " " + DESC)) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 获取文件路径
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA));
                    // 扫描是否是文件
                    if (ADFileUtils.getInstance().scannerFile(filePath)) {
                        // 是否是当前文件类型
                        if (isContainFileType(contentType, filePath)) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adFileModelList;
    }

    /**
     * 是否
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

    public static class ImageFolderModel {
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
         * 通过图片文件夹的路径获取该目录下的图片
         */
        private List<ImageModel> imagePath;

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

        public List<ImageModel> getImagePath() {
            return imagePath;
        }

        public void setImagePath(List<ImageModel> imagePath) {
            this.imagePath = imagePath;
        }

        public static class ImageModel extends ADFileModel {
            private int width;
            private int height;

            public ImageModel() {

            }

            public ImageModel(int id, String mFilePath, String mFileName, long fileSize, long fileDate, ContentType mContentType, int width, int height) {
                super(id, mFilePath, mFileName, fileSize, fileDate, mContentType);
                this.width = width;
                this.height = height;
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

            @Override
            public String toString() {
                return "ImageModel{" +
                        "width=" + width +
                        ", height=" + height +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ImageFolderModel{" +
                    "dirPath='" + dirPath + '\'' +
                    ", coverPath='" + coverPath + '\'' +
                    ", dirName='" + dirName + '\'' +
                    ", fileCount=" + fileCount +
                    ", imagePath=" + imagePath +
                    '}';
        }
    }

    public static class VideoModel extends ADFileModel {
        private String resolution;// 分辨率
        private long duration = 0;

        public VideoModel() {
            super();
        }

        public VideoModel(int id, String filePath, String fileName, String resolution, long fileSize, long fileDate, long duration) {
            super(id, filePath, fileName, fileSize, fileDate, ContentType.video);
            this.resolution = resolution;
            this.duration = duration;
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

    public static class ADFileModel {
        private int id;
        private String filePath;
        private String fileName;
        private long fileSize;
        private long fileDate;
        private ContentType contentType;

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
        doc,
        txt,
        ppt,
        xls,
        pdf,
        zip,
        apk
    }
}
