package travel.tour.view;

import travel.tour.model.TourPackage;
import static travel.tour.model.Constants.*;
import java.util.*;

/**
 * RequestUserView
 * implements comminication with user
 * Created by Zakhar on 29.05.2016.
 */
public class RequestUserView {

    /**
     * printMessage
     * printing given message
     * @param message message to print
     */
    public void printMessage (String message) {
        System.out.print(message);
    }

    /**
     * printResult
     * printing finded results
     * @param resultList ArrayList of result
     */
    public void printResult(ArrayList<TourPackage> resultList) {
        if (resultList.isEmpty()){
            System.out.println(NO_RESULTS_MESSAGE);
        } else {
            for (TourPackage tourPackage : resultList) {
                System.out.println();
                for (String result : tourPackage.getResultArrayListString()) {
                    System.out.print(result + RESULT_BORDER);
                }
            }
        }
    }
}
