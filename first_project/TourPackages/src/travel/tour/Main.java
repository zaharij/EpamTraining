package travel.tour;

import travel.tour.controller.RequestUserController;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        RequestUserController q = new RequestUserController();
        q.requestUserStart();
    }
}