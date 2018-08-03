package com.jsmy.pingshan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */

public class ForecastBean {

    /**
     * status : ok
     * lang : zh_CN
     * server_time : 1443418212
     * tzshift : 28800
     * location : [25.1552,121.6544]
     * unit : metric
     * result : {"status":"ok","hourly":{"status":"ok","pm25":[{"value":8,"datetime":"2015-09-28 13:00"}],"description":"暴雨渐大，今天晚间21点钟后大雨，其后阴","skycon":[{"value":"RAIN","datetime":"2015-09-28 13:00"}],"cloudrate":[{"value":1,"datetime":"2015-09-28 13:00"}],"humidity":[{"value":0.92,"datetime":"2015-09-28 13:00"}],"wind":[{"direction":23.09,"speed":80.66,"datetime":"2015-09-28 13:00"}],"temperature":[{"value":25.11,"datetime":"2015-09-28 13:00"}]},"minutely":{"status":"ok","precipitation":[0.2812,0.2959,0.3125,0.3288,0.3427,0.3519,0.3542,0.3481,0.3351,0.3176,0.2978,0.278,0.2604,0.2469,0.2371,0.2302,0.2254,0.2218,0.2188,0.2154,0.2119,0.2082,0.2046,0.2011,0.1979,0.1951,0.1928,0.1908,0.1893,0.1882,0.1875,0.1872,0.1873,0.1874,0.1876,0.1877,0.1875,0.187,0.1867,0.187,0.1886,0.1921,0.1979,0.2064,0.2166,0.2273,0.2372,0.2452,0.25,0.2507,0.248,0.2425,0.2352,0.227,0.2188,0.2111,0.2044,0.1985,0.1937,0.19],"datasource":"radar","description":"16分钟后雨渐小，转为小雨"},"daily":{"status":"ok","astro":[{"date":"2015-09-28","sunset":{"time":"17:44"},"sunrise":{"time":"05:44"}}],"temperature":[{"date":"2015-09-28","max":26.86,"avg":25.39,"min":24.97}],"pm25":[{"date":"2015-09-28","max":13,"avg":6,"min":0}],"skycon":[{"date":"2015-09-28","value":"RAIN"}],"cloudrate":[{"date":"2015-09-28","max":1,"avg":1,"min":1}],"aqi":[{"date":"2015-09-28","max":38,"avg":32,"min":26}],"precipitation":[{"date":"2015-09-28","max":9.6091,"avg":4.4194,"min":1.4964}],"wind":[{"date":"2015-09-28","max":{"direction":86.6,"speed":103.66},"avg":{"direction":56.13,"speed":55.22},"min":{"direction":11.12,"speed":35.05}}],"humidity":[{"date":"2015-09-28","max":0.93,"avg":0.89,"min":0.8}]},"alert":[{"status":"预警中","code":"0903","description":"请有关单位和人员做好防范工作。","pubdate":"2016-07-25 17:10:00","location":"海南省白沙县","bound_coord":[116.39,39.93,116.1,40.2]}]}
     */

    private String status;
    private String lang;
    private int server_time;
    private int tzshift;
    private String unit;
    private ResultBean result;
    private List<Double> location;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public int getTzshift() {
        return tzshift;
    }

