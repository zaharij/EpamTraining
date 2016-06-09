package travel.tour.model.fabric;

import travel.tour.model.ExcursionTourVoucher;
import travel.tour.model.TourPackage;

import java.util.ArrayList;

/**
 * ExcursionTourMaker
 * implementations of pattern Fabric Method
 * creation object of ExcursionTour type
 * Created by Zakhar on 30.05.2016.
 */
public class ExcursionTourMaker implements TourMaker{

    /**
     * createTourVoucher
     * creating object of ExcursionTour type
     * @param resultArrayListString - given parameters for object
     * @return current object
     */
    @Override
    public TourPackage createTourVoucher(ArrayList<String> resultArrayListString) {
        return new ExcursionTourVoucher(resultArrayListString);
    }
}
