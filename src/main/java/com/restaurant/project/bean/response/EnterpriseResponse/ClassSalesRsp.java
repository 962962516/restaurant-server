package com.restaurant.project.bean.response.EnterpriseResponse;

public class ClassSalesRsp {
    private int combo;
    private int comboPrice;
    private int chickenBugger;
    private int chickenBuggerPrice;
    private int beefBugger;
    private int beefBuggerPrice;
    private int AnGeSi;
    private int AnGeSiPrice;
    private int chicken;
    private int chickenPrice;
    private int snack;
    private int snackPrice;
    private int coffee;
    private int coffeePrice;

    public ClassSalesRsp(int combo, int comboPrice, int chickenBugger, int chickenBuggerPrice, int beefBugger, int beefBuggerPrice, int anGeSi, int anGeSiPrice, int chicken, int chickenPrice, int snack, int snackPrice, int coffee, int coffeePrice) {
        this.combo = combo;
        this.comboPrice = comboPrice;
        this.chickenBugger = chickenBugger;
        this.chickenBuggerPrice = chickenBuggerPrice;
        this.beefBugger = beefBugger;
        this.beefBuggerPrice = beefBuggerPrice;
        AnGeSi = anGeSi;
        AnGeSiPrice = anGeSiPrice;
        this.chicken = chicken;
        this.chickenPrice = chickenPrice;
        this.snack = snack;
        this.snackPrice = snackPrice;
        this.coffee = coffee;
        this.coffeePrice = coffeePrice;
    }

    public int getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(int comboPrice) {
        this.comboPrice = comboPrice;
    }

    public int getChickenBuggerPrice() {
        return chickenBuggerPrice;
    }

    public void setChickenBuggerPrice(int chickenBuggerPrice) {
        this.chickenBuggerPrice = chickenBuggerPrice;
    }

    public int getBeefBuggerPrice() {
        return beefBuggerPrice;
    }

    public void setBeefBuggerPrice(int beefBuggerPrice) {
        this.beefBuggerPrice = beefBuggerPrice;
    }

    public int getAnGeSiPrice() {
        return AnGeSiPrice;
    }

    public void setAnGeSiPrice(int anGeSiPrice) {
        AnGeSiPrice = anGeSiPrice;
    }

    public int getChickenPrice() {
        return chickenPrice;
    }

    public void setChickenPrice(int chickenPrice) {
        this.chickenPrice = chickenPrice;
    }

    public int getSnackPrice() {
        return snackPrice;
    }

    public void setSnackPrice(int snackPrice) {
        this.snackPrice = snackPrice;
    }

    public int getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(int coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public ClassSalesRsp() {
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getChickenBugger() {
        return chickenBugger;
    }

    public void setChickenBugger(int chickenBugger) {
        this.chickenBugger = chickenBugger;
    }

    public int getBeefBugger() {
        return beefBugger;
    }

    public void setBeefBugger(int beefBugger) {
        this.beefBugger = beefBugger;
    }

    public int getAnGeSi() {
        return AnGeSi;
    }

    public void setAnGeSi(int anGeSi) {
        AnGeSi = anGeSi;
    }

    public int getChicken() {
        return chicken;
    }

    public void setChicken(int chicken) {
        this.chicken = chicken;
    }

    public int getSnack() {
        return snack;
    }

    public void setSnack(int snack) {
        this.snack = snack;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }
}
