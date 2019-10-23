package com.finance.tradingDataService.domain.instrumentHistoryPoints;


public class StockHistoryPoint {
      private String currency;
      private String price;
      private String price_open;
      private String day_high;
      private String day_low;
      private String week_high_52;
      private String week_low_52;
      private String day_change;
      private String change_pct;
      private String close_yesterday;
      private String market_cap;
      private String volume;
      private String volume_avg;
      private String shares;
      private String stock_exchange_long;
      private String stock_exchange_short;
      private String timezone;
      private String timezone_name;
      private String gmt_offset;
      private String last_trade_time;
      private String pe;
      private String eps;

      public StockHistoryPoint(String currency, String price, String price_open, String day_high, String day_low, String week_high_52, String week_low_52, String day_change, String change_pct, String close_yesterday, String market_cap, String volume, String volume_avg, String shares, String stock_exchange_long, String stock_exchange_short, String timezone, String timezone_name, String gmt_offset, String last_trade_time, String pe, String eps) {
            this.currency = currency;
            this.price = price;
            this.price_open = price_open;
            this.day_high = day_high;
            this.day_low = day_low;
            this.week_high_52 = week_high_52;
            this.week_low_52 = week_low_52;
            this.day_change = day_change;
            this.change_pct = change_pct;
            this.close_yesterday = close_yesterday;
            this.market_cap = market_cap;
            this.volume = volume;
            this.volume_avg = volume_avg;
            this.shares = shares;
            this.stock_exchange_long = stock_exchange_long;
            this.stock_exchange_short = stock_exchange_short;
            this.timezone = timezone;
            this.timezone_name = timezone_name;
            this.gmt_offset = gmt_offset;
            this.last_trade_time = last_trade_time;
            this.pe = pe;
            this.eps = eps;
      }

      public String getCurrency() {
            return currency;
      }

      public String getPrice() {
            return price;
      }

      public String getPrice_open() {
            return price_open;
      }

      public String getDay_high() {
            return day_high;
      }

      public String getDay_low() {
            return day_low;
      }

      public String getWeek_high_52() {
            return week_high_52;
      }

      public String getWeek_low_52() {
            return week_low_52;
      }

      public String getDay_change() {
            return day_change;
      }

      public String getChange_pct() {
            return change_pct;
      }

      public String getClose_yesterday() {
            return close_yesterday;
      }

      public String getMarket_cap() {
            return market_cap;
      }

      public String getVolume() {
            return volume;
      }

      public String getVolume_avg() {
            return volume_avg;
      }

      public String getShares() {
            return shares;
      }

      public String getStock_exchange_long() {
            return stock_exchange_long;
      }

      public String getStock_exchange_short() {
            return stock_exchange_short;
      }

      public String getTimezone() {
            return timezone;
      }

      public String getTimezone_name() {
            return timezone_name;
      }

      public String getGmt_offset() {
            return gmt_offset;
      }

      public String getLast_trade_time() {
            return last_trade_time;
      }

      public String getPe() {
            return pe;
      }

      public String getEps() {
            return eps;
      }
}