    public void setTzshift(int tzshift) {
        this.tzshift = tzshift;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public static class ResultBean {
        /**
         * status : ok
         * hourly : {"status":"ok","pm25":[{"value":8,"datetime":"2015-09-28 13:00"}],"description":"暴雨渐大，今天晚间21点钟后大雨，其后阴","skycon":[{"value":"RAIN","datetime":"2015-09-28 13:00"}],"cloudrate":[{"value":1,"datetime":"2015-09-28 13:00"}],"humidity":[{"value":0.92,"datetime":"2015-09-28 13:00"}],"wind":[{"direction":23.09,"speed":80.66,"datetime":"2015-09-28 13:00"}],"temperature":[{"value":25.11,"datetime":"2015-09-28 13:00"}]}
         * minutely : {"status":"ok","precipitation":[0.2812,0.2959,0.3125,0.3288,0.3427,0.3519,0.3542,0.3481,0.3351,0.3176,0.2978,0.278,0.2604,0.2469,0.2371,0.2302,0.2254,0.2218,0.2188,0.2154,0.2119,0.2082,0.2046,0.2011,0.1979,0.1951,0.1928,0.1908,0.1893,0.1882,0.1875,0.1872,0.1873,0.1874,0.1876,0.1877,0.1875,0.187,0.1867,0.187,0.1886,0.1921,0.1979,0.2064,0.2166,0.2273,0.2372,0.2452,0.25,0.2507,0.248,0.2425,0.2352,0.227,0.2188,0.2111,0.2044,0.1985,0.1937,0.19],"datasource":"radar","description":"16分钟后雨渐小，转为小雨"}
         * daily : {"status":"ok","astro":[{"date":"2015-09-28","sunset":{"time":"17:44"},"sunrise":{"time":"05:44"}}],"temperature":[{"date":"2015-09-28","max":26.86,"avg":25.39,"min":24.97}],"pm25":[{"date":"2015-09-28","max":13,"avg":6,"min":0}],"skycon":[{"date":"2015-09-28","value":"RAIN"}],"cloudrate":[{"date":"2015-09-28","max":1,"avg":1,"min":1}],"aqi":[{"date":"2015-09-28","max":38,"avg":32,"min":26}],"precipitation":[{"date":"2015-09-28","max":9.6091,"avg":4.4194,"min":1.4964}],"wind":[{"date":"2015-09-28","max":{"direction":86.6,"speed":103.66},"avg":{"direction":56.13,"speed":55.22},"min":{"direction":11.12,"speed":35.05}}],"humidity":[{"date":"2015-09-28","max":0.93,"avg":0.89,"min":0.8}]}
         * alert : [{"status":"预警中","code":"0903","description":"请有关单位和人员做好防范工作。","pubdate":"2016-07-25 17:10:00","location":"海南省白沙县","bound_coord":[116.39,39.93,116.1,40.2]}]
         */

        private String status;
        private HourlyBean hourly;
        private MinutelyBean minutely;
        private DailyBean daily;
        private List<AlertBean> alert;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public HourlyBean getHourly() {
            return hourly;
        }

        public void setHourly(HourlyBean hourly) {
            this.hourly = hourly;
        }

        public MinutelyBean getMinutely() {
            return minutely;
        }

        public void setMinutely(MinutelyBean minutely) {
            this.minutely = minutely;
        }

        public DailyBean getDaily() {
            return daily;
        }

        public void setDaily(DailyBean daily) {
            this.daily = daily;
        }

        public List<AlertBean> getAlert() {
            return alert;
        }

        public void setAlert(List<AlertBean> alert) {
            this.alert = alert;
        }

        public static class HourlyBean {
            /**
             * status : ok
             * pm25 : [{"value":8,"datetime":"2015-09-28 13:00"}]
             * description : 暴雨渐大，今天晚间21点钟后大雨，其后阴
             * skycon : [{"value":"RAIN","datetime":"2015-09-28 13:00"}]
             * cloudrate : [{"value":1,"datetime":"2015-09-28 13:00"}]
             * humidity : [{"value":0.92,"datetime":"2015-09-28 13:00"}]
             * wind : [{"direction":23.09,"speed":80.66,"datetime":"2015-09-28 13:00"}]
             * temperature : [{"value":25.11,"datetime":"2015-09-28 13:00"}]
             */

            private String status;
            private String description;
            private List<Pm25Bean> pm25;
            private List<SkyconBean> skycon;
            private List<CloudrateBean> cloudrate;
            private List<AqiBean> aqi;
            private List<HumidityBean> humidity;
            private List<WindBean> wind;
            private List<TemperatureBean> temperature;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<Pm25Bean> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25Bean> pm25) {
                this.pm25 = pm25;
            }

            public List<SkyconBean> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBean> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBean> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBean> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBean> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBean> aqi) {
                this.aqi = aqi;
            }

            public List<HumidityBean> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBean> humidity) {
                this.humidity = humidity;
            }

            public List<WindBean> getWind() {
                return wind;
            }

            public void setWind(List<WindBean> wind) {
                this.wind = wind;
            }

