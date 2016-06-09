package travel.tour.model;

import travel.tour.model.enumeration.*;
import java.util.ArrayList;

import static travel.tour.model.Constants.*;

/**
 * RestTourVoucher
 * Object of current type
 * Created by Zakhar on 28.05.2016.
 */
public class RestTourVoucher extends TourPackage {
    private HotelEnum hotelEnum;
    ArrayList<String> resultArrayListString;

    public RestTourVoucher(ArrayList<String> resultArrayListString) {
        super (resultArrayListString.get(VOUCHERS_NUMBER_ARRAY), resultArrayListString.get(COUNTRY_ARRAY), resultArrayListString.get(TRANSPORT_ARRAY)
                , resultArrayListString.get(FOOD_TYPE_ARRAY), resultArrayListString.get(DAYS_NUMBER_ARRAY), resultArrayListString.get(PRICE_ARRAY));
        this.resultArrayListString = resultArrayListString;
        this.hotelEnum = HotelEnum.valueOf(resultArrayListString.get(HOTEL_REST_ARRAY).toUpperCase());
    }

    public HotelEnum getHotelEnum() {
        return hotelEnum;
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
