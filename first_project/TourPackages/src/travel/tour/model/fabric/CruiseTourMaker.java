package travel.tour.model.fabric;

import travel.tour.model.CruiseTourVoucher;
import travel.tour.model.TourPackage;

import java.util.ArrayList;

/**
 * CruiseTourMaker
 * implementations of pattern Fabric Method
 * generates cruise tour
 * Created by Zakhar on 30.05.2016.
 */
public class CruiseTourMaker implements TourMaker{

    /**
     * createTourVoucher
     * creates object of CruiseTour type
     * @param resultArrayListString given parameters for object
     * @return object of current type
     */
    @Override
    public TourPackage createTourVoucher(ArrayList<String> resultArrayListString) {
        return new CruiseTourVoucher(resultArrayListString);
    }
}
