package com.pipelineworks.stream.entity;


import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class SensorData {


        private String time;

        private String power;

        private String temp;

        private String humidity;

        private String light;

        private String CO2;

        private String dust;

        private String compositeKey;

        public SensorData() {

        }

        public SensorData(String time, String power, String temp, String humidity, String light, String CO2, String dust, String compositeKey) {
            this.time = time;
            this.power = power;
            this.temp = temp;
            this.humidity = humidity;
            this.light = light;
            this.CO2 = CO2;
            this.dust = dust;
            this.compositeKey = compositeKey;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getLight() {
            return light;
        }

        public void setLight(String light) {
            this.light = light;
        }

        public String getCO2() {
            return CO2;
        }

        public void setCO2(String CO2) {
            this.CO2 = CO2;
        }

        public String getDust() {
            return dust;
        }

        public void setDust(String dust) {
            this.dust = dust;
        }

        public String getCompositeKey() {
            return this.getTemp() + ":" + this.getHumidity() + ":" + this.getCO2() + ":" + this.getLight();
        }


        @Override
        public String toString() {

            ObjectMapper om = new ObjectMapper();
            try {
                return om.writeValueAsString(this);
            } catch (Exception ex) {
                return ex.getMessage();
            }

        /*return "{" +
                "time='" + time + '\'' +
                ", power='" + power + '\'' +
                ", tem='" + tem + '\'' +
                ", humidity='" + humidity + '\'' +
                ", light='" + light + '\'' +
                ", CO2='" + CO2 + '\'' +
                ", dust='" + dust + '\'' +
                '}';*/
        }


        public static void main(String[] args) {

            SensorData sd = new SensorData();
            sd.setCO2("co2");
            sd.setDust("dust");


            ObjectMapper om = new ObjectMapper();
            try {
                System.out.println(om.writeValueAsString(sd));
                System.out.println(sd.toString());

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }