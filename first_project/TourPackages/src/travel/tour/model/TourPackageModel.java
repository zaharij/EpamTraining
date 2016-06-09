package travel.tour.model;

import travel.tour.model.fabric.*;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static travel.tour.model.Constants.*;

/**
 * TourPackageModel
 * business logic of project
 * Created by Zakhar on 29.05.2016.
 */
public class TourPackageModel {
    private PredicateFilter predicate;
    private ArrayList<String> currentTourListString = new ArrayList<>();
    private ArrayList<TourPackage> tourPackagesListOutput = new ArrayList<>();
    private ArrayList<TourPackage> tourPackagesListResult = new ArrayList<>();
    Pattern tourPattern = Pattern.compile(VOUCHER_REGULAR, Pattern.MULTILINE);
    Pattern parameterPattern = Pattern.compile(PARAMETER_REGULAR, Pattern.MULTILINE);
    Pattern tourTypePattern = Pattern.compile(TOUR_TYPE_REGULAR, Pattern.MULTILINE);

    /**
     * readTourFile
     * reading initilising file
     * @param requestUserControllerMap - parameters from user
     * @return ArrayList of suitable vouchers
     * @throws FileNotFoundException
     */
    public ArrayList<TourPackage> readTourFile(LinkedHashMap requestUserControllerMap) throws FileNotFoundException {
        File file = new File(FILE_DIR);
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String currentLine;

                while ((currentLine = in.readLine()) != null) {
                    Matcher tourMatcher = tourPattern.matcher(currentLine);
                    Matcher parameterMatcher = parameterPattern.matcher(currentLine);
                    Matcher tourTypeMatcher = tourTypePattern.matcher(currentLine);
                    while (tourMatcher.find() && tourTypeMatcher.find()
                            && tourTypeMatcher.group().equalsIgnoreCase((String) requestUserControllerMap.get(TOUR_TYPE_KEY))){
                        currentTourListString = new ArrayList<>();
                        while (parameterMatcher.find()){
                            currentTourListString.add(parameterMatcher.group());
                        }
                        TourMaker tourMaker = getMakerByType((String) requestUserControllerMap.get(TOUR_TYPE_KEY));
                        TourPackage tourPackage = tourMaker.createTourVoucher(currentTourListString);
                        tourPackagesListOutput.add(tourPackage);
                    }
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        filterTourList(tourPackagesListOutput, chooseFilterPredicate(requestUserControllerMap));
        sortByPrice();
        return tourPackagesListResult;
    }

    /**
     * chooseFilterPredicate
     * choosing filter for different tour vouchers
     * @param requestUserControllerMap user's request parameters
     * @return - filter
     */
    private Predicate chooseFilterPredicate (LinkedHashMap requestUserControllerMap) {
        predicate = new PredicateFilter(requestUserControllerMap);
        if (requestUserControllerMap.get(TOUR_TYPE_KEY).toString().equalsIgnoreCase(REST_TOUR_TYPE)){
            return predicate.getAllFilterRestPredicate();
        } else if (requestUserControllerMap.get(TOUR_TYPE_KEY).toString().equalsIgnoreCase(CRUISE_TOUR_TYPE)) {
            return predicate.getAllFilterCruisePredicate();
        } else if (requestUserControllerMap.get(TOUR_TYPE_KEY).toString().equalsIgnoreCase(EXCURSION_TOUR_TYPE)) {
            return predicate.getAllFilterExcursionPredicate();
        } else {
            return predicate.getAllFilterTreatmentPredicate();
        }
    }

    /**
     * filterTourList
     * filter list by given parameters
     * @param listOfTours - given tours
     * @param predicate - filter py parameter
     */
    private void filterTourList(List<TourPackage> listOfTours, Predicate predicate) {
        for(int iterator = NO_ELEMENTS; iterator < listOfTours.size(); iterator++){
            if(predicate.test(listOfTours.get(iterator))) {
                tourPackagesListResult.add(listOfTours.get(iterator));
            }
        }
    }

    /**
     * fileExists
     * file validation
     */
    public void fileExists() throws FileNotFoundException {
        File file = new File(FILE_DIR);
        if (!file.exists()){
            throw new FileNotFoundException();
        }
    }

    /**
     * sortByPrice
     * sort output List by price
     */
    private void sortByPrice(){
        tourPackagesListResult.sort(Comparator.comparing(TourPackage::getPrice));
    }

    /**
     * getMakerByType
     * making objects by given type, main method which implements Fabric Method pattern
     * @param type suitable type name
     * @return needed maker
     */
    private TourMaker getMakerByType (String type) {
        if(type.equalsIgnoreCase(REST_TOUR_TYPE)) {
            return new RestTourMaker();
        }else if(type.equalsIgnoreCase(CRUISE_TOUR_TYPE)) {
            return new CruiseTourMaker();
        }else if(type.equalsIgnoreCase(EXCURSION_TOUR_TYPE)){
            return new ExcursionTourMaker();
        }else if(type.equalsIgnoreCase(TREATMENT_TOUR_TYPE)){
            return new TreatmentTourMaker();
        }else {
            throw new RuntimeException(NO_TYPE_EXCEPTION);
        }
    }
}
