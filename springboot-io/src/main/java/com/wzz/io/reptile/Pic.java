package com.wzz.io.reptile;

import lombok.Data;

import java.util.List;

@Data
public class Pic {
    private String queryEnc;
    private String queryExt;
    private int listNum;
    private int displayNum;
    private String gsm;
    private String bdFmtDispNum;
    private String bdSearchTime;
    private int isNeedAsyncRequest;
    private String bdIsClustered;
    private List<DataBean> data;

    @Data
    public static class DataBean {
        private String adType;
        private String hasAspData;
        private String thumbURL;
        private Object commodityInfo;
        private int isCommodity;
        private String middleURL;
        private String shituToken;
        private String largeTnImageUrl;
        private int hasLarge;
        private String hoverURL;
        private int pageNum;
        private String objURL;
        private String fromURL;
        private String fromJumpUrl;
        private String fromURLHost;
        private String currentIndex;
        private int width;
        private int height;
        private String type;
        private int is_gif;
        private int isCopyright;
        private Object resourceInfo;
        private String strategyAssessment;
        private String filesize;
        private String bdSrcType;
        private String di;
        private String pi;
        private String is;
        private String imgCollectionWord;
        private String hasThumbData;
        private int bdSetImgNum;
        private int partnerId;
        private int spn;
        private String bdImgnewsDate;
        private String fromPageTitle;
        private String fromPageTitleEnc;
        private String bdSourceName;
        private String bdFromPageTitlePrefix;
        private int isAspDianjing;
        private String token;
        private String imgType;
        private String cs;
        private String os;
        private String simid;
        private String personalized;
        private Object simid_info;
        private Object face_info;
        private Object xiangshi_info;
        private String adPicId;
        private String source_type;
        private List<ReplaceUrlBean> replaceUrl;

        @Data
        public static class ReplaceUrlBean {

            private String ObjURL;
            private String ObjUrl;
            private String FromURL;
            private String FromUrl;
        }
    }
}
