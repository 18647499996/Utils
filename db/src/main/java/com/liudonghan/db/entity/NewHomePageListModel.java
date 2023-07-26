package com.liudonghan.db.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Wang_hui
 * Time:2022/7/29
 */
public class NewHomePageListModel implements MultiItemEntity {

    /**
     * id : 12
     * name : 害害害
     * content : [{"id":1872,"url":"https://shops-1307611133.cos.ap-beijing.myqcloud.com/APPLE/Image/Article/1661393518059.jpg","sort":1872,"title":"如果没能力把眼前的苟且过好，就暂时不要去想诗和远方。早安~","avatar":"https://shops-1307611133.cos.ap-beijing.myqcloud.com/APPLE/Image/Portrait/1661752074506.jpg","content":"我会在没有人的暗夜深深检讨自己的缺憾。但我不愿在众目睽睽之下，把自己像次品一般展览\u2014\u2014毕淑敏《关于人生的沉思》","user_id":910,"like_num":1,"browse_num":36},{"id":1873,"url":"https://shops-1307611133.cos.ap-beijing.myqcloud.com/APPLE/Image/Article/1661393518482.jpg,https://shops-1307611133.cos.ap-beijing.myqcloud.com/Android/Image/Article/1661415400111","sort":1873,"title":"不不不他他他他他","avatar":"https://shops-1307611133.cos.ap-beijing.myqcloud.com/Android/Image/Portrait/1654519237514","content":"他铁路不出去啦他他邋遢他","user_id":962,"like_num":3,"browse_num":13}]
     * model_type : 3
     * sort : 1
     */

    private int id;
    private String name;
    private String subtitle;
    private int model_type;
    private int sort;
    private String activity_bg;
    private String goods_bg;
    private String jump_path;
    private List<ContentBean> content;

    public String getActivity_bg() {
        return activity_bg;
    }

    public void setActivity_bg(String activity_bg) {
        this.activity_bg = activity_bg;
    }

    public String getGoods_bg() {
        return goods_bg;
    }

    public void setGoods_bg(String goods_bg) {
        this.goods_bg = goods_bg;
    }

    public String getJump_path() {
        return jump_path;
    }

