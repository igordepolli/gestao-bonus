package br.ufes.main;

import br.ufes.presenter.MainScreenPresenter;

public class Main {

    public static void main(String[] args) {

        try {
            MainScreenPresenter.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
