package com.example.esstelleague;

import java.util.ArrayList;

public class ProjectFactory {

    private ArrayList<Attraction> dataset = new ArrayList<>();
    private static ProjectFactory instance = null;

    public static ProjectFactory getInstance() {
        if( instance == null ) {
            instance = new ProjectFactory();
        }
        return instance;
    }

    private ProjectFactory() {
        constructDataSet();
    }

    public ArrayList<Attraction> getDataset() {
        return dataset;
    }

    private void constructDataSet() {
        this.dataset.add(new Attraction("Baron", "rv_baron", R.string.rv_baron));
        this.dataset.add(new Attraction("Bob", "rv_bob", R.string.rv_bob));
        this.dataset.add(new Attraction("De zwevende belg", "rv_de_zwevende_belg", R.string.rv_dezwevendebelg));
        this.dataset.add(new Attraction("Droomvlucht", "rv_droomvlucht", R.string.rv_droomvlucht));
        this.dataset.add(new Attraction("Fata morgana", "rv_fata_morgana", R.string.rv_fatamorgana));
        this.dataset.add(new Attraction("Festival overval", "rv_festival_overal", R.string.rv_festivaloveral));
        this.dataset.add(new Attraction("Johan en de eenhoorn", "rv_johan_en_de_eenhoorn", R.string.rv_johanendeeenhoorn));
        this.dataset.add(new Attraction("Monsieur connibale", "rv_monsieur_cannibale", R.string.rv_monsieurcannibale));
        this.dataset.add(new Attraction("Python", "rv_python", R.string.rv_python));
        this.dataset.add(new Attraction("Symbolica", "rv_symbolica", R.string.rv_symbolica));
        this.dataset.add(new Attraction("Vogel rok", "rv_vogel_rok", R.string.rv_vogelrok));

    }
}
