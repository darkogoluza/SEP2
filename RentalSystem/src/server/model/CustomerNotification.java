package server.model;

import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.objects.user.User;
import shared.objects.user.UserRole;

public class CustomerNotification implements Runnable {
    ModelProxy modelProxy;

    public CustomerNotification(ModelProxy modelProxy) {
        this.modelProxy = modelProxy;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            User user = modelProxy.getManageUser().getLoggedUser();
            System.out.println(user);
            System.out.println("it is working");
            if(user == null)
                continue;

            if(!user.getRole().equals(UserRole.customer))
                continue;

            ReservationList allReservations = modelProxy.getManageReservations().getAllReservations().filterByCustomerUsername(user.getUsername());

            for (int i = 0; i < allReservations.size(); i++) {
                if(allReservations.getByIndex(i).getStatus() != ReservationStatus.rented)
                    continue;

                if(allReservations.getByIndex(i).expiresIn(60)) {
                    System.out.println("Expires in 60h");
                    //TODO just send an event to view and show some alert.
                    break;
                }
            }
        }
    }
}
