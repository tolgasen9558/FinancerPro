package com.example.android.financerpro.DataModels;

import com.example.android.financerpro.DataModels.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drystan on 12/14/17.
 */

public class CurrencyExchange {

        /**
         * base : USD
         * date : 12-14-2017
         * rates : {"AUD":1.4884,"BGN":1.9558,"BRL":4.1296,"CAD":1.4894,"CHF":1.0946,"CNY":7.3903,"CZK":27.03,"DKK":7.4503,"GBP":0.7989,"HKD":8.8652,"HRK":7.5105,"HUF":313.3,"IDR":15007.54,"ILS":4.3182,"INR":75.8528,"JPY":128.07,"KRW":1315.78,"MXN":19.8996,"MYR":4.4382,"NOK":9.4401,"NZD":1.6518,"PHP":52.615,"PLN":4.244,"RON":4.4693,"RUB":77.543,"SEK":9.2413,"SGD":1.5389,"THB":40.115,"TRY":3.2285,"EUR": .8476,"ZAR":16.8758}
         */

        private String base;
        private String date;
        /**
         * AUD : 1.3045
         * BGN : 1.6512
         * BRL : 3.3372
         * CAD : 1.2829
         * CHF : 0.98658
         * CNY : 6.6085
         * CZK : 21.693
         * DKK : 6.2839
         * GBP : 0.74431
         * HKD : 7.8091
         * HRK : 6.3721
         * HUF : 265.15
         * IDR : 13573
         * ILS : 3.527
         * INR : 64.37
         * JPY : 112.61
         * KRW : 1088
         * MXN : 19.1
         * MYR : 4.0842
         * NOK : 8.2552
         * NZD : 1.4296
         * PHP : 50.572
         * PLN : 3.5644
         * RON : 3.9114
         * RUB : 58.775
         * SEK : 8.3992
         * SGD : 1.3466
         * THB : 32.507
         * TRY : 3.8713
         * ZAR : 13.469
         * EUR : 0.84424
         */

        private RatesEntity rates;

