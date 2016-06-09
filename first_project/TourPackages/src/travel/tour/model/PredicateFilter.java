package travel.tour.model;

import java.util.LinkedHashMap;
import java.util.function.Predicate;
import static travel.tour.model.Constants.*;

/**
 * Created by Zakhar on 05.06.2016.
 */
public class PredicateFilter {
    LinkedHashMap requestUserControllerMap;
    Predicate<TourPackage> filterCountryPredicate = (e -> requestUserControllerMap.get(COUNTRY_KEY).toString().equalsIgnoreCase(String.valueOf(e.getCountryEnum())));
    Predicate<TourPackage> filterVouchersNumberPredicate = (e -> Integer.parseInt((String) requestUserControllerMap.get(VOUCHERS_NUMBER_KEY)) <= e.getVoucherNumbers());
    Predicate<TourPackage> filterPricePredicate = (e -> Integer.parseInt((String) requestUserControllerMap.get(PRICE_KEY)) >= e.getPrice());
    Predicate<TourPackage> filterDaysNumberPredicate = (e -> Integer.parseInt((String) requestUserControllerMap.get(DAYS_NUMBER_KEY)) >= e.getDaysNumber());
    Predicate<TourPackage> filterTransportPredicate = (e -> requestUserControllerMap.get(TRANSPORT_KEY) == null? true: requestUserControllerMap.get(TRANSPORT_KEY).toString().equalsIgnoreCase(String.valueOf(e.getTransportEnum())));
    Predicate<TourPackage> filterFoodPredicate = (e -> requestUserControllerMap.get(FOOD_KEY) == null? true: requestUserControllerMap.get(FOOD_KEY).toString().equalsIgnoreCase(String.valueOf(e.getFoodTypeEnum())));
    Predicate<CruiseTourVoucher> filterCruiseTypePredicate = (e -> requestUserControllerMap.get(CRUISE_TYPE_KEY) == null? true: requestUserControllerMap.get(CRUISE_TYPE_KEY).toString().equalsIgnoreCase(String.valueOf(e.getCruiseTypeEnum())));
    Predicate<ExcursionTourVoucher> filterExcursionTypePredicate = (e -> requestUserControllerMap.get(EXCURSION_TYPE_KEY) == null? true: requestUserControllerMap.get(EXCURSION_TYPE_KEY).toString().equalsIgnoreCase(String.valueOf(e.getExcursionTypeEnum())));
    Predicate<TreatmentTourVoucher> filterTreatmentTypePredicate = (e -> requestUserControllerMap.get(TREATMENT_TYPE_KEY) == null? true: requestUserControllerMap.get(TREATMENT_TYPE_KEY).toString().equalsIgnoreCase(String.valueOf(e.getTreatmentTypeEnum())));
    Predicate<RestTourVoucher> filterHotelTypeRestPredicate = (e -> requestUserControllerMap.get(HOTEL_KEY) == null? true: requestUserControllerMap.get(HOTEL_KEY).toString().equalsIgnoreCase(String.valueOf(e.getHotelEnum())));
    Predicate<ExcursionTourVoucher> filterHotelTypeExcursionPredicate = (e -> requestUserControllerMap.get(HOTEL_KEY) == null? true: requestUserControllerMap.get(HOTEL_KEY).toString().equalsIgnoreCase(String.valueOf(e.getHotelEnum())));
    Predicate<TreatmentTourVoucher> filterHotelTypeTreatmentPredicate = (e -> requestUserControllerMap.get(HOTEL_KEY) == null? true: requestUserControllerMap.get(HOTEL_KEY).toString().equalsIgnoreCase(String.valueOf(e.getHotelEnum())));
    Predicate<TourPackage> allFilterPredicates = filterCountryPredicate.and(filterVouchersNumberPredicate).and(filterPricePredicate).and(filterDaysNumberPredicate).and(filterTransportPredicate).and(filterFoodPredicate);
    Predicate<ExcursionTourVoucher> allFilterExcursionPredicate = filterExcursionTypePredicate.and(filterHotelTypeExcursionPredicate).and(allFilterPredicates);
    Predicate<TreatmentTourVoucher> allFilterTreatmentPredicate = filterTreatmentTypePredicate.and(filterHotelTypeTreatmentPredicate).and(allFilterPredicates);
    Predicate<CruiseTourVoucher> allFilterCruisePredicate = filterCruiseTypePredicate.and(allFilterPredicates);
    Predicate<RestTourVoucher> allFilterRestPredicate = filterHotelTypeRestPredicate.and(allFilterPredicates);

    public PredicateFilter (LinkedHashMap requestUserControllerMap) {
        this.requestUserControllerMap = requestUserControllerMap;
    }

    public Predicate<ExcursionTourVoucher> getAllFilterExcursionPredicate() {
        return allFilterExcursionPredicate;
    }

    public Predicate<TreatmentTourVoucher> getAllFilterTreatmentPredicate() {
        return allFilterTreatmentPredicate;
    }

    public Predicate<CruiseTourVoucher> getAllFilterCruisePredicate() {
        return allFilterCruisePredicate;
    }

    public Predicate<RestTourVoucher> getAllFilterRestPredicate() {
        return allFilterRestPredicate;
    }
}
