package com.liudonghan.main.bean;

import java.util.List;

public class FoodChinaHtmlModel {

    private List<Dosing> dosing;

    private List<Step> step;

    public List<Dosing> getDosing() {
        return dosing;
    }

    public void setDosing(List<Dosing> dosing) {
        this.dosing = dosing;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    public static class Dosing {
        private String dosingType;

        private List<FoodChild> foodChildren;

        public String getDosingType() {
            return dosingType;
        }

        public void setDosingType(String dosingType) {
            this.dosingType = dosingType;
        }

        public List<FoodChild> getFoodChildren() {
            return foodChildren;
        }

        public void setFoodChildren(List<FoodChild> foodChildren) {
            this.foodChildren = foodChildren;
        }

        public static class FoodChild {
            private String name;
            private String dosage;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDosage() {
                return dosage;
            }

            public void setDosage(String dosage) {
                this.dosage = dosage;
            }
        }
    }

    public static class Step {
        String img;
        String content;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
