package travel.tour.model.fabric;

import travel.tour.model.TourPackage;
import java.util.ArrayList;

/**
 * TourMaker
 * main interface for implementation Fabric Method pattern
 * Created by Zakhar on 30.05.2016.
 */
public interface TourMaker {

    /**
     * createTourVoucher
     * method for overriding
     * creating object of TourPackages type
     * @param resultArrayListString - given parameters
     * @return current object
     */
    public TourPackage createTourVoucher(ArrayList<String> resultArrayListString);
}
