package client.model;

import javafx.application.Platform;
import shared.objects.errors.AlertHandler;
import shared.objects.reservation.Reservation;
import shared.objects.reservation.ReservationList;
import shared.objects.reservation.ReservationStatus;
import shared.objects.user.User;
import shared.objects.user.UserRole;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomerNotification implements Runnable, PropertyChangeSubject {
    private ModelProxy modelProxy;
    private final int hoursBeforeExpiration = 1;
    private PropertyChangeSupport changeSupport;

    public CustomerNotification(ModelProxy modelProxy) {
        this.modelProxy = modelProxy;
        changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            User user = modelProxy.getManageUser().getLoggedUser();
            if(user == null)
                continue;

            if(!user.getRole().equals(UserRole.customer))
                continue;

            ReservationList allReservations = modelProxy.getManageReservations().getAllReservations().filterByCustomerUsername(user.getUsername());

            for (int i = 0; i < allReservations.size(); i++) {
                Reservation reservation = allReservations.getByIndex(i);
                if(reservation.getStatus() != ReservationStatus.rented)
                    continue;

                if(reservation.expiresIn(hoursBeforeExpiration)) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            AlertHandler.getInstance().onOrderExpireSoon(reservation.getId(), hoursBeforeExpiration);
                        }
                    });
                    break;
                }
            }
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {

        changeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(name, listener);
    }
}
