def username
def password
def userRole
def loginState = false
def optionSelected


println("Login to RestoFinder")

print("Username: ")

username = System.in.newReader().

        readLine()

print("Password: ")

password = System.in.newReader().

        readLine()

while (!loginState) {


    if (username == "admin" && password == "admin1234") {
        loginState = true
        userRole = "Administrator"
        println("")
        println("Login Success!")
        break
    } else if (username == "user" && password == "user1234") {
        loginState = true
        userRole = "User"
    } else {
        println("Login Failed! Incorrect Username/Password!")
        println("")

        loginState = false

        print("Username: ")
        username = System.in.newReader().readLine()
        print("Password: ")
        password = System.in.newReader().readLine()

    }

}


println("")

println("---Main Menu---")

println("1. Search Restaurants")

println("2. Restaurant List")

println("3. Location List")

println("4. Help")

if (userRole == "Administrator") {

    println("---Administrative Stuff---")
    println("5. Add Restaurant")
    println("6. Add Location")
    println("7. Edit Restaurant")
    println("8. Remove Restaurant")
    println("9. Remove Location")

}

println("")

print("Selection: ")

optionSelected = System.in.newReader().

        readLine()

switch (userRole) { // Checking user privileges

    case "Administrator":
        switch (optionSelected) {
            case 5:
                AddRestaurant()
                break
            case 6:
                AddLocation()
                break
            case 7:
                EditRestaurant()
                break
            case 8:
                RemoveRestaurant()
                break
            case 9:
                RemoveLocation()
                break

        }

    default: // User for default case
        switch (optionSelected) {
            case 1:
                break

        }
        break

}


// Methods

void AddRestaurant() {

}

void AddLocation() {

}

void EditRestaurant() {

}

void RemoveRestaurant() {

}

void RemoveLocation() {

}