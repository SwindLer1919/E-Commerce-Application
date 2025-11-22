
public class Session {
    
    public static User user;

    public static User getCurrentUser() {
        return user;
    }

    public static void setCurrentUser(User user) {
        Session.user = user;
    }
}
