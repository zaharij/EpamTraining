package travel.tour.model.fabric;

import travel.tour.model.RestTourVoucher;
import travel.tour.model.TourPackage;
import java.util.ArrayList;

/**
 * RestTourMaker
 * implementation of Fabric Method pattern
 * creating an object of RestTour type
 * Created by Zakhar on 30.05.2016.
 */
public class RestTourMaker implements TourMaker{

    /**
     * createTourVoucher
     * creating object of RestTour type
     * @param resultArrayListString given parameters for object
     * @return current object
     */
    @Override
    public TourPackage createTourVoucher(ArrayList<String> resultArrayListString) {
        return new RestTourVoucher(resultArrayListString);
    }
}
