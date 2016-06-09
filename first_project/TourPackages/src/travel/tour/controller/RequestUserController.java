package travel.tour.controller;

import travel.tour.model.TourPackageModel;
import travel.tour.view.RequestUserView;
import static travel.tour.model.Constants.*;
import java.io.IOException;
import java.util.*;

/**
 * RequestUserController
 * connect model with view
 * Created by Zakhar on 29.05.2016.
 */
public class RequestUserController {
    private LinkedHashMap<String, String> requestTourMap = new LinkedHashMap<String, String>(){{
        put(VOUCHERS_NUMBER_KEY, null);
        put(TOUR_TYPE_KEY, null);
        put(COUNTRY_KEY, null);
        put(PRICE_KEY, null);
        put(DAYS_NUMBER_KEY, null);
        put(TRANSPORT_KEY, null);
        put(CRUISE_TYPE_KEY, null);
        put(EXCURSION_TYPE_KEY, null);
        put(TREATMENT_TYPE_KEY, null);
        put(HOTEL_KEY, null);
        put(FOOD_KEY, null);
    }};
    private String inputString;
    RequestUserView userView = new RequestUserView();
    TourPackageModel tourPackageModel = new TourPackageModel();

    /**
     * requestUserController
     * connect model with view
     * @return LinkedHashMap - user's request
     */
    public LinkedHashMap requestUserController () {
        Set set = requestTourMap.entrySet();
        Iterator iteration = set.iterator();
        while(iteration.hasNext()) {
            Map.Entry mapEntry = (Map.Entry)iteration.next();
            Scanner scanIn = new Scanner(System.in);
            searchInput(requestTourMap, mapEntry, scanIn);
            if (mapEntry.getKey().equals(DAYS_NUMBER_KEY)){
                userView.printMessage(CONTINUE_ADVANCED_SEARCH_MESSAGE);
                inputString = scanIn.next();
                if (!(inputString.equalsIgnoreCase(YES_REQUEST))){
                    break;
                } else {
                    advancedSearch(requestTourMap, iteration, mapEntry, scanIn);
                }
            }
        }
        return requestTourMap;
    }

    /**
     * advancedSearch
     * view method for advanced search
     * @param requestTourMap - request parameters collection
     * @param iteration Iteration object
     * @param mapEntry Map.Entry object
     * @param scanIn Scanner object
     */
    private void advancedSearch(LinkedHashMap<String, String> requestTourMap, Iterator iteration, Map.Entry mapEntry, Scanner scanIn) {
         if (requestTourMap.get(TOUR_TYPE_KEY).equalsIgnoreCase(REST_TOUR_TYPE)){
            while (iteration.hasNext()){
                mapEntry = (Map.Entry)iteration.next();
                scanIn = new Scanner(System.in);
                if ((((String) mapEntry.getKey()).equalsIgnoreCase(CRUISE_TYPE_KEY)) || (((String) mapEntry.getKey()).equalsIgnoreCase(EXCURSION_TYPE_KEY))
                        || (((String) mapEntry.getKey()).equalsIgnoreCase(TREATMENT_TYPE_KEY))){
                    continue;
                }
                searchInput(requestTourMap, mapEntry, scanIn);
            }
        }else if (requestTourMap.get(TOUR_TYPE_KEY).equalsIgnoreCase(TREATMENT_TOUR_TYPE)){
            while (iteration.hasNext()){
                mapEntry = (Map.Entry)iteration.next();
                scanIn = new Scanner(System.in);
                if ((((String) mapEntry.getKey()).equalsIgnoreCase(CRUISE_TYPE_KEY)) || (((String) mapEntry.getKey()).equalsIgnoreCase(EXCURSION_TYPE_KEY))){
                    continue;
                }
                searchInput(requestTourMap, mapEntry, scanIn);
            }
        }else if (requestTourMap.get(TOUR_TYPE_KEY).equalsIgnoreCase(CRUISE_TOUR_TYPE)){
            while (iteration.hasNext()){
                mapEntry = (Map.Entry)iteration.next();
                scanIn = new Scanner(System.in);
                if ((((String) mapEntry.getKey()).equalsIgnoreCase(EXCURSION_TYPE_KEY)) || (((String) mapEntry.getKey()).equalsIgnoreCase(TREATMENT_TYPE_KEY))
                        || (((String) mapEntry.getKey()).equalsIgnoreCase(HOTEL_KEY))){
                    continue;
                }
                searchInput(requestTourMap, mapEntry, scanIn);
            }
        }else if (requestTourMap.get(TOUR_TYPE_KEY).equalsIgnoreCase(EXCURSION_TOUR_TYPE)){
            while (iteration.hasNext()){
                mapEntry = (Map.Entry)iteration.next();
                scanIn = new Scanner(System.in);
                if ((((String) mapEntry.getKey()).equalsIgnoreCase(CRUISE_TYPE_KEY)) || (((String) mapEntry.getKey()).equalsIgnoreCase(TREATMENT_TYPE_KEY))){
                    continue;
                }
                searchInput(requestTourMap, mapEntry, scanIn);
            }
        }
    }

    /**
     * searchInput
     * input user's parameters manually
     * @param requestTourMap - request parameters collection
     * @param mapEntry - Map.Entry object
     * @param scanIn - Scanner object
     */
    private void searchInput(LinkedHashMap<String, String> requestTourMap, Map.Entry mapEntry, Scanner scanIn) {
        userView.printMessage(mapEntry.getKey() + REQUEST_SEPARATOR);
        inputString = scanIn.next();
        requestTourMap.put((String) mapEntry.getKey(), inputString);
    }

    /**
     * requestUserStart
     * start method
     */
    public void requestUserStart () {
        try {
            tourPackageModel.fileExists();
            userView.printResult(tourPackageModel.readTourFile(requestUserController()));
        } catch (IOException e) {
            userView.printMessage(FILE_NOT_FOUND);
        } catch (NumberFormatException eN) {
            userView.printMessage(PARAMETERS_NOT_VALID);
        }
    }
}
