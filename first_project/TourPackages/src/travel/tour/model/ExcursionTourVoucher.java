package travel.tour.model;

import travel.tour.model.enumeration.*;
import java.util.ArrayList;
import static travel.tour.model.Constants.*;
/**
 * ExcursionTourVoucher
 * Object of current type
 * Created by Zakhar on 28.05.2016.
 */
public class ExcursionTourVoucher extends TourPackage {
    private HotelEnum hotelEnum;
    private ExcursionTypeEnum excursionTypeEnum;
    ArrayList<String> resultArrayListString;

    public ExcursionTourVoucher (ArrayList<String> resultArrayListString) {
        super (resultArrayListString.get(VOUCHERS_NUMBER_ARRAY), resultArrayListString.get(COUNTRY_ARRAY), resultArrayListString.get(TRANSPORT_ARRAY)
                , resultArrayListString.get(FOOD_TYPE_EXC_ARRAY), resultArrayListString.get(DAYS_NUMBER_ARRAY), resultArrayListString.get(PRICE_ARRAY));
        this.resultArrayListString = resultArrayListString;
        this.hotelEnum = HotelEnum.valueOf(resultArrayListString.get(HOTEL_EXC_ARRAY).toUpperCase());
        this.excursionTypeEnum = ExcursionTypeEnum.valueOf(resultArrayListString.get(CRUISE_EXCURSION_TYPE_ARRAY).toUpperCase());
    }

    public HotelEnum getHotelEnum() {
        return hotelEnum;
    }

    public ExcursionTypeEnum getExcursionTypeEnum() {
        return excursionTypeEnum;
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
