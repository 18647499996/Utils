package com.liudonghan.main.bean;



import androidx.annotation.NonNull;

import com.liudonghan.utils.ADHtmlUtils;

import java.util.List;

public class FoodJieHtmlModel {
    private String cover;
    private String video;
    private List<String> tags;
    private String desc;
    private Nutrition nutrition;
    private List<Nutrition.Item> ingredient;
    private List<Step> step;


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public List<Nutrition.Item> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Nutrition.Item> ingredient) {
        this.ingredient = ingredient;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    @NonNull
    @Override
    public String toString() {
        return "FoodJieHtmlModel{" +
                "cover='" + cover + '\'' +
                ", video='" + video + '\'' +
                ", tags=" + tags +
                ", desc='" + desc + '\'' +
                ", nutrition=" + nutrition +
                ", ingredient=" + ingredient +
                ", step=" + step +
                '}';
    }

    public static class Nutrition {

        private List<Item> items;
        // 含糖量
        private String sugarDose;
        // 血糖高低
        private String sugarUneven;
        // 建议饮食
        private String suggest;

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public String getSugarDose() {
            return sugarDose;
        }

        public void setSugarDose(String sugarDose) {
            this.sugarDose = sugarDose;
        }

        public String getSugarUneven() {
            return sugarUneven;
        }

        public void setSugarUneven(String sugarUneven) {
            this.sugarUneven = sugarUneven;
        }

        public String getSuggest() {
            return suggest;
        }

        public void setSuggest(String suggest) {
            this.suggest = suggest;
        }

        public static class Item {

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @NonNull
            @Override
            public String toString() {
                return "Nutrition{" +
                        "name='" + name + '\'' +
                        ", value='" + value + '\'' +
                        '}';
            }
        }

        @NonNull
        @Override
        public String toString() {
            return "Nutrition{" +
                    "items=" + items +
                    ", sugarDose='" + sugarDose + '\'' +
                    ", sugarUneven='" + sugarUneven + '\'' +
                    ", suggest='" + suggest + '\'' +
                    '}';
        }
    }

    public static class Step {
        private String content;
        private String image;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
