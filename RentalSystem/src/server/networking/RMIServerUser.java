package server.networking;

import server.model.user.ManageUser;
import shared.networking.ServerUser;
import shared.objects.user.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerUser implements ServerUser {

    private ManageUser model;

    public RMIServerUser(ManageUser model) {
        this.model = model;
        try {
            UnicastRemoteObject.exportObject(this, 0);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }

	@Override
	public void add(User user)
    {
        model.add(user);
    }

	@Override
	public User get(String username) {
		return model.get(username);
	}

	@Override
	public void login(String username, String password) {
		model.login(username, password);
	}

	@Override
	public User getUser() {
		return model.getUser();
	}

}
