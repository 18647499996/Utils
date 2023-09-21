package com.liudonghan.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/20/23
 */
@Entity
public class PetEntity {
    @Id(autoincrement = true)
    private Long id;

    /**
     * pettype : 1
     * name : 京巴犬
     * engName : Pekingese
     * characters : 　友善亲切、勇敢聪明
     * nation : 中国
     * easyOfDisease : 肥胖症
     * life : 12—15年
     * price : 1000起
     * desc : 　　京巴犬又称宫廷狮子
     * feature : 　　理想的京巴犬公犬体重应不超过5kg，母犬不超过5.5kg。看着体型不大，如果举起京巴犬的话，你会发现他惊人的体重，看似身材矮小，其实他属于婴儿肥的那种，加肌肉发达，所以分量肯定不轻咯。
     * characterFeature : 　　京巴犬很难用耳朵来表达自己的情绪，因为他的耳朵呈下垂状态，很难竖起来的，若主人仔细观察，会发现他们的耳根部也能竖起来，尤其是他们表现还怕与恐惧的心情时，你可以看到它们的耳朵也会使劲地向后仰，这时，主人需要立刻安抚它们，让它们慢慢平静下来。
     * careKnowledge : 　　京巴犬在炎热的夏天特别是闷热的天气里，他会出现呼吸困难，甚至窒息死亡。饲养京巴犬的主人应注意家里的窗户每天是否打开了，保证空气流畅，让狗狗呼吸畅通。
     * feedPoints : 　　京巴犬又称北京犬，也有很多人叫他狮子狗，因它圆圆的大眼睛、胖乎乎的小身子以及他憨嘟嘟的面孔深受人们的喜爱。京巴犬在饲养过程中也是有很多方面要注意的，那在生活中要如何正确喂养呢?
     * url : http://www.boqii.com/entry/detail/630.html
     * coverURL : http://img.boqiicdn.com/Data/BK/P/img71381407403025.jpg
     */

    // todo 宠物类型，0猫科、1犬类、2爬行类、3小宠物类、4水族类(例：1)
    private int pettype;
    // todo 宠物名字(例：哈士奇)
    private String name;
    // todo 宠物英文名(例：SiberianHuskiy)
    private String engName;
    // todo 性格特点(例：聪明机灵、极度热情、神经质)
    private String characters;
    // todo 祖籍(例：俄罗斯)
    private String nation;
    // todo 易患病(例：肠胃疾病)
    private String easyOfDisease;
    // todo 寿命(例：9-15年)
    private String life;
    // todo 价格(例：2000-4000元)
    private String price;
    // todo 描述(例：西伯利亚)
    private String desc;
    // todo 体态特征(例：西伯利亚雪橇犬是一种原始的古老犬种......)
    private String feature;
    // todo 特点(例：哈士奇的外表英俊潇洒......)
    private String characterFeature;
    // todo 照顾须知(例：哈士奇虽然看着一副冷漠无情的样子......)
    private String careKnowledge;
    // todo 喂养注意(例：哈士奇有着敏感的肠胃......)
    private String feedPoints;
    // todo 详细来源(例：http://www.boqii.com/entry/detail/357.html)
    private String url;
    // todo 封面图片(例：http://img.boqiicdn.com/Data/BK/P/imagick14371435571930.png)
    private String coverURL;

    @Generated(hash = 467686809)
    public PetEntity(Long id, int pettype, String name, String engName, String characters, String nation, String easyOfDisease, String life, String price,
            String desc, String feature, String characterFeature, String careKnowledge, String feedPoints, String url, String coverURL) {
        this.id = id;
        this.pettype = pettype;
        this.name = name;
        this.engName = engName;
        this.characters = characters;
        this.nation = nation;
        this.easyOfDisease = easyOfDisease;
        this.life = life;
        this.price = price;
        this.desc = desc;
        this.feature = feature;
        this.characterFeature = characterFeature;
        this.careKnowledge = careKnowledge;
        this.feedPoints = feedPoints;
        this.url = url;
        this.coverURL = coverURL;
    }

    @Generated(hash = 2027225065)
    public PetEntity() {
    }

    public int getPettype() {
        return pettype;
    }

    public void setPettype(int pettype) {
        this.pettype = pettype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEasyOfDisease() {
        return easyOfDisease;
    }

    public void setEasyOfDisease(String easyOfDisease) {
        this.easyOfDisease = easyOfDisease;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getCharacterFeature() {
        return characterFeature;
    }

    public void setCharacterFeature(String characterFeature) {
        this.characterFeature = characterFeature;
    }

    public String getCareKnowledge() {
        return careKnowledge;
    }

    public void setCareKnowledge(String careKnowledge) {
        this.careKnowledge = careKnowledge;
    }

    public String getFeedPoints() {
        return feedPoints;
    }

    public void setFeedPoints(String feedPoints) {
        this.feedPoints = feedPoints;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