            public List<TemperatureBean> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBean> temperature) {
                this.temperature = temperature;
            }

            public static class Pm25Bean {
                /**
                 * value : 8
                 * datetime : 2015-09-28 13:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class SkyconBean {
                /**
                 * value : RAIN
                 * datetime : 2015-09-28 13:00
                 */

                private String value;
                private String datetime;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class CloudrateBean {
                /**
                 * value : 1
                 * datetime : 2015-09-28 13:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class AqiBean{
                /**
                 * value : 33.0
                 * datetime : 2015-09-28 13:00
                 */
                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class HumidityBean {
                /**
                 * value : 0.92
                 * datetime : 2015-09-28 13:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class WindBean {
                /**
                 * direction : 23.09
                 * speed : 80.66
                 * datetime : 2015-09-28 13:00
                 */

                private double direction;
                private double speed;
                private String datetime;

                public double getDirection() {
                    return direction;
                }

                public void setDirection(double direction) {
                    this.direction = direction;
                }

                public double getSpeed() {
                    return speed;
                }

                public void setSpeed(double speed) {
                    this.speed = speed;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class TemperatureBean {
                /**
                 * value : 25.11
                 * datetime : 2015-09-28 13:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }
        }

        public static class MinutelyBean {
            /**
             * status : ok
             * precipitation : [0.2812,0.2959,0.3125,0.3288,0.3427,0.3519,0.3542,0.3481,0.3351,0.3176,0.2978,0.278,0.2604,0.2469,0.2371,0.2302,0.2254,0.2218,0.2188,0.2154,0.2119,0.2082,0.2046,0.2011,0.1979,0.1951,0.1928,0.1908,0.1893,0.1882,0.1875,0.1872,0.1873,0.1874,0.1876,0.1877,0.1875,0.187,0.1867,0.187,0.1886,0.1921,0.1979,0.2064,0.2166,0.2273,0.2372,0.2452,0.25,0.2507,0.248,0.2425,0.2352,0.227,0.2188,0.2111,0.2044,0.1985,0.1937,0.19]
             * datasource : radar
             * description : 16分钟后雨渐小，转为小雨
             */

            private String status;
            private String datasource;
            private String description;
            private List<Double> precipitation;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDatasource() {
                return datasource;
            }

            public void setDatasource(String datasource) {
                this.datasource = datasource;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<Double> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<Double> precipitation) {
                this.precipitation = precipitation;
            }
        }

        public static class DailyBean {
            /**
             * status : ok
             * astro : [{"date":"2015-09-28","sunset":{"time":"17:44"},"sunrise":{"time":"05:44"}}]
             * temperature : [{"date":"2015-09-28","max":26.86,"avg":25.39,"min":24.97}]
             * pm25 : [{"date":"2015-09-28","max":13,"avg":6,"min":0}]
             * skycon : [{"date":"2015-09-28","value":"RAIN"}]
             * cloudrate : [{"date":"2015-09-28","max":1,"avg":1,"min":1}]
             * aqi : [{"date":"2015-09-28","max":38,"avg":32,"min":26}]
             * precipitation : [{"date":"2015-09-28","max":9.6091,"avg":4.4194,"min":1.4964}]
             * wind : [{"date":"2015-09-28","max":{"direction":86.6,"speed":103.66},"avg":{"direction":56.13,"speed":55.22},"min":{"direction":11.12,"speed":35.05}}]
             * humidity : [{"date":"2015-09-28","max":0.93,"avg":0.89,"min":0.8}]
             */

            private String status;
            private List<AstroBean> astro;
            private List<TemperatureBeanX> temperature;
            private List<Pm25BeanX> pm25;
            private List<SkyconBeanX> skycon;
            private List<CloudrateBeanX> cloudrate;
            private List<AqiBean> aqi;
            private List<PrecipitationBean> precipitation;
            private List<WindBeanX> wind;
            private List<HumidityBeanX> humidity;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<AstroBean> getAstro() {
                return astro;
            }

            public void setAstro(List<AstroBean> astro) {
                this.astro = astro;
            }

            public List<TemperatureBeanX> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBeanX> temperature) {
                this.temperature = temperature;
            }

            public List<Pm25BeanX> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25BeanX> pm25) {
                this.pm25 = pm25;
            }

            public List<SkyconBeanX> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBeanX> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBeanX> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBeanX> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBean> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBean> aqi) {
                this.aqi = aqi;
            }

            public List<PrecipitationBean> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationBean> precipitation) {
                this.precipitation = precipitation;
            }

            public List<WindBeanX> getWind() {
                return wind;
            }

            public void setWind(List<WindBeanX> wind) {
                this.wind = wind;
            }

            public List<HumidityBeanX> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBeanX> humidity) {
                this.humidity = humidity;
            }
            // 日出日落
            public static class AstroBean {
                /**
                 * date : 2015-09-28
                 * sunset : {"time":"17:44"}
                 * sunrise : {"time":"05:44"}
                 */

                private String date;
                private SunsetBean sunset;
                private SunriseBean sunrise;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public SunsetBean getSunset() {
                    return sunset;
                }

                public void setSunset(SunsetBean sunset) {
                    this.sunset = sunset;
                }

                public SunriseBean getSunrise() {
                    return sunrise;
                }

                public void setSunrise(SunriseBean sunrise) {
                    this.sunrise = sunrise;
                }

                public static class SunsetBean {
                    /**
                     * time : 17:44
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }

                public static class SunriseBean {
                    /**
                     * time : 05:44
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }
            }
            // 温度
            public static class TemperatureBeanX {
                /**
                 * date : 2015-09-28
                 * max : 26.86
                 * avg : 25.39
                 * min : 24.97
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
            // pm25
            public static class Pm25BeanX {
                /**
                 * date : 2015-09-28
                 * max : 13
                 * avg : 6
                 * min : 0
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
            // 天气概况
            public static class SkyconBeanX {
                /**
                 * date : 2015-09-28
                 * value : RAIN
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
            // 云量
            public static class CloudrateBeanX {
                /**
                 * date : 2015-09-28
                 * max : 1
                 * avg : 1
                 * min : 1
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
            // AQI
            public static class AqiBean {
                /**
                 * date : 2015-09-28
                 * max : 38
                 * avg : 32
                 * min : 26
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
            // 降水强度
            public static class PrecipitationBean {
                /**
                 * date : 2015-09-28
                 * max : 9.6091
                 * avg : 4.4194
                 * min : 1.4964
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
            // 风
            public static class WindBeanX {
                /**
                 * date : 2015-09-28
                 * max : {"direction":86.6,"speed":103.66}
                 * avg : {"direction":56.13,"speed":55.22}
                 * min : {"direction":11.12,"speed":35.05}
                 */

                private String date;
                private MaxBean max;
                private AvgBean avg;
                private MinBean min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxBean getMax() {
                    return max;
                }

                public void setMax(MaxBean max) {
                    this.max = max;
                }

                public AvgBean getAvg() {
                    return avg;
                }

                public void setAvg(AvgBean avg) {
                    this.avg = avg;
                }

                public MinBean getMin() {
                    return min;
                }

                public void setMin(MinBean min) {
                    this.min = min;
                }

                public static class MaxBean {
                    /**
                     * direction : 86.6
                     * speed : 103.66
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class AvgBean {
                    /**
                     * direction : 56.13
                     * speed : 55.22
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class MinBean {
                    /**
                     * direction : 11.12
                     * speed : 35.05
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }
            }
            // 相对湿度
            public static class HumidityBeanX {
                /**
                 * date : 2015-09-28
                 * max : 0.93
                 * avg : 0.89
                 * min : 0.8
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }
        }

        public static class AlertBean {
            /**
             * status : 预警中
             * code : 0903
             * description : 请有关单位和人员做好防范工作。
             * pubdate : 2016-07-25 17:10:00
             * location : 海南省白沙县
             * bound_coord : [116.39,39.93,116.1,40.2]
             */

            private String status;
            private String code;
            private String description;
            private String pubdate;
            private String location;
            private List<Double> bound_coord;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public List<Double> getBound_coord() {
                return bound_coord;
            }

            public void setBound_coord(List<Double> bound_coord) {
                this.bound_coord = bound_coord;
            }
        }
    }
}
