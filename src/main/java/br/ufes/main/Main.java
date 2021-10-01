package br.ufes.main;

import br.ufes.presenter.MainScreenPresenter;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        try {
            MainScreenPresenter.getInstance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }
}
