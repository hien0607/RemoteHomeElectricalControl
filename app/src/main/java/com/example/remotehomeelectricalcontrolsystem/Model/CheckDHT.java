package com.example.remotehomeelectricalcontrolsystem.Model;

public class CheckDHT {

        private double humidity;
        private double temperature;


        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

    public CheckDHT(double humidity, double temperature) {
        this.humidity = humidity;
        this.temperature = temperature;
    }
}
