package travel.tour.model;

/**
 * Properties
 * Properties class
 * Created by Zakhar on 30.05.2016.
 */
public abstract class Constants {
    public final static String PARAMETER_REGULAR = "(\\d|\\w)+";
    public final static String VOUCHER_REGULAR = "(([0-9])+){1}(([,])+([a-zA-Z])+){2}(([,])+(([0-9])+)){2}(([,])+([a-zA-Z])+){1,4}";
    public final static String TOUR_TYPE_REGULAR = "(?<=^\\d+\\W)(\\w+)";
    public final static String FILE_DIR = "D:\\epamPro\\first_project\\TourPackages\\TourPackages.csv";

    public final static int NO_ELEMENTS = 0;

    public final static String REST_TOUR_TYPE = "rest";
    public final static String CRUISE_TOUR_TYPE = "cruise";
    public final static String EXCURSION_TOUR_TYPE = "excursion";
    public final static String TREATMENT_TOUR_TYPE = "treatment";
    public final static String NO_TYPE_EXCEPTION = "no such types!";
    public final static String REQUEST_SEPARATOR = ": ";
    public final static String CONTINUE_ADVANCED_SEARCH_MESSAGE = "continue advanced search? y/n: ";
    public final static String YES_REQUEST = "y";
    public final static String NO_RESULTS_MESSAGE = "no results on your request!";
    public final static String RESULT_BORDER = " | ";
    public final static String FILE_NOT_FOUND = "file not found, or problems with IO";
    public final static String PARAMETERS_NOT_VALID = "parameters are not valid!";

    public final static int VOUCHERS_NUMBER_ARRAY = 0;
    public final static int COUNTRY_ARRAY = 2;
    public final static int TRANSPORT_ARRAY = 5;
    public final static int FOOD_TYPE_ARRAY = 7;
    public final static int FOOD_TYPE_EXC_ARRAY = 8;
    public final static int DAYS_NUMBER_ARRAY = 4;
    public final static int PRICE_ARRAY = 3;
    public final static int CRUISE_EXCURSION_TYPE_ARRAY = 6;
    public final static int HOTEL_EXC_ARRAY = 7;
    public final static int HOTEL_REST_ARRAY = 6;

    public final static String VOUCHERS_NUMBER_KEY = "vouchers_number";
    public final static String TOUR_TYPE_KEY = "tour_type";
    public final static String COUNTRY_KEY = "country";
    public final static String TRANSPORT_KEY = "transport";
    public final static String CRUISE_TYPE_KEY = "cruise_type";
    public final static String EXCURSION_TYPE_KEY = "excursion_type";
    public final static String TREATMENT_TYPE_KEY = "treatment_type";
    public final static String HOTEL_KEY = "hotel";
    public final static String FOOD_KEY = "food";
    public final static String DAYS_NUMBER_KEY = "days_number";
    public final static String PRICE_KEY = "price";
}
