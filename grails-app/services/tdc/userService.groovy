package tdc

import com.google.gson.Gson

class userService {

    def users = [:]

    def addUser(user) {

        if(!users.containsKey(user.userName)) {
            users[user["userName"]] = new Gson().fromJson(user.toString(), User.class)
            return true
        }

        return false
    }

    def getUsers() {
        users.values()
    }

    def get(userName) {
        users[userName]
    }

    def modifyUser(user) {
        def userToModify = users[user.userName]
        userToModify.firstName = user.firstName
        userToModify.lastName = user.lastName
        userToModify.birthday = user.birthday
        userToModify.position = user.position
        userToModify.profilePicture = user.profilePicture
        userToModify.email = user.email
        users[user.userName] = userToModify

    }

    def loadInitialData() {
        users["admin"] = new User("admin", "admin", "roberto", "perez", "", [latitude: 200, longitude: 200],null, "admin@mimail.com")
        users["abc"] = new User("abc", "abc", "juan", "perez", "", [latitude: 200, longitude: 500], null, "abc@mimail.com")
        users["soso123"] = new User("meme123", "admin", "roberto", "perez", "",[latitude: 200, longitude: 400], null, "meme@mimail.com")
    }

    def validateLogin(user) {
        if(users.containsKey(user.userName)) {
            if(users[user.userName].password == user.password) {
                return true
            }
        }

        return false
    }

    def updatePassword(userData) {

        if(users.containsKey(userData.userName)) {
            if(users[userData.userName].password == userData.oldPassword) {
                users[userData.userName].password = userData.newPassword
                return true
            }
        }

        return false
    }
}
