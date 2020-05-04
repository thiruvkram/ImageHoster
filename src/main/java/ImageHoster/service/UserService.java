package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    /**
     * This method check if the password strength is valid.
     * Password strength is valid if it contains one digit , one alphabet and one special character.
     * @param password
     * @return Returns true if the password has expected strength else false.
     */
    public boolean isPasswordStrengthValid(String password) {
        if (!password.isEmpty() && password.length() >= 3) {
            boolean containsChar = false;
            boolean containsNum = false;
            boolean containsSpecialChar = false;
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isDigit(c)) {
                    containsNum = true;
                    continue;
                }
                if (Character.isLetter(c)) {
                    containsChar = true;
                    continue;
                }
                if (!Character.isLetter(c) && !Character.isLetter(c)) {
                    containsSpecialChar = true;
                    continue;
                }
            }
            if (containsChar && containsNum && containsSpecialChar) {
                return true;
            }
        }
        return false;
    }
}
