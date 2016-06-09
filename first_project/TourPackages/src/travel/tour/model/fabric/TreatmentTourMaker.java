package travel.tour.model.fabric;

import travel.tour.model.TourPackage;
import travel.tour.model.TreatmentTourVoucher;
import java.util.ArrayList;

/**
 * TreatmentTourMaker
 * implementation of Fabric Method pattern
 * Created by Zakhar on 30.05.2016.
 */
public class TreatmentTourMaker implements TourMaker{

    /**
     * createTourVoucher
     * creating object of Treatment type
     * @param resultArrayListString - given parameters
     * @return current object
     */
    @Override
    public TourPackage createTourVoucher(ArrayList<String> resultArrayListString) {
        return new TreatmentTourVoucher(resultArrayListString);
    }
}