    public void setJump_path(String jump_path) {
        this.jump_path = jump_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getModel_type() {
        return model_type;
    }

    public void setModel_type(int model_type) {
        this.model_type = model_type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewHomePageListModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", model_type=" + model_type +
                ", sort=" + sort +
                ", activity_bg='" + activity_bg + '\'' +
                ", goods_bg='" + goods_bg + '\'' +
                ", jump_path='" + jump_path + '\'' +
                ", content=" + content +
                '}';
    }

    @Override
    public int getItemType() {
        return 0;
    }


    public static class ContentBean implements MultiItemEntity {
        /**
         * id : 1872
         * url : https://shops-1307611133.cos.ap-beijing.myqcloud.com/APPLE/Image/Article/1661393518059.jpg
         * sort : 1872
         * title : 如果没能力把眼前的苟且过好，就暂时不要去想诗和远方。早安~
         * avatar : https://shops-1307611133.cos.ap-beijing.myqcloud.com/APPLE/Image/Portrait/1661752074506.jpg
         * content : 我会在没有人的暗夜深深检讨自己的缺憾。但我不愿在众目睽睽之下，把自己像次品一般展览——毕淑敏《关于人生的沉思》
         * user_id : 910
         * like_num : 1
         * browse_num : 36
         * images : ["http://mms0.baidu.com/it/u=193775696,2292586258&fm=253&app=138&f=JPEG&fmt=auto&q=75?w=778&h=500","http: //mms0.baidu.com/it/u=193775696,2292586258&fm=253&app=138&f=JPEG&fmt=auto&q=75?w=778&h=500","http://mms0.baidu.com/it/u=193775696,2292586258&fm=253&app=138&f=JPEG&fmt=auto&q=75?w=778&h=500"]
         * moduleList : [{"id":"1","icon":"https://api.tuansudi.com/api/File/GetFileStreamByFileName?fileName=1656405286/jikuaidi@3x.png","name":"寄快递","sort":"1","backTitle":"汇速递"},{"id":"2","icon":"https://api.tuansudi.com/api/File/GetFileStreamByFileName?fileName=1656405286/jikuaidi@3x.png","name":"查询快递","sort":"2","backTitle":"汇速递"}]
         */

        private String id;
        private String url;
        private String sort;
        private String name;
        private String title;
        private String avatar;
        private String content;
        private String jump_path;
        private String price;
        private String icon;
        private int status;
        private String user_id;
        private String like_num;
        private String browse_num;
        private String share_num;
        private String desc;
        private String rete;
        private String nickname;
        private String datetime;
        private long start_time;
        private String remark;
        private String img;
        private int url_type;
        private String count;
        private List<String> images;
        private List<ModuleListBean> moduleList;
        boolean hyh_more_select;
        private int type;// 0图文 1视频
        private String cover_img; //视频封面图
        private String time_length; //视频时长
        private boolean is_subscribe;// 是否订阅 boolean类型
        private String createTime;
        private int ouid;
        private GoodsPresaleBeans presale;
        private String address;
        private String home_province;//个人信息市
        public GoodsPresaleBeans getPresale() {
            return presale;
        }

        public String getHome_province() {
            return home_province;
        }

        public void setHome_province(String home_province) {
            this.home_province = home_province;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPresale(GoodsPresaleBeans presale) {
            this.presale = presale;
        }

        public int getOuid() {
            return ouid;
        }

        public void setOuid(int ouid) {
            this.ouid = ouid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isIs_subscribe() {
            return is_subscribe;
        }

        public void setIs_subscribe(boolean is_subscribe) {
            this.is_subscribe = is_subscribe;
        }

        public String getTime_length() {
            return time_length;
        }

        public void setTime_length(String time_length) {
            this.time_length = time_length;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public boolean isHyh_more_select() {
            return hyh_more_select;
        }

        public void setHyh_more_select(boolean hyh_more_select) {
            this.hyh_more_select = hyh_more_select;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getUrl_type() {
            return url_type;
        }

        public void setUrl_type(int url_type) {
            this.url_type = url_type;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public String getShare_num() {
            return share_num;
        }

        public void setShare_num(String share_num) {
            this.share_num = share_num;
        }

        public String getId() {
            return id;
        }

        public void setId(int String) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getBrowse_num() {
            return browse_num;
        }

        public void setBrowse_num(String browse_num) {
            this.browse_num = browse_num;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJump_path() {
            return jump_path;
        }

        public void setJump_path(String jump_path) {
            this.jump_path = jump_path;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRete() {
            return rete;
        }

        public void setRete(String rete) {
            this.rete = rete;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        @Override
        public int getItemType() {
            return 0;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<ModuleListBean> getModuleList() {
            return moduleList;
        }

        public void setModuleList(List<ModuleListBean> moduleList) {
            this.moduleList = moduleList;
        }

        public static class GoodsPresaleBeans implements Serializable {

            /**
             * local_goods_id : 1158469
             * price_sell : 1.00
             * price_presale : 1.00
             * price_leader : 2.00
             * name : 测试商
             * start_time : 2022-11-16 00:00:00
             * end_time : 2022-11-23 23:59:00
             * pay_start_time : 2022-11-23 23:59:00
             * pay_end_time : 2022-11-25 23:59:00
             * task_time : 2
             * is_full_refund : 1
             * price_leader_sell : -1.00
             */

            private int local_goods_id;
            private String price_sell;
            private String price_presale;
            private String price_leader;
            private String coupon_price;
            private String name;
            private String start_time;
            private String end_time;
            private String pay_start_time;
            private String pay_end_time;
            private int task_time;
            private int is_full_refund;
            private String price_leader_sell;
            private String price_pay;

            public String getPrice_pay() {
                return price_pay;
            }

            public void setPrice_pay(String price_pay) {
                this.price_pay = price_pay;
            }

            public String getCoupon_price() {
                return coupon_price;
            }

            public void setCoupon_price(String coupon_price) {
                this.coupon_price = coupon_price;
            }

            public int getLocal_goods_id() {
                return local_goods_id;
            }

            public void setLocal_goods_id(int local_goods_id) {
                this.local_goods_id = local_goods_id;
            }

            public String getPrice_sell() {
                return price_sell;
            }

            public void setPrice_sell(String price_sell) {
                this.price_sell = price_sell;
            }

            public String getPrice_presale() {
                return price_presale;
            }

            public void setPrice_presale(String price_presale) {
                this.price_presale = price_presale;
            }

            public String getPrice_leader() {
                return price_leader;
            }

            public void setPrice_leader(String price_leader) {
                this.price_leader = price_leader;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getPay_start_time() {
                return pay_start_time;
            }

            public void setPay_start_time(String pay_start_time) {
                this.pay_start_time = pay_start_time;
            }

            public String getPay_end_time() {
                return pay_end_time;
            }

            public void setPay_end_time(String pay_end_time) {
                this.pay_end_time = pay_end_time;
            }

            public int getTask_time() {
                return task_time;
            }

            public void setTask_time(int task_time) {
                this.task_time = task_time;
            }

            public int getIs_full_refund() {
                return is_full_refund;
            }

            public void setIs_full_refund(int is_full_refund) {
                this.is_full_refund = is_full_refund;
            }

            public String getPrice_leader_sell() {
                return price_leader_sell;
            }

            public void setPrice_leader_sell(String price_leader_sell) {
                this.price_leader_sell = price_leader_sell;
            }
        }

        public static class ModuleListBean {
            /**
             * id : 1
             * icon : https://api.tuansudi.com/api/File/GetFileStreamByFileName?fileName=1656405286/jikuaidi@3x.png
             * name : 寄快递
             * sort : 1
             * backTitle : 汇速递
             */

            private String idX;
            private String iconX;
            private String nameX;
            private String sortX;
            private String backTitle;

            public String getIdX() {
                return idX;
            }

            public void setIdX(String idX) {
                this.idX = idX;
            }

            public String getIconX() {
                return iconX;
            }

            public void setIconX(String iconX) {
                this.iconX = iconX;
            }

            public String getNameX() {
                return nameX;
            }

            public void setNameX(String nameX) {
                this.nameX = nameX;
            }

            public String getSortX() {
                return sortX;
            }

            public void setSortX(String sortX) {
                this.sortX = sortX;
            }

            public String getBackTitle() {
                return backTitle;
            }

            public void setBackTitle(String backTitle) {
                this.backTitle = backTitle;
            }
        }
    }

}
