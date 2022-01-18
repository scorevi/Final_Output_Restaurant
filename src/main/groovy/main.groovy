import java.io.File
class Globals {
    static List restoNameList = []
    static List locationList = ["Balintawak", "Kamuning", "Quezon Ave."]
    static List categoryList = ["Fine Dining", "Fast Food", "Desserts"]
    static List restaurantList = [][]

    static def restoCount = 0

    static def username // input user
    static def password // input password
    static def userRole // determining user's privileges
    static def loginState = false // if user's logged in or out
    static def optionSelected // determining index of selected choices

}

restoLogin()

void restoLogin() { // a.k.a Main Method

    println("Login to RestoFinder")

    print("Username: ")

    Globals.username = System.in.newReader().readLine()

    print("Password: ")

    Globals.password = System.in.newReader().readLine()


    while (!Globals.loginState) {


        if (Globals.username == "admin" && Globals.password == "admin1234") {
            Globals.loginState = true
            Globals.userRole = "Administrator"
            println("")
            println("Login Success!")
            initializeMainMenu()

        } else if (Globals.username == "user" && Globals.password == "user1234") {
            Globals.loginState = true
            Globals.userRole = "User"
            println("")
            println("Login Success!")
            initializeMainMenu()

        } else {
            println("Login Failed! Incorrect Username/Password!")
            println("")

            Globals.loginState = false

            print("Username: ")
            Globals.username = System.in.newReader().readLine()
            print("Password: ")
            Globals.password = System.in.newReader().readLine()

        }

    }

}


void initializeMainMenu() {
    println("Checking files...")
    println("")
    println("---Main Menu---")

    println("1. Search Restaurants") // Member 1 - Sean
    println("2. View Restaurant List") // Member 1 - Sean
    println("3. View Location List") // Member 1 - Sean
    println("4. Help") // Member 1 - Sean
    println("5. Log out") // Member 1 - Sean
    println("6. Exit") // Member 1 - Sean

    println("")

    if (Globals.userRole == "Administrator") {

        println("---Administrative Stuff---")
        println("7. Add Restaurant") // Member 1 - Sean
        println("8. Add Location") // Member 2
        println("9. Edit Restaurant") // Member 1 - Sean
        println("10. Remove Restaurant") // Member 1 - Sean
        println("11. Remove Location") // Member 2 - Pat
        println("12. Add Category") // Member 2 - Pat
        println("13. Remove Category") // Member 2 - Pat

    }

    println("")

    print("Number selection: ")

    Globals.optionSelected = System.in.newReader().readLine()

    switch (Globals.userRole) { // Checking user privileges

        case "Administrator":
            switch (Globals.optionSelected) {
                case "7":
                    AddRestaurant()
                    break
                case "8":
                    AddLocation()
                    break
                case "9":
                    EditRestaurant()
                    break
                case "10":
                    RemoveRestaurant()
                    break
                case "11":
                    RemoveLocation()
                    break
                case "12":
                    AddCategory()
                    break
                case "13":
                    RemoveCategory()
                    break

            }

        default: // User for default case
            switch (Globals.optionSelected) {
                case "1":
                    break
                case "2":
                    break
                case "3":
                    ViewListFunc()
                    break
                case "4":
                    break
                case "5":
                    if (Globals.loginState) {

                        Globals.loginState = false
                        Globals.username = ""
                        Globals.password = ""
                        Globals.userRole = ""

                        println("Log out successful!")
                        println("")


                        restoLogin()
                    }
                    break
                case "6":
                    System.exit(0)
                    break
                default:
                    initializeMainMenu()
                    break

            }
            break

    }

}

// Methods //

// User Functions ---

void SearchRestaurants() {

}

void ViewListFunc() { // View restaurants and locations
    def userResponse

    println("")
    println("Restaurant Locations: ")
    for (item in Globals.locationList) {
        println(item)
    }

    println("")
    println("1. Go back")
    print("Selection: ")
    userResponse = System.in.newReader().readLine()

    switch (userResponse) {
        case "1":
            initializeMainMenu()
            break
        default:
            ViewListFunc()
            break
    }

}

// Administrator Functions ---

void AddRestaurant() {
    def restoName
    def restoLocation
    def restoCategory
    def restoAddress
    boolean restoActionFinished = false

    println("--Add Restaurant--")
    print("Name: ")
    restoName = System.in.newReader().readLine()

    print("Location: ")
    restoLocation = System.in.newReader().readLine()
    while (!restoActionFinished) {

        if (restoLocation in Globals.locationList) {
            print("Category: ")
            restoCategory = System.in.newReader().readLine()

            while (!restoActionFinished) {

                if (restoCategory in Globals.categoryList) {
                    println("Successfully added restaurant to the list!")

                    // Add data to their respective list array
                    Globals.restaurantList.add([Globals.restoCount][0], restoName) // index 0
                    Globals.restaurantList.add([Globals.restoCount][0], restoLocation) // index 1
                    Globals.restaurantList.add([Globals.restoCount][0], restoCategory) // index 2
                    Globals.restaurantList.add([Globals.restoCount][0], restoAddress) // index 3
                    restoActionFinished = true
                    sleep(5000)
                    initializeMainMenu()

                } else {
                    println("Category not found in the restaurant category list. Please try again!")
                    println("")
                    println("List of valid restaurant categories: ")
                    for (item in Globals.categoryList) {
                        println(item)
                    }
                    println("")
                    print("Category: ")
                    restoCategory = System.in.newReader().readLine()
                }

            }

        } else {
            println("Location not found in the location list. Please try again!")
            println("")
            println("List of valid locations: ")
            for (item in Globals.locationList) {
                println(item)
            }
            println("")
            print("Location: ")
            restoLocation = System.in.newReader().readLine()

        }

    }


}

void AddLocation() {
    def restoLocationName
    def restoActionFinished

    println("--Add Location--")
    print("Location: ")
    restoLocationName = System.in.newReader().readLine()

    while (!restoActionFinished) {
        if (restoLocationName.isBlank()) {
            println("Invalid input! You entered nothing, please try again!")
            print("Location: ")
            restoLocationName = System.in.newReader().readLine()
        } else {
            restoActionFinished = true
            Globals.locationList.add(restoLocationName)
            println("Successfully added location!")
            sleep(5000)
        }
    }

}

void EditRestaurant() {

}

void RemoveRestaurant() {

}

void RemoveLocation() {

}

void AddCategory() {

}

void RemoveCategory() {

}