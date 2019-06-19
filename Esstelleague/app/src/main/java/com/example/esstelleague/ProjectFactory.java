package com.example.esstelleague;

import java.util.ArrayList;

/**
 * @author Daphne
 * @author Lucas
 * The ProjectFactory creates images + strings for in the RecyclerView.
 */

class ProjectFactory {

    private ArrayList<Attraction> mDataset = new ArrayList<>();
    private static ProjectFactory mInstance = null;

    /**
     * @return ProjectFactory object.
     */

    static ProjectFactory getInstance() {
        if( mInstance == null ) {
            mInstance = new ProjectFactory();
        }
        return mInstance;
    }

    /**
     * On construction construct data set.
     */

    private ProjectFactory() {
        constructDataSet();
    }

    /**
     * Receive the dataset.
     * @return Receive the dataset.
     */

    ArrayList<Attraction> getDataset() {
        return mDataset;
    }

    /**
     * Construct the dataset with images and corresponding Strings.
     */

    private void constructDataSet() {
        this.mDataset.add(new Attraction("Baron", "rv_baron", R.string.rv_baron));
        this.mDataset.add(new Attraction("Bob", "rv_bob", R.string.rv_bob));
        this.mDataset.add(new Attraction("De zwevende belg", "rv_de_zwevende_belg", R.string.rv_dezwevendebelg));
        this.mDataset.add(new Attraction("Droomvlucht", "rv_droomvlucht", R.string.rv_droomvlucht));
        this.mDataset.add(new Attraction("Fata morgana", "rv_fata_morgana", R.string.rv_fatamorgana));
        this.mDataset.add(new Attraction("Festival overval", "rv_festival_overal", R.string.rv_festivaloveral));
        this.mDataset.add(new Attraction("Johan en de eenhoorn", "rv_johan_en_de_eenhoorn", R.string.rv_johanendeeenhoorn));
        this.mDataset.add(new Attraction("Monsieur connibale", "rv_monsieur_cannibale", R.string.rv_monsieurcannibale));
        this.mDataset.add(new Attraction("Python", "rv_python", R.string.rv_python));
        this.mDataset.add(new Attraction("Symbolica", "rv_symbolica", R.string.rv_symbolica));
        this.mDataset.add(new Attraction("Vogel rok", "rv_vogel_rok", R.string.rv_vogelrok));

    }
}
