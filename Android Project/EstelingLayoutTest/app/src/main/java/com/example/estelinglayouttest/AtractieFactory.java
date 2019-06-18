package com.example.estelinglayouttest;

import java.util.ArrayList;

public class AtractieFactory {

    private ArrayList<Attractie> dataset = new ArrayList<>();
    private static AtractieFactory instance = null;

    public static AtractieFactory getInstance() {
        if( instance == null ) {
            instance = new AtractieFactory();
        }
        return instance;
    }

    private AtractieFactory() {
        constructDataSet();
    }

    public ArrayList<Attractie> getDataset() {
        return dataset;
    }

    private void constructDataSet() {
        this.dataset.add(new Attractie("Baron", "baron", R.string.baron));
        this.dataset.add(new Attractie("Bob", "bob", R.string.bob));
        this.dataset.add(new Attractie("De zwevende belg", "de_zwevende_belg", R.string.dezwevendebelg));
        this.dataset.add(new Attractie("Droomvlucht", "droomvlucht", R.string.droomvlucht));
        this.dataset.add(new Attractie("Fata morgana", "fata_morgana", R.string.fatamorgana));
        this.dataset.add(new Attractie("Festival overval", "festival_overal", R.string.festivaloveral));
        this.dataset.add(new Attractie("Johan en de eenhoorn", "johan_en_de_eenhoorn", R.string.johanendeeenhoorn));
        this.dataset.add(new Attractie("Monsieur connibale", "monsieur_cannibale", R.string.monsieurcannibale));
        this.dataset.add(new Attractie("Python", "python", R.string.python));
        this.dataset.add(new Attractie("Symbolica", "symbolica", R.string.symbolica));
        this.dataset.add(new Attractie("Vogel rok", "vogel_rok", R.string.vogelrok));

    }
}
