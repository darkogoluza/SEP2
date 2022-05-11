package client.model.reservation;

import java.sql.SQLException;

public interface ManageReservationPersistence
{
    ReservationList load() throws SQLException;
    void save((ReservationList reservationList)) throws SQLException;
    void save() throws SQLException;



}
