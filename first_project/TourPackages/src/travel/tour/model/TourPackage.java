package travel.tour.model;

import travel.tour.model.enumeration.*;
import java.util.ArrayList;

/**
 * TourPackage
 * main class of Touristic vouchers objects
 * Created by Zakhar on 28.05.2016.
 */
public abstract class TourPackage {
    private int voucherNumbers;
    private CountryEnum countryEnum;
    private TransportEnum transportEnum;
    private FoodTypeEnum foodTypeEnum;
    private int daysNumber;
    private double price;

    public double getPrice() {
        return price;
    }

    public TourPackage (String voucherNumbers, String countryString, String transportString, String foodTypeString, String daysNumberString, String priceString) {
        this.voucherNumbers = Integer.parseInt(voucherNumbers);
        this.countryEnum = CountryEnum.valueOf(countryString.toUpperCase());
        this.transportEnum = TransportEnum.valueOf(transportString.toUpperCase());
        this.foodTypeEnum = FoodTypeEnum.valueOf(foodTypeString.toUpperCase());
        this.daysNumber = Integer.parseInt(daysNumberString);
        this.price = Double.parseDouble(priceString.toUpperCase());
    }

    public TourPackage () {
    }

    public int getVoucherNumbers() {
        return voucherNumbers;
    }

    public CountryEnum getCountryEnum() {
        return countryEnum;
    }

    public TransportEnum getTransportEnum() {
        return transportEnum;
    }

    public FoodTypeEnum getFoodTypeEnum() {
        return foodTypeEnum;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    /**
     * getResultArrayListString
     * simple getter
     * @return ArrayList of object parameters
     */
    public abstract ArrayList<String> getResultArrayListString();
}
