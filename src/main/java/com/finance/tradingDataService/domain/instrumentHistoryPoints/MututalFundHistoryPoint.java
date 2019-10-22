package com.finance.tradingDataService.domain.instrumentHistoryPoints;

@Entity
public class MututalFundHistoryPoint {
    private String price;
    private String return_ytd;
    private String net_assets;
    private String change_asset_value;
    private String change_pct;
    private String yield_pct;
    private String return_day;
    private String return_1week;
    private String return_4week;
    private String return_13week;
    private String return_52week;
    private String return_156week;
    private String return_260week;
    private String income_dividend;
    private String income_dividend_date;
    private String capital_gain;
    private String expense_ratio;

    public MututalFundHistoryPoint(String price, String return_ytd, String net_assets, String change_asset_value, String change_pct, String yield_pct, String return_day, String return_1week, String return_4week, String return_13week, String return_52week, String return_156week, String return_260week, String income_dividend, String income_dividend_date, String capital_gain, String expense_ratio) {
        this.price = price;
        this.return_ytd = return_ytd;
        this.net_assets = net_assets;
        this.change_asset_value = change_asset_value;
        this.change_pct = change_pct;
        this.yield_pct = yield_pct;
        this.return_day = return_day;
        this.return_1week = return_1week;
        this.return_4week = return_4week;
        this.return_13week = return_13week;
        this.return_52week = return_52week;
        this.return_156week = return_156week;
        this.return_260week = return_260week;
        this.income_dividend = income_dividend;
        this.income_dividend_date = income_dividend_date;
        this.capital_gain = capital_gain;
        this.expense_ratio = expense_ratio;
    }

    public String getPrice() {
        return price;
    }

    public String getReturn_ytd() {
        return return_ytd;
    }

    public String getNet_assets() {
        return net_assets;
    }

    public String getChange_asset_value() {
        return change_asset_value;
    }

    public String getChange_pct() {
        return change_pct;
    }

    public String getYield_pct() {
        return yield_pct;
    }

    public String getReturn_day() {
        return return_day;
    }

    public String getReturn_1week() {
        return return_1week;
    }

    public String getReturn_4week() {
        return return_4week;
    }

    public String getReturn_13week() {
        return return_13week;
    }

    public String getReturn_52week() {
        return return_52week;
    }

    public String getReturn_156week() {
        return return_156week;
    }

    public String getReturn_260week() {
        return return_260week;
    }

    public String getIncome_dividend() {
        return income_dividend;
    }

    public String getIncome_dividend_date() {
        return income_dividend_date;
    }

    public String getCapital_gain() {
        return capital_gain;
    }

    public String getExpense_ratio() {
        return expense_ratio;
    }
}
