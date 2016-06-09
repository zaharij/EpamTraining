package travel.tour.model;

import travel.tour.model.enumeration.CruiseTypeEnum;
import java.util.ArrayList;
import static travel.tour.model.Constants.*;

/**
 * CruiseTourVoucher
 * Object of current type
 * Created by Zakhar on 28.05.2016.
 */
public class CruiseTourVoucher extends TourPackage {
    private CruiseTypeEnum cruiseTypeEnum;
    ArrayList<String> resultArrayListString;

    public CruiseTourVoucher(ArrayList<String> resultArrayListString) {
        super (resultArrayListString.get(VOUCHERS_NUMBER_ARRAY), resultArrayListString.get(COUNTRY_ARRAY), resultArrayListString.get(TRANSPORT_ARRAY)
                , resultArrayListString.get(FOOD_TYPE_ARRAY), resultArrayListString.get(DAYS_NUMBER_ARRAY), resultArrayListString.get(PRICE_ARRAY));
        this.resultArrayListString = resultArrayListString;
        this.cruiseTypeEnum = CruiseTypeEnum.valueOf(resultArrayListString.get(CRUISE_EXCURSION_TYPE_ARRAY).toUpperCase());
    }

    public CruiseTypeEnum getCruiseTypeEnum() {
        return cruiseTypeEnum;
    }

    /**
     * getResultArrayListString
     * simple getter
     * @return ArrayList of object parameters
     */
    @Override
    public ArrayList<String> getResultArrayListString() {
        return resultArrayListString;
    }
}
