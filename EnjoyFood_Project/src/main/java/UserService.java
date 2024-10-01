import user.model.User;

public interface UserService {

	boolean login(User user);

	boolean siunup(User user);

	boolean duplicateId(String id);

	boolean duplicatePhoneNumber(String phoneNumber);

	boolean duplicateOwnerNumber(String ownerNumber);

}