        public List<Currency> getCurrencyList(){
            List<Currency> currencyList = new ArrayList<>();
            currencyList.add(new Currency("AUD", rates.getAUD()));
            currencyList.add(new Currency("BGN", rates.getBGN()));
            currencyList.add(new Currency("BRL", rates.getBRL()));
            currencyList.add(new Currency("CAD", rates.getCAD()));
            currencyList.add(new Currency("CHF", rates.getCHF()));

            currencyList.add(new Currency("CNY", rates.getCNY()));
            currencyList.add(new Currency("CZK", rates.getCZK()));
            currencyList.add(new Currency("DKK", rates.getDKK()));
            currencyList.add(new Currency("GBP", rates.getGBP()));
            currencyList.add(new Currency("HKD", rates.getHKD()));

            currencyList.add(new Currency("HRK", rates.getHRK()));
            currencyList.add(new Currency("HUF", rates.getHUF()));
            currencyList.add(new Currency("IDR", rates.getIDR()));
            currencyList.add(new Currency("ILS", rates.getILS()));
            currencyList.add(new Currency("INR", rates.getINR()));

            currencyList.add(new Currency("JPY", rates.getJPY()));
            currencyList.add(new Currency("KRW", rates.getKRW()));
            currencyList.add(new Currency("MXN", rates.getMXN()));
            currencyList.add(new Currency("MYR", rates.getMYR()));
            currencyList.add(new Currency("NOK", rates.getNOK()));

            currencyList.add(new Currency("NZD", rates.getNZD()));
            currencyList.add(new Currency("PHP", rates.getPHP()));
            currencyList.add(new Currency("PLN", rates.getPLN()));
            currencyList.add(new Currency("RON", rates.getRON()));
            currencyList.add(new Currency("RUB", rates.getRUB()));

            currencyList.add(new Currency("SEK", rates.getSEK()));
            currencyList.add(new Currency("SGD", rates.getSGD()));
            currencyList.add(new Currency("THB", rates.getTHB()));
            currencyList.add(new Currency("TRY", rates.getTRY()));
            currencyList.add(new Currency("EUR", rates.getEUR()));

            currencyList.add(new Currency("ZAR", rates.getZAR()));

            return currencyList;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public RatesEntity getRates() {
            return rates;
        }

        public void setRates(RatesEntity rates) {
            this.rates = rates;
        }

        public static class RatesEntity {
            private double AUD;
            private double BGN;
            private double BRL;
            private double CAD;
            private double CHF;
            private double CNY;
            private double CZK;
            private double DKK;
            private double GBP;
            private double HKD;
            private double HRK;
            private double HUF;
            private double IDR;
            private double ILS;
            private double INR;
            private double JPY;
            private double KRW;
            private double MXN;
            private double MYR;
            private double NOK;
            private double NZD;
            private double PHP;
            private double PLN;
            private double RON;
            private double RUB;
            private double SEK;
            private double SGD;
            private double THB;
            private double TRY;
            private double EUR;
            private double ZAR;

            public double getAUD() {
                return AUD;
            }

            public void setAUD(double AUD) {
                this.AUD = AUD;
            }

            public double getBGN() {
                return BGN;
            }

            public void setBGN(double BGN) {
                this.BGN = BGN;
            }

            public double getBRL() {
                return BRL;
            }

            public void setBRL(double BRL) {
                this.BRL = BRL;
            }

            public double getCAD() {
                return CAD;
            }

            public void setCAD(double CAD) {
                this.CAD = CAD;
            }

            public double getCHF() {
                return CHF;
            }

            public void setCHF(double CHF) {
                this.CHF = CHF;
            }

            public double getCNY() {
                return CNY;
            }

            public void setCNY(double CNY) {
                this.CNY = CNY;
            }

            public double getCZK() {
                return CZK;
            }

            public void setCZK(double CZK) {
                this.CZK = CZK;
            }

            public double getDKK() {
                return DKK;
            }

            public void setDKK(double DKK) {
                this.DKK = DKK;
            }

            public double getGBP() {
                return GBP;
            }

            public void setGBP(double GBP) {
                this.GBP = GBP;
            }

            public double getHKD() {
                return HKD;
            }

            public void setHKD(double HKD) {
                this.HKD = HKD;
            }

            public double getHRK() {
                return HRK;
            }

            public void setHRK(double HRK) {
                this.HRK = HRK;
            }

            public double getHUF() {
                return HUF;
            }

            public void setHUF(double HUF) {
                this.HUF = HUF;
            }

            public double getIDR() {
                return IDR;
            }

            public void setIDR(double IDR) {
                this.IDR = IDR;
            }

            public double getILS() {
                return ILS;
            }

            public void setILS(double ILS) {
                this.ILS = ILS;
            }

            public double getINR() {
                return INR;
            }

            public void setINR(double INR) {
                this.INR = INR;
            }

            public double getJPY() {
                return JPY;
            }

            public void setJPY(double JPY) {
                this.JPY = JPY;
            }

            public double getKRW() {
                return KRW;
            }

            public void setKRW(double KRW) {
                this.KRW = KRW;
            }

            public double getMXN() {
                return MXN;
            }

            public void setMXN(double MXN) {
                this.MXN = MXN;
            }

            public double getMYR() {
                return MYR;
            }

            public void setMYR(double MYR) {
                this.MYR = MYR;
            }

            public double getNOK() {
                return NOK;
            }

            public void setNOK(double NOK) {
                this.NOK = NOK;
            }

            public double getNZD() {
                return NZD;
            }

            public void setNZD(double NZD) {
                this.NZD = NZD;
            }

            public double getPHP() {
                return PHP;
            }

            public void setPHP(double PHP) {
                this.PHP = PHP;
            }

            public double getPLN() {
                return PLN;
            }

            public void setPLN(double PLN) {
                this.PLN = PLN;
            }

            public double getRON() {
                return RON;
            }

            public void setRON(double RON) {
                this.RON = RON;
            }

            public double getRUB() {
                return RUB;
            }

            public void setRUB(double RUB) {
                this.RUB = RUB;
            }

            public double getSEK() {
                return SEK;
            }

            public void setSEK(double SEK) {
                this.SEK = SEK;
            }

            public double getSGD() {
                return SGD;
            }

            public void setSGD(double SGD) {
                this.SGD = SGD;
            }

            public double getTHB() {
                return THB;
            }

            public void setTHB(double THB) {
                this.THB = THB;
            }

            public double getTRY() {
                return TRY;
            }

            public void setTRY(double TRY) {
                this.TRY = TRY;
            }

            public double getEUR() {
                return EUR;
            }

            public void setEUR(double EUR) { this.EUR = EUR; }

            public double getZAR() {
                return ZAR;
            }

            public void setZAR(double ZAR) {
                this.ZAR = ZAR;
            }
        }
    }
